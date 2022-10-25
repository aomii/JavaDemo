package com.aoming.fkh.service;

import com.aoming.fkh.entity.form.LocationForm;
import com.aoming.fkh.entity.po.ClassPojo;
import com.aoming.fkh.entity.po.LogisticsDriverTrack;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ClassVoService extends IService<ClassPojo> {
    String getClassName(Long id);//自定义方法

    List<LogisticsDriverTrack> getHistoryLocation(LocationForm locationForm);

    void compressData();
}