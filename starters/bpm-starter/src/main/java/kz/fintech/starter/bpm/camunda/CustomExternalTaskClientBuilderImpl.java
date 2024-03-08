package kz.fintech.starter.bpm.camunda;

import kz.fintech.starter.bpm.config.BpmProps;
import org.camunda.bpm.client.impl.ExternalTaskClientBuilderImpl;
import org.camunda.bpm.client.interceptor.impl.RequestInterceptorHandler;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

public class CustomExternalTaskClientBuilderImpl extends ExternalTaskClientBuilderImpl {

    private final String processDefinitionKey;
    private final BpmProps bpmProps;
    private final LoadBalancerClient loadBalancerClient;

    public CustomExternalTaskClientBuilderImpl(String processDefinitionKey, BpmProps bpmProps, LoadBalancerClient loadBalancerClient) {
        this.processDefinitionKey = processDefinitionKey;
        this.bpmProps = bpmProps;
        this.loadBalancerClient = loadBalancerClient;
    }

    @Override
    protected void initTopicSubscriptionManager() {
        topicSubscriptionManager = new CustomTopicSubscriptionManager(engineClient, typedValues, lockDuration,
                processDefinitionKey, bpmProps);
        topicSubscriptionManager.setBackoffStrategy(getBackoffStrategy());

        if (isBackoffStrategyDisabled) {
            topicSubscriptionManager.disableBackoffStrategy();
        }

        if (isAutoFetchingEnabled()) {
            topicSubscriptionManager.start();
        }
    }

    @Override
    protected void initEngineClient() {
        RequestInterceptorHandler requestInterceptorHandler = new RequestInterceptorHandler(interceptors);
        CustomRequestExecutor requestExecutor = new CustomRequestExecutor(requestInterceptorHandler, objectMapper);
        engineClient = new EurekaEngineClient(workerId, maxTasks, asyncResponseTimeout, bpmProps.getEurekaBaseUrl(), requestExecutor, usePriority, loadBalancerClient);
    }
}
