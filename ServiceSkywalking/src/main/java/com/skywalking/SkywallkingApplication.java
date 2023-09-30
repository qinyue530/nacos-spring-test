package com.skywalking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SkywallkingApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkywallkingApplication.class);
    }
}
