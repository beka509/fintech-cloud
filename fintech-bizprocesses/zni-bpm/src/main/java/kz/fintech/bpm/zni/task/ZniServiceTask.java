package kz.fintech.bpm.zni.task;

import kz.fintech.models.bpm.zni.ZniRequest;
import lombok.var;
import org.camunda.bpm.engine.delegate.DelegateExecution;

public class ZniServiceTask {

    public void approveByCurator(DelegateExecution execution, String authenticatedUserId) throws Exception{
        System.out.println("approve by curator");
        var zniRequest =  (ZniRequest)execution.getVariable("zniRequest");
        System.out.println("requestNumber: "+zniRequest.getRequest().getRequestNumber());
        execution.setVariable("curatorDesition","approved");
    }

    public void executeByGroup(DelegateExecution execution, String authenticatedUserId) throws Exception{
        System.out.println("executed by group");
        var zniRequest =  (ZniRequest)execution.getVariable("zniRequest");
        zniRequest.getRequest().getRequestNumber();
        System.out.println("requestNumber: "+zniRequest.getRequest().getRequestNumber());
    }

    public void revisionByInitiator(DelegateExecution execution, String authenticatedUserId) throws Exception{
        System.out.println("revised by initiator");
        var zniRequest =  (ZniRequest)execution.getVariable("zniRequest");
        execution.setVariable("resendRequest","yes");
    }
}