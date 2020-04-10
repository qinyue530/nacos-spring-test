package com.nacos.consume;

import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
public class consumeService {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/getService")
    public Object getService(){
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("ProduceService");
        ServiceInstance serviceInstance = serviceInstanceList.get(0);
        URI url = serviceInstance.getUri();
        String result = restTemplate.getForObject(url+"/setService",String.class);
        return "调用生产者服务 - 返回值" + result;
    }

}
