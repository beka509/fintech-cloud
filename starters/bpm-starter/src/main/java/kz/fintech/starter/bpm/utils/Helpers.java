package kz.fintech.starter.bpm.utils;

import kz.fintech.starter.bpm.config.BpmProps;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Optional;

public abstract class Helpers {
    public static String getTopicName(Method method) {
        return StringUtils.capitalize(method.getName());
    }

    public static int maxTasks(BpmProps props, String processDefinitionKey) {
        int maxTasks = Optional.of(props)
                .map(BpmProps::getProcesses)
                .map(map -> map.get(processDefinitionKey))
                .map(BpmProps.ProcessProps::getMaxTasks)
                .orElse(props.getMaxTasks());
        if (maxTasks <= 0) maxTasks = 10;
        return maxTasks;
    }

    public static int maxThreads(BpmProps props, String processDefinitionKey) {
        int maxThreads = Optional.of(props)
                .map(BpmProps::getProcesses)
                .map(map -> map.get(processDefinitionKey))
                .map(BpmProps.ProcessProps::getMaxThreads)
                .orElse(props.getMaxThreads());
        if (maxThreads <= 0) maxThreads = 5;
        return maxThreads;
    }
}
