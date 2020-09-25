package com.nacos.controller;

import com.alibaba.nacos.api.exception.NacosException;
import com.nacos.dao.AllHeatmap;
import com.nacos.listener.ListenerConfig;
import com.nacos.service.AllHeatmapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AllHeatmapController {
    @Autowired
    private AllHeatmapService allHeatmapService;
    @RequestMapping("/getAll")
    public String getAll() throws NacosException {
        List<AllHeatmap> allHeatmapList = allHeatmapService.selectAllAllHeatmap();
       /* for(AllHeatmap s : allHeatmapList){
            System.out.println(s.toString());
        }*/
        System.out.println(allHeatmapList);
        ListenerConfig.listenerConfigTest();
        return "allHeatmapList.toString()";
    }

}
