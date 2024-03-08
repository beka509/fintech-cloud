package kz.fintech.starter.bpm.config;

import kz.fintech.starter.bpm.components.Marker;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(BpmProps.class)
@ComponentScan(basePackageClasses = Marker.class)
class BpmAutoConfig {

}
