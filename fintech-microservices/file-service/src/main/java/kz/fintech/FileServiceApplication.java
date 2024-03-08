package kz.fintech;

import kz.fintech.commons.feignclients.DbServiceClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.validation.groups.Default;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories
@EnableFeignClients(basePackageClasses = {Default.class, DbServiceClient.class})
public class FileServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FileServiceApplication.class);
    }
}