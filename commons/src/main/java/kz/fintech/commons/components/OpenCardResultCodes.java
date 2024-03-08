package kz.fintech.commons.components;

public interface OpenCardResultCodes {
    String STARTED = "STARTED";

    String WAITING_LIVENESS_TASK = "WAITING_LIVENESS";
    String WAITING_AITU_TASK = "WAITING_AITU";
    String INIT = "INIT";
    String LIVENESS = "LIVENESS";
    String AITU = "AITU";
    String INTERNAL_SCREENING = "INTERNAL_SCREENING";
    String EXTERNAL_SCREENING = "EXTERNAL_SCREENING";
    String KISC = "KISC";
    String KISC_DECLINED = "KISC_DECLINED";
    String KISC_ERROR = "KISC_ERROR";
    String GDB_ERROR = "GDB_ERROR";
    String KISC_NOT_FOUND = "KISC_NOT_FOUND";
    String COMPLIANCE = "COMPLIANCE";
    String COMPLIANCE_DECLINED = "COMPLIANCE_DECLINED";
    String COMPLETED = "COMPLETED";
    String NOT_FOUND = "NOT_FOUND";
    String NOT_FOUND_GBD = "NOT_FOUND_GBD";
    String DOCUMENT_INVALID = "DOCUMENT_INVALID";
    String WAITING_END_REQUEST = "WAITING_END_REQUEST";
    String GETTING_BROKER_AGREEMENT = "GETTING_BROKER_AGREEMENT";
    String WAITING_BROKER_AGREEMENT = "WAITING_BROKER_AGREEMENT";
    String SELECT_BROKER_ACCOUNT = "SELECT_BROKER_ACCOUNT";
    String CARD_EXISTS = "CARD_EXISTS";
    String HAS_CARD = "HAS_CARD";
    String HAS_NOT_CARD = "HAS_NOT_CARD";
    String SCORING_REJECT = "SCORING_REJECT";
    String LOANS_NOT_FOUND = "LOANS_NOT_FOUND";
    String BROKER_ACC_NOT_EXIST = "BROKER_ACC_NOT_EXIST";
    String WORLD_BROKER_ACC_NOT_EXIST = "WORLD_BROKER_ACC_NOT_EXIST";
    String CHECK_HAS_CARD = "CHECK_HAS_CARD";
    String CHECK_HAS_BROKER_ACCOUNT = "CHECK_HAS_BROKER_ACCOUNT";
    String SYSTEM_ERROR = "SYSTEM_ERROR";
    String IN_PROCESS = "IN_PROCESS";
    String GBD_IDENTITY_CARD_CODE = "002";
    String GBD_RESIDENCE_CARD_CODE = "003";
    String DELIVERY = "DELIVERY";
    String TIME_OUT = "TIME_OUT";
    String GENERATING_SID = "GENERATING_SID";
    String WAITING_FRAME = "WAITING_FRAME";
    String SCORING2 = "SCORING2";
    String SIGN_DOCS = "SIGN_DOCS";
    String GEN_DOCS = "GEN_DOCS";

    String CLIENT_CARD = "CLIENT_CARD";
    String OPEN_CARD = "OPEN_CARD";
    String IMPORT_ACCOUNT = "IMPORT_ACCOUNT";
    String SIGN_BANK = "SIGN_BANK";
    String CHANGE_PRODUCT = "CHANGE_PRODUCT";
    String GENERATE_DOC = "GENERATE_DOC";

    //response for front
    String INVALID_CITY = "INVALID_CITY";
    String ACCEPTED = "ACCEPTED";
    String ERROR = "ERROR";
    String REFUND = "REFUND";
    String SEND_PP = "SEND_PP";
    String SCORING = "SCORING";
    String INVALID_OTP = "INVALID_OTP";
    String INVALID_PHONE_NUMBER = "INVALID_PHONE_NUMBER";
    String BANNED_IIN = "BANNED_IIN";

    String GREETING_SMS_MULTI = "Спасибо за выбор нашей карты! Счет откроется в течение 5-10 минут. Пока мы открываем ваш " +
            "счёт, прочитайте о возможностях карты https://1.bankffin.kz/fc";
    String GREETING_SMS_INVEST = "Спасибо за выбор нашей карты! Счет откроется в течение 5-10 минут. Пока мы открываем ваш " +
            "счёт, прочитайте о возможностях карты https://1.bankffin.kz/ic";
    String GREETING_SMS_MFO = "Спасибо за выбор нашей карты! Счет откроется в течение 5-10 минут. Пока мы открываем ваш " +
            "счёт, прочитайте о возможностях карты https://bankffin.kz/ru/cards/freepay-card/activation";
    String GREETING_SMS = "Спасибо за выбор нашей карты! Счет откроется в течение 5-10 минут. Пока мы открываем ваш " +
            "счёт, прочитайте о возможностях карты %s";


    String SUCCESS = "SUCCESS";
    String CANCEL = "CANCEL";
    String COLVIR = "COLVIR";

    String IS_CREDIT_LIMIT_VAR = "isCreditLimit";
    String IS_ARRESTED_VAR = "isArrested";
    String IS_OPEN_BAN_VAR = "isOpenBan";
    String IS_BLACK_LIST_VAR = "isBlackList";
    String IS_INACTIVE_TAXPAYER_VAR = "isInactiveTaxpayer";
    String IS_FAKE_ENTREPRENEUR = "isFakeEntrepreneur";
    String IS_AFFILIATED_PERSONS = "isAffiliatedPersons";
    String IS_CUSTOM_SCORING_PASSED = "isCustomScoringPassed";
    String IS_GCVP = "isGcvp";
    String IS_KDN_STE = "isKdnSTE";
    String IS_KDN_CHECK = "isKdnCheck";
    String IS_CALCULATED = "isCalculated";
    String IS_SMS_SENDED = "isSmsSended";
    String WAITING_BANK_PAYMENT = "WAITING_BANK_PAYMENT";


}
