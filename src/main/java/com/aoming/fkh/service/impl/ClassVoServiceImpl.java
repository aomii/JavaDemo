package com.aoming.fkh.service.impl;


import com.aoming.fkh.entity.form.LocationForm;
import com.aoming.fkh.entity.po.ClassPojo;
import com.aoming.fkh.entity.po.LogisticsDriverTrack;
import com.aoming.fkh.entity.po.LogisticsDriverTrackHistory;
import com.aoming.fkh.mapper.ClassMapper;
import com.aoming.fkh.mapper.TrackHistoryMapper;
import com.aoming.fkh.mapper.TrackMapper;
import com.aoming.fkh.optimize.track_compress.TrjCompressor;
import com.aoming.fkh.optimize.track_compress.ZipHelper;
import com.aoming.fkh.optimize.track_sparse.GisDouglasUtil2;
import com.aoming.fkh.service.ClassVoService;
import com.aoming.fkh.utils.TrjCompressor2;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ClassVoServiceImpl extends ServiceImpl<ClassMapper, ClassPojo> implements ClassVoService {
    @Autowired
    private  TrackMapper trackMapper;

    @Autowired
    private TrackHistoryMapper trackHistoryMapper;

    public String getClassName(Long id){
        ClassPojo byId = this.getById (id);
        return byId.getClassName ();
 
    }

    @Override
    public List<LogisticsDriverTrack> getHistoryLocation(LocationForm locationForm) {
        List<LogisticsDriverTrackHistory> histories =
            this.trackHistoryMapper.selectList(new QueryWrapper<LogisticsDriverTrackHistory>().eq(LogisticsDriverTrackHistory.VEHICLE_PLATE_NO, locationForm.getPlateNo()).orderByAsc(LogisticsDriverTrackHistory.CREATED_TIME));
        List<LogisticsDriverTrack> result=new ArrayList<>();
        LogisticsDriverTrackHistory history = histories.get(0);
        TrjCompressor2 trjCompressor = new TrjCompressor2(6);
        histories.forEach(historie->{
            List<LogisticsDriverTrack> tracks = trjCompressor.decode(historie.getCompressContent());
            result.addAll(tracks);
        });
        result.forEach(track->{
            track.setVehiclePlateNo(history.getVehiclePlateNo());
            track.setDriverId(history.getDriverId());
            track.setWaybillId(history.getWaybillId());
        });
        return result;
    }

    @Override
    public void compressData() {
        // TODO: 2022/10/21 开启对应核数的线程去压缩轨迹
        List<String> vehicleNos=this.trackMapper.selectVehicleNos();
        List<LogisticsDriverTrack> list=this.trackMapper.selectList(new QueryWrapper<LogisticsDriverTrack>().eq(LogisticsDriverTrack.VEHICLE_PLATE_NO,
                                                                                      vehicleNos.get(0)).orderByAsc(LogisticsDriverTrack.UPLOAD_TIME));
        List<LogisticsDriverTrack> tracks = GisDouglasUtil2.douglasPeucker(list, 3);

        TrjCompressor2 trjCompressor = new TrjCompressor2(6);


        String encode = trjCompressor.encode(tracks);
        log.info("第一次压缩后：{}",encode);

        String zipCompress = ZipHelper.compress(encode);
        log.info("第二次压缩后：{}",zipCompress);

        LogisticsDriverTrackHistory history = new LogisticsDriverTrackHistory();
        LogisticsDriverTrack track = tracks.get(0);
        history.setVehiclePlateNo(track.getVehiclePlateNo());
        history.setWaybillId(track.getWaybillId());
        history.setDriverId(track.getDriverId());
        history.setCompressContent(zipCompress);
        history.setCompressBeginTime(track.getUploadTime());
        history.setCompressEndTime(tracks.get(tracks.size()-1).getUploadTime());
        this.trackHistoryMapper.insert(history);
    }



}