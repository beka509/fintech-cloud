package kz.fintech.bpm.zni;


import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableProcessApplication
@EnableDiscoveryClient
public class ZniBpmApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZniBpmApplication.class);
    }
}
