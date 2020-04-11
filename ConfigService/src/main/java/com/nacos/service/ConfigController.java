package com.nacos.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
@RefreshScope
public class ConfigController {
    public static void main(String[] args) {
        SpringApplication.run(ConfigController.class);
    }
    @Value("${common.name}")
    private String name;

    @RequestMapping("/getName")
    public String getName(){
        return name;
    }
}
