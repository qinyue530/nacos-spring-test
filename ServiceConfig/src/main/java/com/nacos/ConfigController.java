package com.nacos;


import com.alibaba.cloud.nacos.discovery.NacosDiscoveryClient;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SpringBootApplication
@RefreshScope
//-javaagent:D:\Apache\apache-skywalking-apm-bin\agent\skywalking-agent.jar -Dserver.port=7090
public class ConfigController implements ApplicationContextAware {
    public static void main(String[] args) {
        SpringApplication.run(ConfigController.class);
    }

    @Value("${common.name}")
    private String name;

    @RequestMapping("/test/getName")
    public String getName() throws NacosException {
        NamingService naming = NamingFactory.createNamingService("127.0.0.1:8848");
//        System.out.println(naming.toString());
        List<Instance> instance =naming.getAllInstances("ConfigService");
        for(Instance i : instance){
            System.out.println(i.getInstanceId()+" "+i.getServiceName()+" "+i.getIp()+":"+i.getPort()+"实例元数据"+i.getMetadata());
            System.out.println(i.getMetadata().get("test")+"=============");
        }

        return name + "================8083=================";
    }

    @RequestMapping("/geta")
    public String getsssssss(){
        return String.valueOf(1/0);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println(applicationContext.getBean("allHeatmapMapper"));
    }
}
