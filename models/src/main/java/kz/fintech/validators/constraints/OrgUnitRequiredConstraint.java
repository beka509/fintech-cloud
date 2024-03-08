package kz.fintech.validators.constraints;

import kz.fintech.validators.validators.OrgUnitRequiredValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OrgUnitRequiredValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface OrgUnitRequiredConstraint {
    String message() default "{javax.validation.constraints.NotEmpty.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}