package com.skywalking.controller;

import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PluginController {
    @GetMapping("/getTrsceId")
    public String getTrsceId(){
        ActiveSpan.error(new RuntimeException("test-error"));
        ActiveSpan.info("test - info");
        ActiveSpan.debug("test - debug");
        return TraceContext.traceId();

    }
}
