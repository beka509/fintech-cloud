package kz.fintech.commons.config.converters;

import kz.fintech.exceptions.ConversionException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component("NovaLocalDateConverter")
public class NovaLocalDateConverter implements NovaConverter<LocalDate> {

    private final static String[] AVAILABLE_PATTERNS = {"dd.MM.yyyy", "dd/MM/yyyy", "dd MMM yyyy", "yyyy-MM-dd"};

    @Override
    public LocalDate convert(String value) {
        if (!StringUtils.hasLength(value)) return null;

        for (String pattern : AVAILABLE_PATTERNS) {
            try {
                return LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern));
            } catch (Exception ex) {

            }
        }
        throw new ConversionException("Failed to convert [" + value + "] to LocalDate");
    }
}
