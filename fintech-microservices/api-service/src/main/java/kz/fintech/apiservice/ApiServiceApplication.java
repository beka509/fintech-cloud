package kz.fintech.apiservice;

import kz.fintech.commons.feignclients.CallServiceClient;
import kz.fintech.commons.feignclients.FileServiceClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.validation.groups.Default;


@SpringBootApplication
@EnableFeignClients(basePackageClasses = {Default.class, FileServiceClient.class, CallServiceClient.class})
@EnableAspectJAutoProxy
public class ApiServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiServiceApplication.class, args);
    }
}