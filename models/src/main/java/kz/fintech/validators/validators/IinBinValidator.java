package kz.fintech.validators.validators;

import kz.fintech.validators.constraints.IinBin;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class IinBinValidator implements ConstraintValidator<IinBin, String> {

    private static final int[] b1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11},
            b2 = {3, 4, 5, 6, 7, 8, 9, 10, 11, 1, 2};

    @Override
    public boolean isValid(String iinBin, ConstraintValidatorContext cxt) {
        if (StringUtils.isEmpty(iinBin)) return true;
        if (!Pattern.matches("^[0-9]{12}$", iinBin)) return false;
        int[] a = new int[12];
        int checkSum = 0;
        for (int i = 0; i < 12; i++) {
            a[i] = Integer.parseInt(iinBin.charAt(i) + "");
            if (i < 11) checkSum += a[i] * b1[i];
        }
        checkSum = checkSum % 11;
        if (checkSum == 10) {
            checkSum = 0;
            for (int i = 0; i < 11; i++)
                checkSum += a[i] * b2[i];
            checkSum = checkSum % 11;
        }
        return checkSum == a[11];
    }

}