package kz.fintech.validators.constraints;

import kz.fintech.validators.validators.KzIbanValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = KzIbanValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface KzIban {
    String message() default "Bad IBAN";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
