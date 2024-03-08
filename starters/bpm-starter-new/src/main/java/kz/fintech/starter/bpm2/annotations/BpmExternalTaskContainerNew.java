package kz.fintech.starter.bpm2.annotations;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

//Компонента для регистрации слушателей external service task
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface BpmExternalTaskContainerNew {

    //Название процесса в Camunda (ProcessDefinitionKey)
    String process();
}

