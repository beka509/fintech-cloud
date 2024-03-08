package kz.fintech.commons.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {

    @Bean
    public feign.Logger.Level level(){
        return Logger.Level.FULL;
    }
}
