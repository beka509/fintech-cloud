package kz.fintech.models;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Data
public class LocalizedString implements Serializable {

    public static final String KZ = "KZ";
    public static final String RU = "RU";
    public static final String EN = "EN";
    private static final long serialVersionUID = -6798344629133352922L;

    Map<String, String> values = new HashMap<>();

    public LocalizedString add(String locale, String value){
        values.putIfAbsent(locale, value);
        return this;
    }

    public String get(Locale locale) {
        return get(locale.getLanguage());
    }

    public String get(String locale) {
        if (values.containsKey(locale)) return values.get(locale);
        if (values.containsKey(RU)) return values.get(RU);
        if (values.containsKey(KZ)) return values.get(KZ);
        return "";
    }
}
