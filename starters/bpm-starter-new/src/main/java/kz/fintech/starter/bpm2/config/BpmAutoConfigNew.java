package kz.fintech.starter.bpm2.config;

import kz.fintech.starter.bpm2.components.MarkerNew;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(BpmPropsNew.class)
@ComponentScan(basePackageClasses = MarkerNew.class)
class BpmAutoConfigNew {

}
