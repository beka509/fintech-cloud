package kz.fintech.starter.bpm2.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface BpmExternalTaskNew {

    //Навзание topic. По умолчанию используется название метода в initCap
    String topic() default "";

    //Время в миллисекундах при блокировании service task
    long lockDuration() default 30_000;
}
