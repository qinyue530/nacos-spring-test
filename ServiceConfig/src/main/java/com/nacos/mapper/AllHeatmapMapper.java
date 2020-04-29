package com.nacos.mapper;

import com.nacos.dao.AllHeatmap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@Mapper
public interface AllHeatmapMapper {
    @Select("select * from all_heatmap")
    List<AllHeatmap> selectAllAllHeatmap();
}
