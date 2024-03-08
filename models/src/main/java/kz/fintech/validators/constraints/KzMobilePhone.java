package kz.fintech.validators.constraints;

import kz.fintech.validators.validators.KzMobilePhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = KzMobilePhoneValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface KzMobilePhone {
    String message() default "Bad phone number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
