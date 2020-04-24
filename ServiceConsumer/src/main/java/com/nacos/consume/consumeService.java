package com.nacos.consume;

import com.nacos.loadbalance.LoadBalancer;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
    @Autowired
    private LoadBalancer loadBalancer;

    /**
     * 手写负载均衡器
     * @return
     */
    @RequestMapping("/getService")
    public Object getService(){
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("ProduceService");
//        ServiceInstance serviceInstance = serviceInstanceList.get(0);
        ServiceInstance serviceInstance = loadBalancer.getSingleAddres(serviceInstanceList);
        URI url = serviceInstance.getUri();
        String result = restTemplate.getForObject(url+"/setService",String.class);
        return "调用生产者服务 - 返回值" + result;
    }
    /**
     * 注意 restTemplate要加上 @LoadBalanced注解， 看ConsumeApp代码
     * Ribbon 负载均衡
     */

    @RequestMapping("/Ribbon")
    public Object ribbonService(){
        String result= restTemplate.getForObject("http://ProduceService/setService" , String.class);
        return "Ribbon 负载均衡 - 返回信息" + result ;
    }

}
