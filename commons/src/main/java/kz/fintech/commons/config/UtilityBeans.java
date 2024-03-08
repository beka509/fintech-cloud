package kz.fintech.commons.config;

import kz.fintech.commons.components.NumberInWords;
import kz.fintech.commons.components.impl.NumberInKazakhWords;
import kz.fintech.commons.components.impl.NumberInRussianWords;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UtilityBeans {

    @Bean
    public NumberInWords numberInKazakhWords(){
        return new NumberInKazakhWords();
    }

    @Bean
    public NumberInWords numberInRussianWords(){
        return new NumberInRussianWords();
    }

}
