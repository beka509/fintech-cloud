package kz.fintech.starter.bpm.camunda;

import kz.fintech.starter.bpm.exceptions.CamundaServerNotFoundException;
import lombok.SneakyThrows;
import org.camunda.bpm.client.impl.EngineClient;
import org.camunda.bpm.client.impl.EngineClientException;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.impl.ExternalTaskImpl;
import org.camunda.bpm.client.task.impl.dto.BpmnErrorRequestDto;
import org.camunda.bpm.client.task.impl.dto.CompleteRequestDto;
import org.camunda.bpm.client.task.impl.dto.ExtendLockRequestDto;
import org.camunda.bpm.client.task.impl.dto.FailureRequestDto;
import org.camunda.bpm.client.topic.impl.dto.FetchAndLockRequestDto;
import org.camunda.bpm.client.topic.impl.dto.TopicRequestDto;
import org.camunda.bpm.client.variable.impl.TypedValueField;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class EurekaEngineClient extends EngineClient {

    protected final CustomRequestExecutor engineInteraction;
    protected final LoadBalancerClient loadBalancerClient;
    protected final URI baseUri;

    @SneakyThrows
    public EurekaEngineClient(String workerId, int maxTasks, Long asyncResponseTimeout, String baseUrl, CustomRequestExecutor engineInteraction, LoadBalancerClient loadBalancerClient) {
        super(workerId, maxTasks, asyncResponseTimeout, baseUrl, engineInteraction);
        this.engineInteraction = engineInteraction;
        this.loadBalancerClient = loadBalancerClient;
        this.baseUri = new URI(baseUrl);
    }

    @SneakyThrows
    public EurekaEngineClient(String workerId, int maxTasks, Long asyncResponseTimeout, String baseUrl, CustomRequestExecutor engineInteraction, boolean usePriority, LoadBalancerClient loadBalancerClient) {
        super(workerId, maxTasks, asyncResponseTimeout, baseUrl, engineInteraction, usePriority);
        this.engineInteraction = engineInteraction;
        this.loadBalancerClient = loadBalancerClient;
        this.baseUri = new URI(baseUrl);
    }

    public List<ExternalTask> fetchAndLock(List<TopicRequestDto> topics) throws EngineClientException {
        FetchAndLockRequestDto payload = new FetchAndLockRequestDto(workerId, maxTasks, asyncResponseTimeout, topics, usePriority);
        String resourceUrl = getBaseUrl() + FETCH_AND_LOCK_RESOURCE_PATH;
        ExternalTask[] externalTasks = engineInteraction.postRequest(resourceUrl, payload, ExternalTaskImpl[].class);
        return Arrays.asList(externalTasks);
    }

    public void unlock(String taskId) throws EngineClientException {
        String resourcePath = UNLOCK_RESOURCE_PATH.replace("{id}", taskId);
        String resourceUrl = getBaseUrl() + resourcePath;
        engineInteraction.postRequest(resourceUrl, null, Void.class);
    }

    public void complete(String taskId, Map<String, Object> variables, Map<String, Object> localVariables) throws EngineClientException {
        Map<String, TypedValueField> typedValueDtoMap = typedValues.serializeVariables(variables);
        Map<String, TypedValueField> localTypedValueDtoMap = typedValues.serializeVariables(localVariables);

        CompleteRequestDto payload = new CompleteRequestDto(workerId, typedValueDtoMap, localTypedValueDtoMap);
        String resourcePath = COMPLETE_RESOURCE_PATH.replace("{id}", taskId);
        String resourceUrl = getBaseUrl() + resourcePath;
        engineInteraction.postRequest(resourceUrl, payload, Void.class);
    }

    public void failure(String taskId, String errorMessage, String errorDetails, int retries, long retryTimeout) throws EngineClientException {
        FailureRequestDto payload = new FailureRequestDto(workerId, errorMessage, errorDetails, retries, retryTimeout);
        String resourcePath = FAILURE_RESOURCE_PATH.replace("{id}", taskId);
        String resourceUrl = getBaseUrl() + resourcePath;
        engineInteraction.postRequest(resourceUrl, payload, Void.class);
    }

    public void bpmnError(String taskId, String errorCode, String errorMessage, Map<String, Object> variables) throws EngineClientException {
        Map<String, TypedValueField> typeValueDtoMap = typedValues.serializeVariables(variables);
        BpmnErrorRequestDto payload = new BpmnErrorRequestDto(workerId, errorCode, errorMessage, typeValueDtoMap);
        String resourcePath = BPMN_ERROR_RESOURCE_PATH.replace("{id}", taskId);
        String resourceUrl = getBaseUrl() + resourcePath;
        engineInteraction.postRequest(resourceUrl, payload, Void.class);
    }

    public void extendLock(String taskId, long newDuration) throws EngineClientException {
        ExtendLockRequestDto payload = new ExtendLockRequestDto(workerId, newDuration);
        String resourcePath = EXTEND_LOCK_RESOURCE_PATH.replace("{id}", taskId);
        String resourceUrl = getBaseUrl() + resourcePath;
        engineInteraction.postRequest(resourceUrl, payload, Void.class);
    }

    public byte[] getLocalBinaryVariable(String variableName, String processInstanceId) throws EngineClientException {
        String resourcePath = getBaseUrl() + GET_LOCAL_BINARY_VARIABLE
                .replace(ID_PATH_PARAM, processInstanceId)
                .replace(NAME_PATH_PARAM, variableName);

        return engineInteraction.getRequest(resourcePath);
    }

    @Override
    public String getBaseUrl() {
        ServiceInstance serviceInstance = loadBalancerClient.choose(baseUri.getHost());
        if (serviceInstance == null) throw new CamundaServerNotFoundException(baseUri.getHost() + " not found");
        return loadBalancerClient.reconstructURI(serviceInstance, baseUri).toASCIIString();
    }
}
