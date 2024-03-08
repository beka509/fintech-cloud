package kz.fintech.dbservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.validation.groups.Default;


@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories
@EnableFeignClients(basePackageClasses = Default.class)
public class DbServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DbServiceApplication.class, args);
    }
}