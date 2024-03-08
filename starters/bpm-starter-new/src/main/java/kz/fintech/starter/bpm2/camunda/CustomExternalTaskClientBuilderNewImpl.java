package kz.fintech.starter.bpm2.camunda;

import kz.fintech.starter.bpm2.config.BpmPropsNew;
import org.camunda.bpm.client.impl.ExternalTaskClientBuilderImpl;
import org.camunda.bpm.client.interceptor.impl.RequestInterceptorHandler;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

public class CustomExternalTaskClientBuilderNewImpl extends ExternalTaskClientBuilderImpl {

    private final String processDefinitionKey;
    private final BpmPropsNew bpmProps;
    private final LoadBalancerClient loadBalancerClient;

    public CustomExternalTaskClientBuilderNewImpl(String processDefinitionKey, BpmPropsNew bpmProps, LoadBalancerClient loadBalancerClient) {
        this.processDefinitionKey = processDefinitionKey;
        this.bpmProps = bpmProps;
        this.loadBalancerClient = loadBalancerClient;
    }

    @Override
    protected void initTopicSubscriptionManager() {
        topicSubscriptionManager = new CustomTopicSubscriptionManagerNew(engineClient, typedValues, lockDuration,
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
        CustomRequestExecutorNew requestExecutor = new CustomRequestExecutorNew(requestInterceptorHandler, objectMapper);
        engineClient = new EurekaEngineClientNew(workerId, maxTasks, asyncResponseTimeout, bpmProps.getEurekaBaseUrl(), requestExecutor, usePriority, loadBalancerClient);
    }
}
