package com.skywalking.controller;

import com.skywalking.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Authenticator;
import java.util.Properties;

@RestController
public class SendMail {

    @Autowired
    private MailService mailService;

    private static final String TO = "xxxxxx@qq.com";
    private static final String SUBJECT = "测试邮件";
    private static final String CONTENT = "test content";

    /**
     * 测试发送普通邮件
     */
    @GetMapping("/sendMail")
    public String sendSimpleMailMessage() {
        mailService.sendSimpleMailMessge(TO, SUBJECT, CONTENT);
        return "邮件发送成功";
    }

}