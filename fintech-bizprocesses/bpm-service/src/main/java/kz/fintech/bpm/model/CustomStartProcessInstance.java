package kz.fintech.bpm.model;

import org.camunda.bpm.engine.rest.dto.runtime.StartProcessInstanceDto;

public class CustomStartProcessInstance extends StartProcessInstanceDto {

    public String getInitiator() {
        return initiator;
    }

    public void setInitiator(String initiator) {
        this.initiator = initiator;
    }

    private String initiator;
}
