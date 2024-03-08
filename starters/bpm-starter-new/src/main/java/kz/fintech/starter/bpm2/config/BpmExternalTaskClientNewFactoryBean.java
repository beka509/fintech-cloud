package kz.fintech.starter.bpm2.config;

import kz.fintech.starter.bpm2.annotations.BpmExternalTaskNew;
import kz.fintech.starter.bpm2.annotations.BpmExternalTaskContainerNew;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.task.ExternalTaskHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

import static kz.fintech.starter.bpm2.utils.HelpersNew.getTopicName;

@Slf4j
class BpmExternalTaskClientNewFactoryBean implements FactoryBean<ExternalTaskClient>, ApplicationContextAware, ApplicationRunner, InitializingBean, DisposableBean {

    @Autowired
    private BpmPropsNew config;
    private ExternalTaskClient client;
    private ApplicationContext applicationContext;

    @Override
    public ExternalTaskClient getObject() {
        return client;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Class<?> getObjectType() {
        return ExternalTaskClient.class;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        val services = applicationContext.getBeansWithAnnotation(BpmExternalTaskContainerNew.class);
        services.forEach((beanName, bean) -> {
            val process = bean.getClass().getAnnotation(BpmExternalTaskContainerNew.class).process();
            ReflectionUtils.doWithMethods(bean.getClass(),
                    method -> subscribeToExternalTasks(bean, method, process),
                    method -> method.getReturnType() == ExternalTaskHandler.class && method.getParameterCount() == 0);
        });
        client.start();
    }

    @SneakyThrows
    private void subscribeToExternalTasks(Object bean, Method method, String processDefinitionKey) {
        String topicName = getTopicName(method);
        long lockDuration = 30_000;
        try {
            val annotation = method.getAnnotation(BpmExternalTaskNew.class);
            if (StringUtils.hasText(annotation.topic())) {
                topicName = annotation.topic();
            }
            lockDuration = annotation.lockDuration();
        } catch (NullPointerException ignored) {
        }
        client
                .subscribe(topicName)
                .lockDuration(lockDuration)
                .processDefinitionKey(processDefinitionKey)
                .handler((ExternalTaskHandler) method.invoke(bean))
                .open();
        log.info("Listening for external tasks. Process [" + processDefinitionKey + "], topic [" + topicName + "]");
    }

    @Override
    public void afterPropertiesSet() {
        client = ExternalTaskClient.create()
                .baseUrl(config.getBaseUrl())
                .disableAutoFetching()
                .asyncResponseTimeout(config.getResponseTimeout())
                .build();
    }

    @Override
    public void destroy() {
        client.stop();
    }
}
