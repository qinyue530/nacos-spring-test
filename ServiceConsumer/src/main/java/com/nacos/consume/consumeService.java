package com.nacos.consume;

import com.nacos.loadbalance.LoadBalancer;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
public class consumeService {
    @Value("${server.port}")
    private String serverPort;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RestTemplate restTemplateMy;
    @Autowired
    private LoadBalancer loadBalancer;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

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
        String result = restTemplateMy.getForObject(url+"/setService",String.class);
        System.out.println(result+ "===============================");
        return "调用生产者服务 - 返回值" + result;
    }
    /**
     * 注意 restTemplate要加上 @LoadBalanced注解， 看ConsumeApp代码
     * Ribbon 负载均衡
     */

    @RequestMapping("/Ribbon")
    public Object ribbonService(){
        String result= restTemplate.getForObject("http://ProduceService/setService" , String.class);
        return "=======" +serverPort+"Ribbon 负载均衡 - 返回信息" + result ;
    }

    /**
     * LoadBalancerClient 实现负载均衡
     * @return
     */
    @RequestMapping("/loadBalance")
    public Object loadBalanceService(){
        ServiceInstance result = loadBalancerClient.choose("ProduceService");
        return "loadBalance 负载均衡 - 返回信息" + result ;
    }
}
