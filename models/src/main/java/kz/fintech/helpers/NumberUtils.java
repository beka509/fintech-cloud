package kz.fintech.helpers;

public abstract class NumberUtils {

    public static double roundMoney(double amount) {
        return Math.round(amount * 100d) / 100d;
    }

    public static double floorMoney(double amount) {
        return Math.floor(amount * 100d) / 100d;
    }
}
