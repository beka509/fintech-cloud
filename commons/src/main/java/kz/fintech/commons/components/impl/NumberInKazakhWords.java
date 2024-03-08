package kz.fintech.commons.components.impl;

import kz.fintech.commons.components.NumberInWords;
import kz.fintech.commons.models.Currency;
import kz.fintech.commons.models.DecimalNumber;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class NumberInKazakhWords implements NumberInWords {

    private static final String[] ones = {"ноль", "бір", "екі", "үш", "төрт", "бес", "алты", "жеті", "сегіз", "тоғыз"};
    private static final String[] tens = {"", "он", "жиырма", "отыз", "қырық", "елу", "алпыс", "жетпіс", "сексен", "тоқсан"};
    private static final String[] thousands = {"мың", "миллион", "миллиард", "триллион"};
    private static final String[] decimals = {"оннан", "жүзден", "мыңнан", "он мыңнан", "жүз мыңнан", "миллионнан", "он миллионнан"};
    private static final Map<String, Currency> currencies = new HashMap<>();

    static {
        currencies.put("KZT", new Currency("теңге", "тиын"));
        currencies.put("USD", new Currency("АҚШ доллары", "цент"));
        currencies.put("EUR", new Currency("евро", "евроцент"));
        currencies.put("RUB", new Currency("рубль", "копейка"));
    }

    @Override
    public String lang() {
        return "KZ";
    }

    @Override
    public String inWords(double number) {
        DecimalNumber decimal = new DecimalNumber(number);
        return inWords(decimal.integerPart, decimal.fractionalPart, decimal.fractions);
    }

    @Override
    public String inCurrency(double number, String currency) {
        double amount = (double) Math.round(number * 100) / 100;
        DecimalNumber decimal = new DecimalNumber(amount);
        Currency curr = currencies.get(currency);
        return inWords(decimal.integerPart) + " " + curr.integerName
                + (decimal.fractionalPart == 0 ? "" : " "
                + inWords(decimal.fractionalPart * (decimal.fractions == 0 ? 10 : 1))
                + " " + curr.fractionalName);
    }

    private String inWords(long integerPart) {
        return inWords(integerPart, 0, 0);
    }

    private String inWords(long integerPart, long fractionalPart, int fractions) {
        if (fractionalPart == 0) return getThousands(integerPart);
        return getThousands(integerPart) + " бүтін " + decimals[fractions] + " " + getThousands(fractionalPart);
    }

    private static String getHundreds(int value) {
        String result = "";
        if (value == 0) return result;

        int x = value % 10;
        if (x > 0)
            result += ones[x];
        x = (value / 10) % 10;

        result = (result.length() == 0 ? tens[x] : tens[x] + " " + result).trim();

        x = (value / 100) % 10;

        if (x > 0)
            result = (ones[x] + " жүз " + result).trim();
        return result;
    }

    private static String getThousands(long value) {
        if (value == 0) return ones[0];
        return getThousands(value, -1);
    }

    private static String getThousands(long value, int level) {
        String result = "";
        int remainder = (int) (value % 1000);
        if (remainder > 0) {
            result = getHundreds(remainder);

            if (level >= 0) result += " " + thousands[level];
        }
        value /= 1000;

        if (value > 0)
            result = getThousands(value, ++level).trim() + " " + result.trim();

        return result;
    }
}

