package kz.fintech.commons.config.converters;

import kz.fintech.exceptions.ConversionException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component("NovaLocalDateTimeConverter")
public class NovaLocalDateTimeConverter implements NovaConverter<LocalDateTime> {

    private final static String[] DATE_PATTERNS = {"dd.MM.yyyy", "dd/MM/yyyy", "dd MMM yyyy", "yyyy-MM-dd"};
    private final static String[] SEPARATOR = {" ", "T"};
    private final static String[] TIME_PATTERNS = {"HH:mm", "HH:mm:ss", "HH:mm:ss.sTZD"};

    @Override
    public LocalDateTime convert(String value) {
        if (!StringUtils.hasLength(value)) return null;

        for (String datePattern : DATE_PATTERNS) {
            for (String timePattern : TIME_PATTERNS) {
                for (String separator : SEPARATOR) {
                    try {
                        return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(datePattern + separator + timePattern));
                    } catch (Exception ex) {

                    }
                }
            }
        }
        throw new ConversionException("Failed to convert [" + value + "] to LocalDateTime");
    }
}
