package kz.fintech.starter.bpm.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import kz.fintech.exceptions.ColvirException;
import kz.fintech.starter.bpm.exceptions.BpmException;
import kz.fintech.starter.bpm.exceptions.NoStackTraceException;
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
public abstract class ExternalTaskExecutorBase<TArg> {

    /*public static final int MAX_ERROR_SIZE = 4000;
    private final CustomBizprocManagementServiceClient bizprocManagementServiceClient;
    private final ProductInfoServiceClient productInfoServiceClient;

    protected ExternalTaskExecutorBase(CustomBizprocManagementServiceClient bizprocManagementServiceClient,
                                       ProductInfoServiceClient productInfoServiceClient) {
        this.bizprocManagementServiceClient = bizprocManagementServiceClient;
        this.productInfoServiceClient = productInfoServiceClient;
    }

    public void safeExecute(ExternalTask task, ExternalTaskService service, Runner<TArg> runner) {
        safeExecute(task, service, true, runner);
    }

    public void safeExecuteSaveOnError(ExternalTask task, ExternalTaskService service, Runner<TArg> runner) {
        safeExecute(task, service, true, true, false, false, runner);
    }

    public void safeExecuteWithoutRequestVar(ExternalTask task, ExternalTaskService service, Runner<TArg> runner) {
        safeExecute(task, service, false, true, false, true, runner);
    }

    public void safeExecute(ExternalTask task, ExternalTaskService service, boolean modifying, Runner<TArg> runner) {
        safeExecute(task, service, modifying, false, false, false, runner);
    }

    public void safeExecute(ExternalTask task,
                            ExternalTaskService service,
                            boolean modifying,
                            boolean saveRequestOnError,
                            boolean hideStackTrace,
                            Runner<TArg> runner) {
        safeExecute(task, service, modifying, saveRequestOnError, hideStackTrace, false, runner);
    }

    public void safeExecute(ExternalTask task,
                            ExternalTaskService service,
                            boolean modifying,
                            boolean saveRequestOnError,
                            boolean hideStackTrace,
                            boolean dontLoadProcessVariable,
                            Runner<TArg> runner) {
        try {
            execute(task, service, modifying, saveRequestOnError, modifying ? false : dontLoadProcessVariable, runner);
        } catch (BpmException e) {
            log.error(e.getMessage());
            reportError(e.getErrorCode(), task, service, true, e);
        } catch (NoStackTraceException e) {
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
            Integer versionId = 1;
            if (task.getProcessDefinitionId().contains(":")) {
                String proc[] = task.getProcessDefinitionId().split(":");
                versionId = Integer.parseInt(proc[1]);
            }
            String decision = productInfoServiceClient.getErrorDecision(task.getProcessInstanceId(),
                    task.getProcessDefinitionKey(),
                    versionId,
                    task.getActivityId(),
                    errorMessage);
            bizprocManagementServiceClient.setProcessVariable(task.getProcessInstanceId(), "errorDecision", decision);
        } catch (Exception ex) {
            log.error(String.format("ExternalTaskExecutorBase reportError task %s", task), ex);
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

    public void unsafeExecute(ExternalTask task, ExternalTaskService service, Runner<TArg> runner) {
        unsafeExecute(task, service, true, false, runner);
    }

    public void unsafeExecuteWithoutRequestVar(ExternalTask task, ExternalTaskService service, Runner<TArg> runner) {
        unsafeExecute(task, service, false, true, runner);
    }

    public void unsafeExecute(ExternalTask task, ExternalTaskService service, boolean modifying, Runner<TArg> runner) {
        unsafeExecute(task, service, modifying, false, runner);
    }

    public void unsafeExecute(ExternalTask task, ExternalTaskService service, boolean modifying, boolean dontLoadProcessVariable, Runner<TArg> runner) {
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
                         Runner<TArg> runner) throws JsonProcessingException {
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
