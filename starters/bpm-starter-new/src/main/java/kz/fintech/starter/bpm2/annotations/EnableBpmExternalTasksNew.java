package kz.fintech.starter.bpm2.annotations;

import kz.fintech.starter.bpm2.config.BpmExternalTaskRegistrarNew;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(BpmExternalTaskRegistrarNew.class)
public @interface EnableBpmExternalTasksNew {

    @AliasFor("basePackageClasses")
    Class<?>[] value() default {};

    @AliasFor("value")
    Class<?>[] basePackageClasses() default {};

    String[] basePackages() default {};
}
