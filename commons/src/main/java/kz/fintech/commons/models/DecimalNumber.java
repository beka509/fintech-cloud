package kz.fintech.commons.models;

import java.math.BigDecimal;

public class DecimalNumber {

    public final long integerPart;
    public final long fractionalPart;
    public final int fractions;

    public DecimalNumber(double number) {
        this.integerPart = (long) number;
        String fractionalPart = new BigDecimal(String.valueOf(number)).subtract(new BigDecimal(integerPart)).toPlainString();
        fractionalPart = fractionalPart.indexOf('.') > 0 ? fractionalPart.substring(fractionalPart.indexOf(".") + 1) : "0";
        this.fractionalPart = Long.parseLong(fractionalPart);
        this.fractions = fractionalPart.length() - 1;
    }
}
