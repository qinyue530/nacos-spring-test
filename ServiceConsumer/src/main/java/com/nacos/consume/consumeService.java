package com.nacos.consume;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class consumeService {

    @Autowired
    DiscoveryClient discoveryClient;
    @RequestMapping("/getService")
    public Object getService(){
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("ConsumeService");
        ServiceInstance serviceInstance = serviceInstanceList.get(0);

        return serviceInstance;
    }

}
