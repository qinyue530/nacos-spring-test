package com.skywalking.controller;

import com.skywalking.pojo.AlarmMessage;
import com.skywalking.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WebHooks {
    private List<AlarmMessage> lastList;

    @Autowired
    private MailService mailService;

    @PostMapping("/webhook")
    public String webhook(@RequestBody List<AlarmMessage> alarmMessageList){
        lastList = alarmMessageList;
        mailService.sendSimpleMailMessge("332578686@qq.com", alarmMessageList.toString(), "Prometheus超时报警");
        return "邮件发送成功";
    }

    @GetMapping("/show")
    public List<AlarmMessage> show(){
        return lastList;
    }

}
