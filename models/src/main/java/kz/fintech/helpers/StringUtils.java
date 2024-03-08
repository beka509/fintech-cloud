package kz.fintech.helpers;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import lombok.SneakyThrows;
import lombok.val;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class StringUtils {

    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int CHARS_LENGTH = CHARS.length();
    private static final Map<String, String> kopf = new LinkedHashMap<>();

    private static String EMAIL_REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*"
            + "@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static final String SPACE = "&nbsp;";
    public static final int PHONE_NUMBER_LENGTH = 10;
    public static final String PHONE_PREFIX = "7";
    public static final int IIN_BIN_LENGTH = 12;

    static {
        putKopf("РЕСПУБЛИКАНСК% ГОСУДАРСТВЕНН% ПРЕДПРИЯТ% НА% ПРАВ% ХОЗЯЙСТВЕНН% ВЕДЕН__", "РГП на ПХВ");
        putKopf("РЕСПУБЛИКАНСК% ГОСУДАРСТВЕНН% УЧРЕЖДЕН__", "РГУ");
        putKopf("КОММУНАЛЬН% ГОСУДАРСТВЕНН% УЧРЕЖДЕНИ_", "КГУ");
        putKopf("РЕСПУБЛИКАНСК% ГОСУДАРСТВЕНН% КАЗЕНН% ПРЕДПРИЯТ__", "РГКП");
        putKopf("КОМИТЕТ% ПО% ДЕЛАМ% СПОРТ% И% ФИЗИЧЕСК% КУЛЬТУР% МИНИСТЕРСТВ% КУЛЬТУР% И% СПОРТ% РЕСПУБЛИК% КАЗАХСТАН", "МКиС РК");
        putKopf("РЕСПУБЛИКАНСК% ГОСУДАРСТВЕНН% ПРЕДПРИЯТ__", "РГП");
        putKopf("УПРАВЛЕН% ДЕЛАМ% ПРЕЗИДЕНТ% РЕСПУБЛИК% КАЗАХСТАН", "УПР.ДЕЛ. Президента РК");
        putKopf("НА% ПРАВ% ХОЗЯЙСТВЕНН% ВЕДЕН__", "НА ПХВ");
        putKopf("РЕСПУБЛИКАНСК% ГОСУДАРСТВЕНН% ПРЕДПРИЯТ__", "РГП");
        putKopf("ЖИЛИЩН% КОММУНАЛЬН% ХОЗЯЙСТВ_", "ЖКХ");
        putKopf("ОБЩЕСТВЕНН% ОБЪЕДИНЕН__", "ОО");
        putKopf("АКЦИОНЕРН% ОБЩЕСТВ_", "АО");
        putKopf("ТОВАРИЩЕСТВ% С% ОГРАНИЧЕНН% ОТВЕТСТВЕННОСТ__", "ТОО");
        putKopf("ГОСУДАРСТВЕНН% УЧРЕЖДЕНИ_", "ГУ");
        putKopf("ЧАСТН% УЧРЕЖДЕНИ_", "ЧУ");
        putKopf("ГОРОД_____", "г. ");
        putKopf("ОБЛАСТ___", "обл. ");
    }

    private static void putKopf(String longName, String shortName) {
        String longNameUpper = longName.toUpperCase();
        String longNameLower = longName.toLowerCase();
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < longName.length(); i++) {
            if (longName.charAt(i) == '%') {
                key.append("\\S*");
            } else if (longName.charAt(i) == '_') {
                key.append("\\S?");
            } else {
                key.append("(").append(longNameLower.charAt(i)).append("|").append(longNameUpper.charAt(i)).append(")");
            }
        }
        kopf.put(key.toString(), shortName);
    }

    public static double calculateDistanceInPercent(String x, String y) {
        double distance = calculateDistance(x, y);
        return 1 - distance / Math.max(x.length(), y.length());
    }

    public static int calculateDistance(String x, String y) {
        int[][] dp = new int[x.length() + 1][y.length() + 1];

        for (int i = 0; i <= x.length(); i++) {
            for (int j = 0; j <= y.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    dp[i][j] = min(dp[i - 1][j - 1]
                                    + costOfSubstitution(x.charAt(i - 1), y.charAt(j - 1)),
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1);
                }
            }
        }

        return dp[x.length()][y.length()];
    }

    public static String minimizeCustomerName(String name) {
        if (name == null) return null;
        for (Map.Entry<String, String> entry : kopf.entrySet()) {
            name = name.replaceAll(entry.getKey(), entry.getValue());
        }
        return name.trim();
    }

    private static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    private static int min(int... numbers) {
        return Arrays.stream(numbers)
                .min().orElse(Integer.MAX_VALUE);
    }

    @SneakyThrows
    public static String mustacheTransform(String template, Map<String, Object> data) {
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache m = mf.compile(new StringReader(template), "");
        try (StringWriter writer = new StringWriter()) {
            m.execute(writer, data).flush();
            return writer.toString();
        }
    }

    public static String substr(String string, int length) {
        if (string == null) return null;
        return string.substring(0, Math.min(length, string.length()));
    }

    public static String upperLike(String text) {
        return text != null && !text.isEmpty() ? "%" + text.trim().toUpperCase() + "%" : null;
    }

    public static boolean stringsEqual(String string1, String string2) {
        if (string1 == null && string2 != null) return false;
        if (string1 != null && string2 == null) return false;
        if (string1 == null) return false;
        return string1.equals(string2);
    }

    public static String trimPhoneNumber(final String phoneNumber) {
        if (phoneNumber == null) return "";
        String phone = phoneNumber.replaceAll("[^\\d]", "");
        if (phone.length() > PHONE_NUMBER_LENGTH) {
            phone = phone.substring(phone.length() - PHONE_NUMBER_LENGTH);
        }
        return phone;
    }

    public static String removeSpaces(final String s) {
        return s.replaceAll("[\\s]", "");
    }

    public static String trimIinBin(final String iinBin) {
        if (iinBin == null) return "";
        return iinBin.replaceAll("[^\\d]", "");
    }

    public static boolean isBin(String iinBin) {
        return trimIinBin(iinBin).length() == 12 && iinBin.charAt(4) > '3';
    }

    public static String generateUniqueUrlId(int length) {
        val result = new StringBuilder();
        val random = new Random();
        for (int i = 0; i < length; i++) {
            result.append(CHARS.charAt(random.nextInt(CHARS_LENGTH)));
        }
        return result.toString();
    }

    public static boolean emailValidator(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
}
