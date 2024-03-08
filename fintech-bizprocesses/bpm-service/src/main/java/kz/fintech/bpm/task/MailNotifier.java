package kz.fintech.bpm.task;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

public class MailNotifier implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("Hello world");
    }
}
