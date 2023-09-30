package com.nacos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.logging.log4j2.Log4J2LoggingSystem;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import sun.rmi.runtime.Log;

@SpringBootApplication
@EnableFeignClients
public class ProduceApp {
    public static void main(String[] args) {
        SpringApplication.run(ProduceApp.class);
    }
}
