package kz.fintech.starter.bpm2.utils;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

@FunctionalInterface
public interface RunnerNew<TArg> {
    void execute(TArg request, Map<String, Object> variables, Map<String, Object> localVariables) throws JsonProcessingException;
}