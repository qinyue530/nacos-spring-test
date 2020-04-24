package com.nacos.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ConsumeService")
public interface produceOpenfeign {

    @GetMapping("/Ribbon")
    String openfeigntest(@RequestParam("i") int i);
}
