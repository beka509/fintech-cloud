package kz.fintech.models;

public interface SmsTemplates {
    String DIGITAL_DOCUMENTS = "%s, вам больше не нужно носить с собой удостоверение личности – добавьте " +
            "его в Apple Wallet или Gpay и используйте в любое время: " +
            "https://1.bankffin.kz/3Tkd6rE";
    String PAYMENT_LINK = "%s, совершайте покупки по INVEST CARD от 50 тысяч тенге и " +
            "участвуйте в розыгрыше ценных бумаг!  Пополните вашу карту онлайн с карты любого банка:  " +
            "%s Подробности акции: https://1.bankffin.kz/promo";
    String REMIND_PAYMENT_LINK_NEW = "%s, пополните INVEST CARD любой картой и получайте вознаграждение на остаток: 3%% в USD, 2,5%% в EUR и 10,5%% в KZT (годовых): %s";
    String SEND_PAYMENT_LINK = "%s, пройдите по этой ссылке и пополните вашу %s онлайн:  %s";
}
