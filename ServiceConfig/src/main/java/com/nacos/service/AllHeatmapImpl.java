package com.nacos.service;

import com.nacos.dao.AllHeatmap;
import com.nacos.mapper.AllHeatmapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class AllHeatmapImpl implements AllHeatmapService {
    @Autowired
    private AllHeatmapMapper allHeatmapMapper;
    @Override
    public List<AllHeatmap> selectAllAllHeatmap() {
        return allHeatmapMapper.selectAllAllHeatmap();
    }
}
