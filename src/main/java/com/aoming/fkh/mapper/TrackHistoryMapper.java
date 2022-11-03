package com.aoming.fkh.mapper;

import com.aoming.fkh.entity.form.LocationForm;
import com.aoming.fkh.entity.po.LogisticsDriverTrack;
import com.aoming.fkh.entity.po.LogisticsDriverTrackHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TrackHistoryMapper extends BaseMapper<LogisticsDriverTrackHistory> {

    List<LogisticsDriverTrackHistory> page(@Param("query")LocationForm query);
}