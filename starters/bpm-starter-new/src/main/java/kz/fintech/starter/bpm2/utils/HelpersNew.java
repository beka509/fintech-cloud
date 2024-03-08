package kz.fintech.starter.bpm2.utils;

import kz.fintech.starter.bpm2.config.BpmPropsNew;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Optional;

public abstract class HelpersNew {
    public static String getTopicName(Method method) {
        return StringUtils.capitalize(method.getName());
    }

    public static int maxTasks(BpmPropsNew props, String processDefinitionKey) {
        int maxTasks = Optional.of(props)
                .map(BpmPropsNew::getProcesses)
                .map(map -> map.get(processDefinitionKey))
                .map(BpmPropsNew.ProcessProps::getMaxTasks)
                .orElse(props.getMaxTasks());
        if (maxTasks <= 0) maxTasks = 10;
        return maxTasks;
    }

    public static int maxThreads(BpmPropsNew props, String processDefinitionKey) {
        int maxThreads = Optional.of(props)
                .map(BpmPropsNew::getProcesses)
                .map(map -> map.get(processDefinitionKey))
                .map(BpmPropsNew.ProcessProps::getMaxThreads)
                .orElse(props.getMaxThreads());
        if (maxThreads <= 0) maxThreads = 5;
        return maxThreads;
    }
}
