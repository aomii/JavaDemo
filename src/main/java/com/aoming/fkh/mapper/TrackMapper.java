package com.aoming.fkh.mapper;

import com.aoming.fkh.entity.po.LogisticsDriverTrack0;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TrackMapper extends BaseMapper<LogisticsDriverTrack0> {

    List<String> selectVehicleNos();
}