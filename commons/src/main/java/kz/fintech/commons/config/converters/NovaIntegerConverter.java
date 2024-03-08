package kz.fintech.commons.config.converters;

import kz.fintech.exceptions.ConversionException;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

@Component("NovaIntegerConverter")
public class NovaIntegerConverter implements NovaConverter<Integer> {

    @Override
    public Integer convert(String value) {
        if (!StringUtils.hasLength(value)) return null;

        String number = value.replace(',', '.').replace(" ", "");
        try {
            return NumberUtils.parseNumber(number, Integer.class);
        } catch (Exception e) {
            throw new ConversionException("Failed to convert [" + value + "] to integer", e);
        }
    }
}
