package com.aoming.fkh.mapper;

import com.aoming.fkh.entity.po.ClassPojo;
import com.aoming.fkh.entity.po.LogisticsDriverTrack;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TrackMapper extends BaseMapper<LogisticsDriverTrack> {

    List<String> selectVehicleNos();
}