package kz.fintech.commons.components;

public interface NumberInWords {

    String lang();

    String inWords(double number);

    String inCurrency(double number, String currency);
}
