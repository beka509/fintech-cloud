package kz.fintech.bpm.func;

import kz.fintech.models.BpmConstants;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import java.lang.reflect.UndeclaredThrowableException;

@lombok.extern.slf4j.Slf4j
public class CamundaHelper {

    public static <TResult, TArg> TResult safeExecute(DelegateExecution execution, Runner<TResult, TArg> runner) {
        return safeExecute(execution, true, runner);
    }

    public static <TResult, TArg> TResult safeExecute(DelegateExecution execution, boolean modifying, Runner<TResult, TArg> runner) {
        return safeExecute(execution, modifying, true, runner);
    }

    public static <TResult, TArg> TResult safeExecute(DelegateExecution execution, boolean modifying, boolean hideStackTrace, Runner<TResult, TArg> runner) {
        try {
            TArg request = getProcessVar(execution);
            TResult result = runner.execute(request);
            if (modifying) CamundaHelper.setProcessVar(execution, request);
            return result;
        } catch (UndeclaredThrowableException e) {
            log.error("UndeclaredThrowableException while executing task", e);
            if (hideStackTrace) {
                String errorMessage = e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
                execution.setVariable("errorMessage", errorMessage);
            } else {
                String error = getErrorStack(e);
                execution.setVariable("errorMessage", error);
            }
            throw new BpmnError("SystemError");
        } catch (Throwable e) {
            log.error("Exception executing task", e);
            if (hideStackTrace) {
                String errorMessage = e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
                execution.setVariable("errorMessage", errorMessage);
            } else {
                String error = getErrorStack(e);
                execution.setVariable("errorMessage", error);
            }
            throw new BpmnError("SystemError");
        }
    }

    private static String getErrorStack(Throwable e) {
        String error = ExceptionUtils.getStackTrace(e);
        if (error.length() > 4000) {
            error = error.substring(0, 4000);
        }
        return error;
    }

    public static <TResult, TArg> TResult unsafeExecute(DelegateExecution execution, Runner<TResult, TArg> runner) {
        return unsafeExecute(execution, true, runner);
    }

    public static <TResult, TArg> TResult unsafeExecute(DelegateExecution execution, boolean modifying, Runner<TResult, TArg> runner) {
        TArg request = getProcessVar(execution);
        TResult result = runner.execute(request);
        if (modifying) CamundaHelper.setProcessVar(execution, request);
        return result;
    }

    public static <T> T getProcessVar(DelegateExecution execution) {
        Object variable = execution.getVariable(BpmConstants.VARIABLE_REQUEST);
        return (T) variable;
    }

    public static <T> void setProcessVar(DelegateExecution execution, T request) {
        execution.setVariable(BpmConstants.VARIABLE_REQUEST, request);
    }
}
