package kz.fintech.commons.components.impl;

import kz.fintech.commons.components.NumberInWords;
import kz.fintech.commons.models.Currency;
import kz.fintech.commons.models.DecimalNumber;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class NumberInRussianWords implements NumberInWords {

    private final static String[] ones = {"", "од", "дв", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"};
    private final static String[] teens = {"десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};
    private final static String[] tens = {"", "", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
    private final static String[] hundreds = {"", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};
    private final static String[] thousands = {"тысяч", "миллион", "миллиард", "триллион"};
    private final static String[] decimals = {"десятых", "сотых", "тысячных", "миллионных", "миллиардных"};

    private final static Map<Form, List<String>> onesForms = new HashMap<>();
    private final static Map<Suffix, List<String>> thousandsSuffixes = new HashMap<>();

    private static final Map<String, Map<Suffix, Currency>> currencies = new HashMap<>();

    static {

        thousandsSuffixes.put(Suffix.SINGLE, Arrays.asList("а", "", "", "", "", "", ""));
        thousandsSuffixes.put(Suffix.TWO_TO_FOUR, Arrays.asList("и", "а", "а", "а", "а", "а"));
        thousandsSuffixes.put(Suffix.PLURAL, Arrays.asList("", "ов", "ов", "ов", "ов", "ов"));

        onesForms.put(Form.MASCULINE, Arrays.asList("", "ин", "а", "", "", "", "", "", "", ""));
        onesForms.put(Form.FEMININE, Arrays.asList("", "на", "е", "", "", "", "", "", "", ""));

        Map<Suffix, Currency> kzt = new HashMap<>();
        kzt.put(Suffix.SINGLE, new Currency("тенге", "тиын"));
        kzt.put(Suffix.TWO_TO_FOUR, new Currency("тенге", "тиын"));
        kzt.put(Suffix.PLURAL, new Currency("тенге", "тиын"));
        currencies.put("KZT", kzt);

        Map<Suffix, Currency> usd = new HashMap<>();
        usd.put(Suffix.SINGLE, new Currency("доллар США", "цент"));
        usd.put(Suffix.TWO_TO_FOUR, new Currency("доллара США", "цента"));
        usd.put(Suffix.PLURAL, new Currency("долларов США", "центов"));
        currencies.put("USD", usd);

        Map<Suffix, Currency> eur = new HashMap<>();
        eur.put(Suffix.SINGLE, new Currency("евро", "евроцент"));
        eur.put(Suffix.TWO_TO_FOUR, new Currency("евро", "евроцента"));
        eur.put(Suffix.PLURAL, new Currency("евро", "евроцентов"));
        currencies.put("EUR", eur);

        Map<Suffix, Currency> rub = new HashMap<>();
        rub.put(Suffix.SINGLE, new Currency("рубль", "копейка"));
        rub.put(Suffix.TWO_TO_FOUR, new Currency("рубля", "копейки"));
        rub.put(Suffix.PLURAL, new Currency("рублей", "копеек"));
        currencies.put("RUB", rub);
    }

    @Override
    public String lang() {
        return "RU";
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
        Map<Suffix, Currency> curr = currencies.get(currency);
        Suffix integerPartSuffix = determineSuffix(decimal.integerPart);
        long fractionalPart = decimal.fractionalPart * (decimal.fractions == 0 ? 10 : 1);
        Suffix fractionalPartSuffix = determineSuffix(fractionalPart);

        return inWords(decimal.integerPart).trim() + " " + curr.get(integerPartSuffix).integerName
                + (fractionalPart == 0 ? "" : " " + inWords(fractionalPart).trim()
                + " " + curr.get(fractionalPartSuffix).fractionalName);
    }

    private String inWords(long integerPart) {
        return inWords(integerPart, 0, 0);
    }

    private String inWords(long integerPart, long fractionalPart, int fractions) {
        return fractionalPart == 0 ? getThousands(integerPart)
                : getThousands(integerPart) + " целых " + getThousands(fractionalPart) + " " + decimals[fractions];
    }

    private static Suffix determineSuffix(long value) {
        int ten = (int) (value % 100);
        int one = (int) (value % 10);
        if (ten >= 10 && ten <= 19) {
            return Suffix.PLURAL;
        } else {
            if (one == 1) return Suffix.SINGLE;
            else if (one >= 2 && one <= 4) return Suffix.TWO_TO_FOUR;
            else return Suffix.PLURAL;
        }
    }

    private static Form getThousandsForm(int thousands) {
        if (thousands == 0) return Form.FEMININE;
        else return Form.MASCULINE;
    }

    private static String getTens(int value, Form form) {
        String result;
        int ten = value % 100;
        int one = value % 10;
        if (ten < 10) {
            result = ones[one] + onesForms.get(form).get(one);
        } else if (ten <= 19) {
            result = teens[ten % 10];
        } else {
            ten /= 10;
            result = tens[ten] + " " + ones[one] + onesForms.get(form).get(one);
        }
        return result;
    }

    private static String getHundreds(int value, Form form) {
        if (value == 0) return "";
        int hundred = (value % 1000) / 100;
        return hundreds[hundred] + " " + getTens(value, form);
    }

    private static String getThousands(long value) {
        if (value == 0) return "ноль";
        return getThousands(value, -1);
    }

    private static String getThousands(long value, int level) {
        String result = "";
        int remainder = (int) (value % 1000);
        if (remainder > 0) {
            result = getHundreds(remainder, getThousandsForm(level)).trim();
            if (level >= 0) {
                Suffix suffix = determineSuffix(remainder);
                result += " " + thousands[level] + thousandsSuffixes.get(suffix).get(level);
            }
        }
        value /= 1000;

        if (value > 0)
            result = getThousands(value, ++level).trim() + " " + result.trim();

        return result;
    }

    enum Suffix {
        SINGLE,
        TWO_TO_FOUR,
        PLURAL
    }

    enum Form {
        MASCULINE,
        FEMININE
    }
}
