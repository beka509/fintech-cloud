package kz.fintech.commons.config.converters;

import kz.fintech.exceptions.ConversionException;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

@Component("NovaDoubleConverter")
public class NovaDoubleConverter implements NovaConverter<Double> {

    @Override
    public Double convert(String value) {
        if (!StringUtils.hasLength(value)) return null;

        String number = value.replace(',', '.').replace(" ", "");
        try {
            return NumberUtils.parseNumber(number, Double.class);
        } catch (Exception e) {
            throw new ConversionException("Failed to convert [" + value + "] to double", e);
        }
    }
}
