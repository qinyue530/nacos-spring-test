package com.nacos.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class produceService {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/setService")
    private String setService(){
        return "生产者 添加服务" + serverPort;
    }

    @GetMapping("/setService/aaa")
    private String getService(){
        return "getService" + serverPort;
    }

    @GetMapping("/")
    private String nullService(){
        return "null  " + serverPort;
    }
}
