package com.nacos.service;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class produceService {

    @GetMapping("/setService")
    private String setService(){
        return "生产者 添加服务";
    }

}
