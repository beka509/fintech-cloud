package kz.fintech.starter.bpm2.components;

import kz.fintech.starter.bpm2.annotations.BpmExternalTaskNew;
import kz.fintech.starter.bpm2.annotations.BpmExternalTaskContainerNew;
import kz.fintech.starter.bpm2.camunda.CustomExternalTaskClientBuilderNewImpl;
import kz.fintech.starter.bpm2.config.BpmPropsNew;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.backoff.ExponentialBackoffStrategy;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static kz.fintech.starter.bpm2.utils.HelpersNew.maxTasks;

@Component
@Slf4j
public class BpmExternalTaskClientLifecycleManagerNew implements ApplicationRunner, DisposableBean {

    private final Map<String, ExternalTaskClient> clients;
    private final BpmPropsNew props;
    private final ApplicationContext applicationContext;

    public BpmExternalTaskClientLifecycleManagerNew(BpmPropsNew props, ApplicationContext applicationContext) {
        this.props = props;
        this.applicationContext = applicationContext;
        this.clients = new HashMap<>();
    }

    @Override
    public void run(ApplicationArguments args) {
        val loadBalancerClient = applicationContext.getBean(LoadBalancerClient.class);
        val services = applicationContext.getBeansWithAnnotation(BpmExternalTaskContainerNew.class);
        services.forEach((beanName, bean) -> {
            val processDefinitionKey = getProcessDefinitionKey(bean.getClass());
            for (Method method : ReflectionUtils.getAllDeclaredMethods(bean.getClass())) {
                if (method.getReturnType() == ExternalTaskHandler.class && method.getParameterCount() == 0) {
                    subscribeToExternalTasks(bean, method, processDefinitionKey, loadBalancerClient);
                }
            }
//            ReflectionUtils.doWithMethods(bean.getClass(),
//                    method -> subscribeToExternalTasks(bean, method, processDefinitionKey),
//                    method -> method.isAccessible() && method.getReturnType() == ExternalTaskHandler.class && method.getParameterCount() == 0);
        });
        this.clients.values().forEach(ExternalTaskClient::start);
    }

    private String getProcessDefinitionKey(Class cls) {
        try {
            return ((BpmExternalTaskContainerNew) cls.getAnnotation(BpmExternalTaskContainerNew.class)).process();
        } catch (NullPointerException e) {
            return getProcessDefinitionKey(cls.getSuperclass());
        }
    }

    @SneakyThrows
    private void subscribeToExternalTasks(Object bean, Method method, String processDefinitionKey, LoadBalancerClient loadBalancerClient) {
        String topicName = StringUtils.capitalize(method.getName());
        String key = processDefinitionKey + "_" + topicName;
        long lockDuration = 30_000;
        try {
            val annotation = method.getAnnotation(BpmExternalTaskNew.class);
            if (StringUtils.hasText(annotation.topic())) {
                topicName = annotation.topic();
            }
            lockDuration = annotation.lockDuration();
        } catch (NullPointerException ignored) {
        }
        ExternalTaskClient client;
        if (this.clients.containsKey(key)) {
            client = this.clients.get(key);
        } else {
            int maxTasks = maxTasks(props, key);
            client = new CustomExternalTaskClientBuilderNewImpl(key, props, loadBalancerClient)
                    .baseUrl(props.getBaseUrl())
                    .disableAutoFetching()
                    .asyncResponseTimeout(props.getResponseTimeout())
                    .backoffStrategy(new ExponentialBackoffStrategy(100L, 1.5F, 1_000L))
                    .maxTasks(maxTasks)
                    .build();
            this.clients.put(key, client);
        }
        client.subscribe(key)
                .lockDuration(lockDuration)
                .handler((ExternalTaskHandler) method.invoke(bean))
                .open();
        log.info("Listening for external tasks. Process [" + processDefinitionKey + "], topic [" + topicName + "]");
    }

    @Override
    public void destroy() {
        this.clients.values().forEach(ExternalTaskClient::stop);
    }
}
