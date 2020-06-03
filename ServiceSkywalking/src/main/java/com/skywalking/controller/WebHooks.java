package com.skywalking.controller;

import com.skywalking.pojo.AlarmMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WebHooks {
    private List<AlarmMessage> lastList;

    @PostMapping("/webhook")
    public void webhook(@RequestBody List<AlarmMessage> alarmMessageList){
        lastList = alarmMessageList;
    }

    @GetMapping("/show")
    public List<AlarmMessage> show(){
        return lastList;
    }

}
