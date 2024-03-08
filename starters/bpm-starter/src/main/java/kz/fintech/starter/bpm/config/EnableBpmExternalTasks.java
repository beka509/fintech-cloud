package kz.fintech.starter.bpm.config;

import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(BpmExternalTaskRegistrar.class)
public @interface EnableBpmExternalTasks {

    @AliasFor("basePackageClasses")
    Class<?>[] value() default {};

    @AliasFor("value")
    Class<?>[] basePackageClasses() default {};

    String[] basePackages() default {};
}
