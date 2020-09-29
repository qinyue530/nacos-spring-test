package com.nacos.controller;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;
import java.util.concurrent.Executor;

@RestController
@RefreshScope
public class YmlController {

    @Value("${server.port}")
    private String port;

    @Value("${spring.cloud.nacos.config.server-addr}")
    public String serverAddr;

    @Value("${spring.cloud.nacos.config.prefix}" + "." + "${spring.cloud.nacos.config.file-extension}")
    public String dataId;

    @Value("${spring.cloud.nacos.config.group}")
    public String group;

    @RequestMapping("/getYml")
    public void getYml() throws  NacosException{
        this.listenerConfigTest(serverAddr,dataId,group);
    }

    public void listenerConfigTest(String serverAddr , String dataId , String group) throws NacosException {
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        ConfigService configService = NacosFactory.createConfigService(properties);
        String content = configService.getConfig(dataId, group, 5000);
        System.out.println(content);
        configService.addListener(dataId, group, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println(serverAddr+"--"+ group + "--" + dataId +"============监听配置 修改");
                System.out.println(configInfo);
            }
            @Override
            public Executor getExecutor() {
                return null;
            }
        });
    }


}
