package com.nacos.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.nacos.openfeign.produceOpenfeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class produceService {
    // 限流规则名称
    private static final String GETORDER_KEY = "orderToMember";

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/setService")
    private String setService(HttpServletRequest request){
        String gatewayPort = request.getHeader("serverPort");
        System.out.println("网关端口号 " + gatewayPort +"============ " + serverPort);
        return "网关端口号 " + gatewayPort +"生产者 添加服务" + serverPort;
    }

    @GetMapping("/setService/aaa")
    private String getService(){
        return "getService" ;
    }
    @SentinelResource(value = GETORDER_KEY, blockHandler = "getOrderQpsException")
    @GetMapping("/")
    public String nullService(){
        return "null  " + serverPort;
    }
    @Autowired
    private produceOpenfeign produceOpenfeign;

    @SentinelResource(value = GETORDER_KEY, blockHandler = "getOrderQpsException")
    @RequestMapping("/doOpenfeign")
    public String doOpenfeign(){
        String result = produceOpenfeign.openfeigntest(1);
        return "===========" + result ;
    }

    @RequestMapping("/orderToMemberSentinelResource")
    public String orderToMemberSentinelResource() {
        return "orderToMemberSentinelResource";
    }


    public String getOrderQpsException(BlockException e) {
        e.printStackTrace();
        return "该接口已经被限流啦!";
    }

}
