package kz.fintech.starter.bpm2.camunda;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.camunda.bpm.client.impl.EngineClientException;
import org.camunda.bpm.client.impl.RequestDto;
import org.camunda.bpm.client.impl.RequestExecutor;
import org.camunda.bpm.client.interceptor.impl.RequestInterceptorHandler;

public class CustomRequestExecutorNew extends RequestExecutor {

    public CustomRequestExecutorNew(RequestInterceptorHandler requestInterceptorHandler, ObjectMapper objectMapper) {
        super(requestInterceptorHandler, objectMapper);
    }

    @Override
    public <T> T postRequest(String resourceUrl, RequestDto requestDto, Class<T> responseClass) throws EngineClientException {
        return super.postRequest(resourceUrl, requestDto, responseClass);
    }

    @Override
    public byte[] getRequest(String resourceUrl) throws EngineClientException {
        return super.getRequest(resourceUrl);
    }

    @Override
    public <T> T executeRequest(HttpUriRequest httpRequest, Class<T> responseClass) throws EngineClientException {
        return super.executeRequest(httpRequest, responseClass);
    }

    @Override
    public <T> ResponseHandler<T> handleResponse(Class<T> responseClass) {
        return super.handleResponse(responseClass);
    }

    @Override
    public <T> T deserializeResponse(HttpEntity httpEntity, Class<T> responseClass) throws EngineClientException {
        return super.deserializeResponse(httpEntity, responseClass);
    }

    @Override
    public ByteArrayEntity serializeRequest(RequestDto dto) throws EngineClientException {
        return super.serializeRequest(dto);
    }

    @Override
    public void initHttpClient(RequestInterceptorHandler requestInterceptorHandler) {
        super.initHttpClient(requestInterceptorHandler);
    }
}
