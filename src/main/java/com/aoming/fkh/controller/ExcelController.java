package com.aoming.fkh.controller;

import com.aoming.fkh.entity.form.LocationForm;
import com.aoming.fkh.entity.po.LogisticsDriverTrack;
import com.aoming.fkh.service.ClassVoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/demo")
public class ExcelController {

    @Autowired
    private ClassVoService classVoService;

    @GetMapping("/getbyid/{id}")
    public String getbyid(@PathVariable("id")Long id){
        return classVoService.getClassName (id);
    }

    @PostMapping("/getHistoryLocation")
    @ResponseBody
    @ApiOperation("获取车辆历史轨迹")
    public List<LogisticsDriverTrack> getHistoryLocation(@RequestBody LocationForm locationForm) {
        return  classVoService.getHistoryLocation(locationForm);
    }
}