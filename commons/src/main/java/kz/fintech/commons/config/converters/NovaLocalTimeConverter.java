package kz.fintech.commons.config.converters;

import kz.fintech.exceptions.ConversionException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Component("NovaLocalTimeConverter")
public class NovaLocalTimeConverter implements NovaConverter<LocalTime> {

    private final static String[] TIME_PATTERNS = {"HH:mm", "HH:mm:ss", "HH:mm:ss.sTZD"};

    @Override
    public LocalTime convert(String value) {
        if (!StringUtils.hasLength(value)) return null;

        for (String timePattern : TIME_PATTERNS) {
            try {
                return LocalTime.parse(value, DateTimeFormatter.ofPattern(timePattern));
            } catch (Exception ignored) {

            }
        }
        throw new ConversionException("Failed to convert [" + value + "] to LocalTime");
    }
}
