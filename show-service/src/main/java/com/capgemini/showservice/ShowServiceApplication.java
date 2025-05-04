package com.capgemini.showservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ShowServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShowServiceApplication.class, args);
    }

}
