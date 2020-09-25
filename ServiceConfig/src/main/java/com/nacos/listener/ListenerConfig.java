package com.nacos.listener;


import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;
import java.util.concurrent.Executor;
public class ListenerConfig {
//    @Value("${spring.cloud.nacos.config.server-addr}")
    public static String serverAddr
         = "127.0.0.1:8848";
//    @Value("${spring.cloud.nacos.config.prefix}" + "." + "${spring.cloud.nacos.config.file-extension}")
    public static String dataId
        = "test.yaml";
//    @Value("${spring.cloud.nacos.config.group}")
    public static String group
        = "DEFAULT_GROUP";
    public static void listenerConfigTest() throws NacosException {
        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        ConfigService configService = NacosFactory.createConfigService(properties);
        String content = configService.getConfig(dataId, group, 5000);
        System.out.println(content);
        configService.addListener(dataId, group, new Listener() {
            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("============监听配置 修改");
                System.out.println(configInfo);
            }
            @Override
            public Executor getExecutor() {
                return null;
            }
        });
    }

}
