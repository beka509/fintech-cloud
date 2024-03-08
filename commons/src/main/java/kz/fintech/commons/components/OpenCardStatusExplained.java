package kz.fintech.commons.components;


import java.util.HashMap;

public class OpenCardStatusExplained {

    public static final HashMap<String, String> requestStatuses = new HashMap<>();
    public static final HashMap<String, String> processStatuses = new HashMap<>();
    public static final HashMap<Integer, String> errorCodeStatuses = new HashMap<>();
    public static final HashMap<Integer, String> deliveryStatuses = new HashMap<>();
    public static final HashMap<Integer, String> deliveryStatusesError = new HashMap<>();


    static {
        requestStatuses.put(OpenCardResultCodes.STARTED, "Запущен");
        requestStatuses.put(OpenCardResultCodes.WAITING_LIVENESS_TASK, "Ожидания прохождения биометрии");
        requestStatuses.put(OpenCardResultCodes.LIVENESS, "Идёт проверка liveness");
        requestStatuses.put(OpenCardResultCodes.INTERNAL_SCREENING, "Проверка во внутренних базах");
        requestStatuses.put(OpenCardResultCodes.EXTERNAL_SCREENING, "Проверка во внешних базах");
        requestStatuses.put(OpenCardResultCodes.KISC_DECLINED, "Фото не совпадает в базе ЦОИД");
        requestStatuses.put(OpenCardResultCodes.KISC_ERROR, "ЦОИД недоступен");
        requestStatuses.put(OpenCardResultCodes.GDB_ERROR, "ГБД ФЛ недоступен");
        requestStatuses.put(OpenCardResultCodes.KISC, "Проверка фото клиента в ЦОИД");
        requestStatuses.put(OpenCardResultCodes.KISC_NOT_FOUND, "Клиент не найден в базе ЦОИД");
        requestStatuses.put(OpenCardResultCodes.COMPLIANCE, "Идёт ручная проверка compliance");
        requestStatuses.put(OpenCardResultCodes.COMPLIANCE_DECLINED, "Отклонено ручной проверкой compliance");
        requestStatuses.put(OpenCardResultCodes.COMPLETED, "Заявка завершена");
        requestStatuses.put(OpenCardResultCodes.NOT_FOUND_GBD, "Клиент не найден в базе ГБД ФЛ");
        requestStatuses.put(OpenCardResultCodes.DOCUMENT_INVALID, "Удостоверение просрочено");
        requestStatuses.put(OpenCardResultCodes.WAITING_END_REQUEST, "Ожидания завершения заявки");
        requestStatuses.put(OpenCardResultCodes.CARD_EXISTS, "Карта уже сущестует");
        requestStatuses.put(OpenCardResultCodes.BROKER_ACC_NOT_EXIST, "Ожидание согласия на открытие брокерского счета");
        requestStatuses.put(OpenCardResultCodes.CHECK_HAS_CARD, "Проверка на наличие карты");
        requestStatuses.put(OpenCardResultCodes.CHECK_HAS_BROKER_ACCOUNT, "Проверка на наличие брокерского счета");
        requestStatuses.put(OpenCardResultCodes.SYSTEM_ERROR, "Техническая ошибка");
        requestStatuses.put(OpenCardResultCodes.TIME_OUT, "Время заявки истекло");
        requestStatuses.put("ACCOUNT_BAN", "Запрет на открытие счетов/карт");
        requestStatuses.put("HAS_DEBTS", "Имеются налоговые задолженности");
        requestStatuses.put("HAS_ARRESTS", "Имеются Аресты");
        requestStatuses.put("FAKE_ENTREPRENEUR", "Является лжепредпринимателем");
        requestStatuses.put("INACTIVE_TAXPAYER", "Является бездействующим налогоплательщиком");
        requestStatuses.put(OpenCardResultCodes.WAITING_FRAME, "Ожидание прохождения фрейма tradernet'а");
        requestStatuses.put(OpenCardResultCodes.GENERATING_SID, "Генерация ссылки на фрейм tradernet'a");

        requestStatuses.put(OpenCardResultCodes.OPEN_CARD, "Выпуск карты");
        requestStatuses.put(OpenCardResultCodes.CLIENT_CARD, "Создание/обновление карточки клиента");

        processStatuses.put(OpenCardResultCodes.ERROR, "Отклонено/Ошибка");
        processStatuses.put(OpenCardResultCodes.SCORING, "Проверка клиента");
        processStatuses.put(OpenCardResultCodes.IN_PROCESS, "В обработке");
        processStatuses.put(OpenCardResultCodes.SUCCESS, "Успешно завершено");
        processStatuses.put(OpenCardResultCodes.WAITING_BANK_PAYMENT, "В ожидании автосальдирование");
    }
}
