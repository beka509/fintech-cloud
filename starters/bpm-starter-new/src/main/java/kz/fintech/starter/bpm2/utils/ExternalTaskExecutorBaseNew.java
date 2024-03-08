package kz.fintech.starter.bpm2.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import kz.fintech.exceptions.ColvirException;
import kz.fintech.starter.bpm2.exceptions.BpmExceptionNew;
import kz.fintech.starter.bpm2.exceptions.NoStackTraceExceptionNew;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.camunda.bpm.client.task.ExternalTask;
import org.camunda.bpm.client.task.ExternalTaskService;
import org.springframework.util.StringUtils;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public abstract class ExternalTaskExecutorBaseNew<TArg> {

    public static final int MAX_ERROR_SIZE = 4000;
    //private final CustomBizprocManagementServiceClient bizprocManagementServiceClient;
    //private final ProductInfoServiceClient productInfoServiceClient;

    /*protected ExternalTaskExecutorBaseNew(CustomBizprocManagementServiceClient bizprocManagementServiceClient,
                                          ProductInfoServiceClient productInfoServiceClient) {
        this.bizprocManagementServiceClient = bizprocManagementServiceClient;
        this.productInfoServiceClient = productInfoServiceClient;
    }

    public void safeExecute(ExternalTask task, ExternalTaskService service, RunnerNew<TArg> runner) {
        safeExecute(task, service, true, runner);
    }

    public void safeExecuteSaveOnError(ExternalTask task, ExternalTaskService service, RunnerNew<TArg> runner) {
        safeExecute(task, service, true, true, false, false, runner);
    }

    public void safeExecuteWithoutRequestVar(ExternalTask task, ExternalTaskService service, RunnerNew<TArg> runner) {
        safeExecute(task, service, false, true, false, true, runner);
    }

    public void safeExecute(ExternalTask task, ExternalTaskService service, boolean modifying, RunnerNew<TArg> runner) {
        safeExecute(task, service, modifying, false, false, false, runner);
    }

    public void safeExecute(ExternalTask task,
                            ExternalTaskService service,
                            boolean modifying,
                            boolean saveRequestOnError,
                            boolean hideStackTrace,
                            RunnerNew<TArg> runner) {
        safeExecute(task, service, modifying, saveRequestOnError, hideStackTrace, false, runner);
    }

    /*public void safeExecute(ExternalTask task,
                            ExternalTaskService service,
                            boolean modifying,
                            boolean saveRequestOnError,
                            boolean hideStackTrace,
                            boolean dontLoadProcessVariable,
                            RunnerNew<TArg> runner) {
        try {
            execute(task, service, modifying, saveRequestOnError, modifying ? false : dontLoadProcessVariable, runner);
        } catch (BpmExceptionNew e) {
            log.error(e.getMessage());
            reportError(e.getErrorCode(), task, service, true, e);
        } catch (NoStackTraceExceptionNew e) {
            log.error(e.getMessage());
            reportError(task, service, true, e);
        } catch (UndeclaredThrowableException e) {
            log.error("UndeclaredThrowableException while executing task", e);
            reportError(task, service, hideStackTrace, e);
        } catch (Throwable e) {
            log.error("Exception executing task", e);
            reportError(task, service, hideStackTrace, e);
        }
    }

    private void reportError(ExternalTask task, ExternalTaskService service, boolean hideStackTrace, Throwable e) {
        reportError("SystemError", task, service, hideStackTrace, e);
    }

    private void reportError(String errorCode, ExternalTask task, ExternalTaskService service, boolean hideStackTrace, Throwable e) {
        String errorMessage;
        if (hideStackTrace) {
            errorMessage = e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
            if (!StringUtils.hasText(errorMessage)) errorMessage = e.getClass().toString();
        } else {
            errorMessage = getErrorStack(e, MAX_ERROR_SIZE);
        }
        if (bizprocManagementServiceClient != null)
            bizprocManagementServiceClient.setProcessVariable(task.getProcessInstanceId(), "errorMessage", errorMessage);
        try {
            String proc[] = task.getProcessDefinitionId().split(":");
            String decision = productInfoServiceClient.getErrorDecision(task.getProcessInstanceId(),
                    task.getProcessDefinitionKey(),
                    proc.length > 1 ? Integer.parseInt(proc[1]) : 0,
                    task.getActivityId(),
                    errorMessage);
            bizprocManagementServiceClient.setProcessVariable(task.getProcessInstanceId(), "errorDecision", decision);
        } catch (Exception ex) {
            log.error(String.format("ExternalTaskExecutorBase reportError %s", task.getProcessInstanceId()), ex);
        }
        service.handleBpmnError(task, errorCode, errorMessage, Collections.singletonMap("errorMessage", errorMessage));
    }


    protected String getErrorStack(Throwable e, int maxSize) {
        String error = getErrorStack(e);
        if (error.length() > maxSize) {
            error = error.substring(0, maxSize);
        }
        return error;
    }

    private String trimErrorMessage(String message) {
        if (message.length() > MAX_ERROR_SIZE) {
            return message.substring(0, MAX_ERROR_SIZE);
        }
        return message;
    }

    protected String getErrorStack(Throwable e) {
        if (e instanceof ColvirException) {
            val colvirException = (ColvirException) e;
            return colvirException.getMessage();
        }
        return ExceptionUtils.getStackTrace(e);
    }

    public void unsafeExecute(ExternalTask task, ExternalTaskService service, RunnerNew<TArg> runner) {
        unsafeExecute(task, service, true, false, runner);
    }

    public void unsafeExecuteWithoutRequestVar(ExternalTask task, ExternalTaskService service, RunnerNew<TArg> runner) {
        unsafeExecute(task, service, false, true, runner);
    }

    public void unsafeExecute(ExternalTask task, ExternalTaskService service, boolean modifying, RunnerNew<TArg> runner) {
        unsafeExecute(task, service, modifying, false, runner);
    }

    public void unsafeExecute(ExternalTask task, ExternalTaskService service, boolean modifying, boolean dontLoadProcessVariable, RunnerNew<TArg> runner) {
        try {
            execute(task, service, modifying, modifying, dontLoadProcessVariable, runner);
        } catch (Throwable e) {
            log.error("Exception executing task", e);
            throwError(task, service, e);
        }
    }

    private void execute(ExternalTask task,
                         ExternalTaskService service,
                         boolean modifying,
                         boolean saveRequestOnError,
                         boolean dontLoadProcessVariable,
                         RunnerNew<TArg> runner) throws JsonProcessingException {
        TArg request = dontLoadProcessVariable ? null : getProcessVar(task);
        Map<String, Object> variables = new HashMap<>();
        Map<String, Object> localVariables = new HashMap<>();
        try {
            runner.execute(request, variables, localVariables);
            if (modifying) setProcessVar(task, request);
        } catch (Throwable e) {
            if (modifying && saveRequestOnError) setProcessVar(task, request);
            try {
                onError(task, service, request, e);
            } catch (Throwable e2) {
                log.error("Failed to report error to " + task.getProcessInstanceId(), e2);
            }
            throw e;
        }
        service.complete(task, variables, localVariables);
    }

    private void throwError(ExternalTask task, ExternalTaskService service, Throwable e) {
        service.handleFailure(task, e.getMessage(), getErrorStack(e, 666), task.getRetries() == null ? 5 : task.getRetries() - 1, 10_000);
    }

    public abstract TArg getProcessVar(ExternalTask task);

    public abstract void setProcessVar(ExternalTask task, TArg request);

    protected void onError(ExternalTask task, ExternalTaskService service, TArg request, Throwable e) {

    }*/
}
