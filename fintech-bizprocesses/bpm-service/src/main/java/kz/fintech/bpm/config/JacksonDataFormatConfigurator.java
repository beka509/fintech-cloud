package kz.fintech.bpm.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.camunda.spin.impl.json.jackson.format.JacksonJsonDataFormat;
import org.camunda.spin.spi.DataFormatConfigurator;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class JacksonDataFormatConfigurator implements DataFormatConfigurator<JacksonJsonDataFormat> {

    public void configure(JacksonJsonDataFormat dataFormat) {
        //org.camunda.spin.impl.json.jackson.format.JacksonJsonDataFormat
        ObjectMapper objectMapper = dataFormat.getObjectMapper();
//        SimpleModule module = new SimpleModule();
//        module.addDeserializer(Serializable.class, new MoneyJsonDeserializer());
//        module.addSerializer(Serializable.class, new MoneyJsonSerializer());
//        objectMapper.registerModule(module);
    }

    public Class<JacksonJsonDataFormat> getDataFormatClass() {
        return JacksonJsonDataFormat.class;
    }

}