package kz.fintech.commons.models;

public class Currency {

    public final String integerName;
    public final String fractionalName;

    public Currency(String integerName, String fractionalName) {
        this.integerName = integerName;
        this.fractionalName = fractionalName;
    }
}
