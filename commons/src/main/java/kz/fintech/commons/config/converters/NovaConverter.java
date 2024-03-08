package kz.fintech.commons.config.converters;

public interface NovaConverter<T> {

    T convert(String value);
}
