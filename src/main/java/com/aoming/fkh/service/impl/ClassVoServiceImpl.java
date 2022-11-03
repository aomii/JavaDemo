package com.aoming.fkh.service.impl;


import com.aoming.fkh.entity.form.LocationForm;
import com.aoming.fkh.entity.po.ClassPojo;
import com.aoming.fkh.entity.po.LogisticsDriverTrack;
import com.aoming.fkh.entity.po.LogisticsDriverTrackHistory;
import com.aoming.fkh.mapper.ClassMapper;
import com.aoming.fkh.mapper.TrackHistoryMapper;
import com.aoming.fkh.mapper.TrackMapper;
import com.aoming.fkh.optimize.track_compress.TripDetailData;
import com.aoming.fkh.optimize.track_compress.TrjCompressor;
import com.aoming.fkh.optimize.track_compress.ZipHelper;
import com.aoming.fkh.optimize.track_sparse.GisDouglasUtil2;
import com.aoming.fkh.service.ClassVoService;
import com.aoming.fkh.utils.TrjCompressor2;
import com.aoming.tuling.jvm.JOLSample;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.hutool.json.JSONUtil;
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
            String encode=ZipHelper.decompress( historie.getCompressContent());
            List<LogisticsDriverTrack> tracks = trjCompressor.decode(encode);
            result.addAll(tracks);
        });
        result.forEach(track->{
            track.setVehiclePlateNo(history.getVehiclePlateNo());
            track.setDriverId(history.getDriverId());
            track.setWaybillId(history.getWaybillId());
        });
        log.info(System.currentTimeMillis()+"");
        return result;
    }

    // 测试压缩长度
    // @Override
    // public void compressData() {
    //     // TODO: 2022/10/21 开启对应核数的线程去压缩轨迹
    //     List<String> vehicleNos=this.trackMapper.selectVehicleNos();
    //     List<LogisticsDriverTrack> list=this.trackMapper.selectList(new QueryWrapper<LogisticsDriverTrack>().eq(LogisticsDriverTrack.VEHICLE_PLATE_NO,
    //                                                                                   vehicleNos.get(0)).orderByAsc(LogisticsDriverTrack.UPLOAD_TIME));
    //     List<LogisticsDriverTrack> tracks = GisDouglasUtil2.douglasPeucker(list, 3);
    //
    //     TrjCompressor2 trjCompressor = new TrjCompressor2(6);
    //
    //
    //     for (int i = 1; i <= 76; i++) {
    //         String encode = trjCompressor.encode(tracks.subList(0,i));
    //
    //         String zipCompress = ZipHelper.compress(encode);
    //         log.info("压缩后长度{}",zipCompress.length());
    //     }
    //
    //
    // }

    @Override
    public void compressData() {
        // TODO: 2022/10/21 开启对应核数的线程去压缩轨迹
        List<String> vehicleNos=this.trackMapper.selectVehicleNos();
        List<LogisticsDriverTrack> list=this.trackMapper.selectList(new QueryWrapper<LogisticsDriverTrack>().eq(LogisticsDriverTrack.VEHICLE_PLATE_NO,
                                                                                                                vehicleNos.get(0)).orderByAsc(LogisticsDriverTrack.UPLOAD_TIME));
        List<LogisticsDriverTrack> tracks = GisDouglasUtil2.douglasPeucker(list, 1);

        TrjCompressor2 trjCompressor = new TrjCompressor2(6);

        String encode = trjCompressor.encode(list);

        String zipCompress = ZipHelper.compress(encode);
        log.info("第二次压缩后：{}；长度{}",zipCompress,zipCompress.length());

        int n= (int) Math.ceil(zipCompress.length()*1.0 / 1000);
        long currentTimeMillis = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            LogisticsDriverTrackHistory history = new LogisticsDriverTrackHistory();
            LogisticsDriverTrack track = tracks.get(0);
            history.setVehiclePlateNo(track.getVehiclePlateNo());
            history.setWaybillId(track.getWaybillId());
            history.setDriverId(track.getDriverId());
            history.setCompressContent(zipCompress);
            history.setCompressBeginTime(track.getUploadTime());
            history.setCompressEndTime(tracks.get(tracks.size()-1).getUploadTime());
            history.setCreatedTime(currentTimeMillis);
            history.setBlockOffset(i);
            this.trackHistoryMapper.insert(history);
        }
        

        System.out.println("=============解压=================================================");
        encode = ZipHelper.decompress(zipCompress);

        List<LogisticsDriverTrack> decode = trjCompressor.decode(encode);
        System.out.println(JSONUtil.toJsonStr(decode));
    }
}