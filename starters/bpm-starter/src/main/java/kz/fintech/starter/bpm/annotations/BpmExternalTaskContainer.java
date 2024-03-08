package kz.fintech.starter.bpm.annotations;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

//Компонента для регистрации слушателей external service task
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface BpmExternalTaskContainer {

    //Название процесса в Camunda (ProcessDefinitionKey)
    String process();
}

