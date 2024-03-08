package kz.fintech.validators.validators;

import kz.fintech.validators.constraints.KzMobilePhone;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class KzMobilePhoneValidator implements ConstraintValidator<KzMobilePhone, String> {

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext cxt) {
        if (StringUtils.isEmpty(phoneNumber)) return true;
        return Pattern.matches("^7[04567]{1}[0-9]{8}$", phoneNumber);
    }
}