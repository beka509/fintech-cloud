package kz.fintech.starter.bpm2.exceptions;

import org.camunda.bpm.client.task.ExternalTask;

public class RequestNotFoundExceptionNew extends RuntimeException {

    private static final long serialVersionUID = -251361449855124252L;

    public RequestNotFoundExceptionNew(ExternalTask task) {
        super(task.getProcessDefinitionKey() + " [id: " + task.getProcessInstanceId() + ", key: " + task.getBusinessKey() + "] not found");
    }
}
