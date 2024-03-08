package kz.fintech.bpm.zni.conf;


import kz.fintech.bpm.zni.task.ZniServiceTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ZniServiceTask zniServiceTask(){
        return new ZniServiceTask();
    }
}
