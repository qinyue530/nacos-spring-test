package com.skywalking.controller;

import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PluginController {
    @GetMapping("/getTrsceId")
    public String getTrsceId(){
        //使当前链路报错，并且提示报错信息（skywalking可视化界面中查看）
        ActiveSpan.error(new RuntimeException("test-error"));
        //打印info信息（skywalking可视化界面中查看）
        ActiveSpan.info("test - info");
        //打印debug信息（不skywalking可视化界面中查看）
        ActiveSpan.debug("test - debug");
        return TraceContext.traceId();

    }
    @GetMapping("/getTrsceIdAAAAAA")
    public String getTrsceIdAAAAAA(){
        //使当前链路报错，并且提示报错信息（skywalking可视化界面中查看）
        ActiveSpan.error(new RuntimeException("test-error"));
        //打印info信息（skywalking可视化界面中查看）
        ActiveSpan.info("test - info");
        //打印debug信息（不skywalking可视化界面中查看）
        ActiveSpan.debug("test - debug");
        return TraceContext.traceId();

    }
    @GetMapping("/timeout")
    public String timeout(){
        try{
            Thread.sleep(1500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return "timeout";
    }
}
