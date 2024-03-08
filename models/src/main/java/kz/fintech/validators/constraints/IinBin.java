package kz.fintech.validators.constraints;

import kz.fintech.validators.validators.IinBinValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IinBinValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IinBin {
    String message() default "Bad IIN/BIN";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
