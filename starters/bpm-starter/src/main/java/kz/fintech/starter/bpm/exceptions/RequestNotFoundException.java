package kz.fintech.starter.bpm.exceptions;

import org.camunda.bpm.client.task.ExternalTask;

public class RequestNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -251361449855124252L;

    public RequestNotFoundException(ExternalTask task) {
        super(task.getProcessDefinitionKey() + " [id: " + task.getProcessInstanceId() + ", key: " + task.getBusinessKey() + "] not found");
    }
}
