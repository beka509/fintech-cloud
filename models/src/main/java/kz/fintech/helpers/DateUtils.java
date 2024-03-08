package kz.fintech.helpers;

import lombok.SneakyThrows;

import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public final class DateUtils {
    public interface Format {
        String DATE = "yyyy-MM-dd";
        String DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss";
        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE);
        DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME);
    }

    public static DateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    public static DateTimeFormatter LOCAL_DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static DateTimeFormatter LOCAL_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public static ZoneId ZONE = ZoneId.of("UTC+6");

    static {
        DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("GMT+06:00"));
    }

    public static String date2str(LocalDateTime date) {
        return date2str(date, "");
    }

    public static String date2str(LocalDate date) {
        return date2str(date, "");
    }

    public static String date2str(Date date) {
        return date2str(date, "");
    }

    public static String date2str(LocalDateTime date, String dateFormat) {
        if (date == null) return "";
        DateTimeFormatter df = dateFormat == null || "".equals(dateFormat) ? LOCAL_DATE_TIME_FORMAT : DateTimeFormatter.ofPattern(dateFormat);
        return date.format(df);
    }

    public static String date2str(LocalDate date, String dateFormat) {
        if (date == null) return "";
        DateTimeFormatter df = dateFormat == null || "".equals(dateFormat) ? LOCAL_DATE_FORMAT : DateTimeFormatter.ofPattern(dateFormat);
        return date.format(df);
    }

    public static String date2str(Date date, String dateFormat) {
        if (date == null) return "";
        DateFormat df = dateFormat == null || "".equals(dateFormat) ? DATE_FORMAT : new SimpleDateFormat(dateFormat);
        return df.format(date);
    }

    public static Date str2date(String string) {
        return str2date(string, "");
    }

    @SneakyThrows
    public static Date str2date(String string, String dateFormat) {
        DateFormat df = dateFormat == null || "".equals(dateFormat) ? DATE_FORMAT : new SimpleDateFormat(dateFormat);
        return df.parse(string);
    }

    public static Date firstDayOfMonth() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    public static int age(Date birthDate) {
        return yearsBetween(Calendar.getInstance().getTime(), birthDate);
    }

    public static int yearsBetween(Date date1, Date date2) {
        if (date1 == null || date2 == null) return -1;
        LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return (int) Math.abs(ChronoUnit.YEARS.between(localDate1, localDate2));
    }

    public static int monthsBetween(Date date1, Date date2) {
        if (date1 == null || date2 == null) return -1;
        LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return (int) Math.abs(ChronoUnit.MONTHS.between(localDate1, localDate2));
    }

    public static int daysBetween(Date date1, Date date2) {
        if (date1 == null || date2 == null) return -1;
        LocalDate localDate1 = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = date2.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return (int) Math.abs(ChronoUnit.DAYS.between(localDate1, localDate2));
    }

    public static LocalDateTime dateToLocalDate(Date date) {
        if (date == null) return null;
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime xgcToDate(XMLGregorianCalendar xgc) {
        return xgcToDate(xgc, ZoneId.systemDefault());
    }

    public static LocalDateTime xgcToDate(XMLGregorianCalendar xgc, ZoneId zoneId) {
        if (xgc == null) return null;
        ZonedDateTime utcZoned = xgc.toGregorianCalendar().toZonedDateTime().withZoneSameInstant(zoneId);
        return utcZoned.toLocalDateTime();
    }

    public static Date localDateToDate(LocalDateTime date) {
        if (date == null) return null;
        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }

    @SneakyThrows
    public static XMLGregorianCalendar dateToXgc(LocalDate date) {
        return DatatypeFactory.newInstance()
                .newXMLGregorianCalendarDate(date.getYear(), date.getMonthValue(), date.getDayOfMonth(),
                        DatatypeConstants.FIELD_UNDEFINED);
    }

    @SneakyThrows
    public static XMLGregorianCalendar dateToXgc(LocalDateTime time) {
        GregorianCalendar gcal = GregorianCalendar.from(time.atZone(ZoneId.of("UTC+0")));
        XMLGregorianCalendar cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
        cal.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
        return cal;
    }
}
