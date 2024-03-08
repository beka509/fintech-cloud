package kz.fintech.validators.validators;

import kz.fintech.validators.constraints.KzIban;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class KzIbanValidator implements ConstraintValidator<KzIban, String> {

    private static final Map<Character, Integer> numbers;

    static {
        char c;
        int i;
        Map<Character, Integer> temp = new HashMap<>();
        for (c = '0', i = 0; c <= '9'; c++, i++) {
            temp.put(c, i);
        }

        for (c = 'A', i = 10; c <= 'Z'; c++, i++) {
            temp.put(c, i);
        }

        for (c = 'a', i = 10; c <= 'z'; c++, i++) {
            temp.put(c, i);
        }
        numbers = Collections.unmodifiableMap(temp);
    }

    //https://en.wikipedia.org/wiki/International_Bank_Account_Number#Modulo_operation_on_IBAN
    //Validating the IBAN
    //An IBAN is validated by converting it into an integer and performing a basic mod-97 operation (as described in ISO 7064) on it. If the IBAN is valid, the remainder equals 1.[Note 1] The algorithm of IBAN validation is as follows:[8]
    //
    //Check that the total IBAN length is correct as per the country. If not, the IBAN is invalid
    //Move the four initial characters to the end of the string
    //Replace each letter in the string with two digits, thereby expanding the string, where A = 10, B = 11, ..., Z = 35
    //Interpret the string as a decimal integer and compute the remainder of that number on division by 97
    //If the remainder is 1, the check digit test is passed and the IBAN might be valid.
    //
    //Example (fictitious United Kingdom bank, sort code 12-34-56, account number 98765432):
    //
    //• IBAN:		GB82 WEST 1234 5698 7654 32
    //• Rearrange:		W E S T12345698765432 G B82
    //• Convert to integer:		3214282912345698765432161182
    //• Compute remainder:		3214282912345698765432161182	mod 97 = 1

    @Override
    public boolean isValid(String iban, ConstraintValidatorContext cxt) {
        if (StringUtils.isEmpty(iban)) return true;
        if (!Pattern.matches("^KZ[A-Za-z0-9]{18}$", iban)) return false;
        BigInteger number = BigInteger.ZERO;
        String check = iban.substring(4) + iban.substring(0, 4);
        for (char c : check.toCharArray()) {
            int digit = numbers.get(c);
            number = number
                    .multiply(digit < 10 ? BigInteger.valueOf(10L) : BigInteger.valueOf(100L))
                    .add(BigInteger.valueOf(digit));
        }
        return number.mod(BigInteger.valueOf(97L)).equals(BigInteger.ONE);
    }
}