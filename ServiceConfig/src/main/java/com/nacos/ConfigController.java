package com.nacos;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @RequestMapping("/getName")
    public String getName(){
        return name;
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
