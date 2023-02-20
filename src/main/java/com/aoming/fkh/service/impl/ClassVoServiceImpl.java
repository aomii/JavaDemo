package com.aoming.fkh.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.aoming.fkh.entity.form.LocationForm;
import com.aoming.fkh.entity.po.ClassPojo;
import com.aoming.fkh.entity.zj.TrackDetailDTO;
import com.aoming.fkh.entity.zj.WaybillDTO;
import com.aoming.fkh.entity.zj.WaybillHistoryTrackCompressDTO;
import com.aoming.fkh.mapper.ClassMapper;
import com.aoming.fkh.mapper.TrackHistoryMapper;
import com.aoming.fkh.mapper.TrackMapper;
import com.aoming.fkh.optimize.track_compress.ZipHelper;
import com.aoming.fkh.service.ClassVoService;
import com.aoming.fkh.utils.GisDouglasUtil;
import com.aoming.fkh.utils.TrjCompressor;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ClassVoServiceImpl extends ServiceImpl<ClassMapper, ClassPojo> implements ClassVoService {
    @Autowired
    private  TrackMapper trackMapper;

    @Autowired
    private TrackHistoryMapper trackHistoryMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

    public static final int SPARSE_D_MAX=15;//抽稀最大力度，单位：米


    public String getClassName(Long id){
        ClassPojo byId = this.getById (id);
        return byId.getClassName ();
    }

    @Override
    public List<TrackDetailDTO> getHistoryLocation(Long waybillId) {
        Criteria criteria = Criteria.where(WaybillHistoryTrackCompressDTO.WAYBILL_ID).is(String.valueOf(waybillId));
        Query query = new Query(criteria);

        List<WaybillHistoryTrackCompressDTO> compressDTOList = mongoTemplate.find(query, WaybillHistoryTrackCompressDTO.class,
                                                                                  WaybillHistoryTrackCompressDTO.WAYBILL_HISTORY_TRACK_COMPRESS);

        if (CollectionUtils.isEmpty(compressDTOList)){
            log.error("mongo查询为空");
            return null;
        }
        WaybillHistoryTrackCompressDTO dto = compressDTOList.get(0);
        String compressContent=dto.getCompressContent();

        TrjCompressor<TrackDetailDTO> trjCompressor = new TrjCompressor(TrackDetailDTO.class);
        String decompressContent = ZipHelper.decompress(compressContent);
        List<TrackDetailDTO> trackDetailDTOS = trjCompressor.decode(decompressContent);
        if (CollectionUtils.isNotEmpty(trackDetailDTOS)){
            log.info("解压结果条数: {}", trackDetailDTOS.size());
            log.info("解压结果：{}", JSONArray.toJSONString(trackDetailDTOS));
        }
        return trackDetailDTOS;
    }


    @Override
    public void compressData() {

        List<TrackDetailDTO> list=creatData();

        log.info("压缩前数据：{}", JSONArray.toJSONString(list));

        WaybillDTO waybillDTO = WaybillDTO.builder().id(1L).driverPhone("18408266277").plateNo("川AP0863").loadTime(getDate("2022-12-12 " +
                                                                                                                        "00：00：00"))
            .unloadTime(getDate("2023-12-12 00：00：00")).build();

        sparseAndCompress(list,waybillDTO);

    }

    private List<TrackDetailDTO> creatData() {
        //保留6位小数
        String s="[{\"agl\":\"0\",\"gtm\":\"20230106042027\",\"hgt\":\"0\",\"lat\":\"27.67307\",\"lon\":\"104.744977\",\"mlg\":\"52428" +
                 ".9\",\"spd\":\"0.0\",\"createTime\":\"\",\"lastUpdateTime\":\"\"},{\"agl\":\"334\",\"gtm\":\"20230106042127\",\"hgt\":\"799\"," +
                 "\"lat\":\"27.673315\",\"lon\":\"104.74492\",\"mlg\":\"52428.9\",\"spd\":\"0.0\",\"createTime\":\"\",\"lastUpdateTime\":\"\"}," +
                 "{\"agl\":\"170\",\"gtm\":\"20230106042157\",\"hgt\":\"792\",\"lat\":\"27.67309\",\"lon\":\"104.74503\",\"mlg\":\"52428.9\"," +
                 "\"spd\":\"9.0\",\"createTime\":\"\",\"lastUpdateTime\":\"\"},{\"agl\":\"235\",\"gtm\":\"20230106042227\",\"hgt\":\"791\"," +
                 "\"lat\":\"27.672845\",\"lon\":\"104.744952\",\"mlg\":\"52429.0\",\"spd\":\"9.0\",\"createTime\":\"\"," +
                 "\"lastUpdateTime\":\"\"},{\"agl\":\"302\",\"gtm\":\"20230106042257\",\"hgt\":\"794\",\"lat\":\"27.672595\",\"lon\":\"104" +
                 ".743815\",\"mlg\":\"52429.1\",\"spd\":\"15.0\",\"createTime\":\"\",\"lastUpdateTime\":\"\"},{\"agl\":\"280\"," +
                 "\"gtm\":\"20230106042326\",\"hgt\":\"795\",\"lat\":\"27.673042\",\"lon\":\"104.743382\",\"mlg\":\"52429.1\",\"spd\":\"0" +
                 ".0\",\"createTime\":\"\",\"lastUpdateTime\":\"\"},{\"agl\":\"317\",\"gtm\":\"20230106042426\",\"hgt\":\"791\"," +
                 "\"lat\":\"27.672772\",\"lon\":\"104.74364\",\"mlg\":\"52429.2\",\"spd\":\"0.0\",\"createTime\":\"\"," +
                 "\"lastUpdateTime\":\"\"},{\"agl\":\"138\",\"gtm\":\"20230106042456\",\"hgt\":\"794\",\"lat\":\"27.67278\"," +
                 "\"lon\":\"104.74348\",\"mlg\":\"52429.2\",\"spd\":\"10.0\",\"createTime\":\"\",\"lastUpdateTime\":\"\"}," +
                 "{\"agl\":\"143\",\"gtm\":\"20230106042526\",\"hgt\":\"811\",\"lat\":\"27.672227\",\"lon\":\"104.744125\"," +
                 "\"mlg\":\"52429.3\",\"spd\":\"11.0\",\"createTime\":\"\",\"lastUpdateTime\":\"\"},{\"agl\":\"265\"," +
                 "\"gtm\":\"20230106042556\",\"hgt\":\"829\",\"lat\":\"27.671602\",\"lon\":\"104.744057\",\"mlg\":\"52429.4\"," +
                 "\"spd\":\"9.0\",\"createTime\":\"\",\"lastUpdateTime\":\"\"},{\"agl\":\"156\",\"gtm\":\"20230106042626\"," +
                 "\"hgt\":\"840\",\"lat\":\"27.671142\",\"lon\":\"104.743535\",\"mlg\":\"52429.5\",\"spd\":\"10.0\"," +
                 "\"createTime\":\"\",\"lastUpdateTime\":\"\"},{\"agl\":\"99\",\"gtm\":\"20230106042656\",\"hgt\":\"841\"," +
                 "\"lat\":\"27.670815\",\"lon\":\"104.743975\",\"mlg\":\"52429.5\",\"spd\":\"9.0\",\"createTime\":\"\"," +
                 "\"lastUpdateTime\":\"\"},{\"agl\":\"5\",\"gtm\":\"20230106042756\",\"hgt\":\"841\",\"lat\":\"27.671045\"," +
                 "\"lon\":\"104.74366\",\"mlg\":\"52429.6\",\"spd\":\"0.0\",\"createTime\":\"\",\"lastUpdateTime\":\"\"}," +
                 "{\"agl\":\"45\",\"gtm\":\"20230106045549\",\"hgt\":\"833\",\"lat\":\"27.67152\",\"lon\":\"104.74362\"," +
                 "\"mlg\":\"52429.6\",\"spd\":\"6.0\",\"createTime\":\"\",\"lastUpdateTime\":\"\"},{\"agl\":\"72\"," +
                 "\"gtm\":\"20230106045618\",\"hgt\":\"831\",\"lat\":\"27.671645\",\"lon\":\"104.74392\",\"mlg\":\"52429" +
                 ".6\",\"spd\":\"0.0\",\"createTime\":\"\",\"lastUpdateTime\":\"\"},{\"agl\":\"335\"," +
                 "\"gtm\":\"20230106045648\",\"hgt\":\"824\",\"lat\":\"27.6718\",\"lon\":\"104.744272\",\"mlg\":\"52429" +
                 ".6\",\"spd\":\"0.0\",\"createTime\":\"\",\"lastUpdateTime\":\"\"},{\"agl\":\"305\"," +
                 "\"gtm\":\"20230106045748\",\"hgt\":\"809\",\"lat\":\"27.67247\",\"lon\":\"104.74381\"," +
                 "\"mlg\":\"52429.6\",\"spd\":\"6.0\",\"createTime\":\"\",\"lastUpdateTime\":\"\"},{\"agl\":\"322\"," +
                 "\"gtm\":\"20230106045818\",\"hgt\":\"802\",\"lat\":\"27.672785\",\"lon\":\"104.743445\"," +
                 "\"mlg\":\"52429.7\",\"spd\":\"6.0\",\"createTime\":\"\",\"lastUpdateTime\":\"\"}," +
                 "{\"agl\":\"287\",\"gtm\":\"20230106045848\",\"hgt\":\"802\",\"lat\":\"27.673105\",\"lon\":\"104" +
                 ".74314\",\"mlg\":\"52429.7\",\"spd\":\"0.0\",\"createTime\":\"\",\"lastUpdateTime\":\"\"}," +
                 "{\"agl\":\"129\",\"gtm\":\"20230106050048\",\"hgt\":\"798\",\"lat\":\"27.67271\",\"lon\":\"104" +
                 ".743765\",\"mlg\":\"52429.8\",\"spd\":\"6.0\",\"createTime\":\"\",\"lastUpdateTime\":\"\"}," +
                 "{\"agl\":\"0\",\"gtm\":\"20230106081938\",\"hgt\":\"0\",\"lat\":\"27.673105\",\"lon\":\"104" +
                 ".74314\",\"mlg\":\"52429.7\",\"spd\":\"0.0\",\"createTime\":\"\",\"lastUpdateTime\":\"\"}," +
                 "{\"agl\":\"0\",\"gtm\":\"20230106081950\",\"hgt\":\"811\",\"lat\":\"27.672665\"," +
                 "\"lon\":\"104.74376\",\"mlg\":\"52429.7\",\"spd\":\"0.0\",\"createTime\":\"\"," +
                 "\"lastUpdateTime\":\"\"},{\"agl\":\"66\",\"gtm\":\"20230106082048\",\"hgt\":\"800\"," +
                 "\"lat\":\"27.672825\",\"lon\":\"104.74488\",\"mlg\":\"52429.8\",\"spd\":\"14.0\"," +
                 "\"createTime\":\"\",\"lastUpdateTime\":\"\"},{\"agl\":\"309\"," +
                 "\"gtm\":\"20230106082118\",\"hgt\":\"802\",\"lat\":\"27.673532\",\"lon\":\"104" +
                 ".744605\",\"mlg\":\"52429.9\",\"spd\":\"6.0\",\"createTime\":\"\"," +
                 "\"lastUpdateTime\":\"\"},{\"agl\":\"312\",\"gtm\":\"20230106082149\"," +
                 "\"hgt\":\"797\",\"lat\":\"27.674075\",\"lon\":\"104.743985\",\"mlg\":\"52430.0\"," +
                 "\"spd\":\"6.0\",\"createTime\":\"\",\"lastUpdateTime\":\"\"},{\"agl\":\"291\"," +
                 "\"gtm\":\"20230106082218\",\"hgt\":\"783\",\"lat\":\"27.67439\",\"lon\":\"104" +
                 ".743642\",\"mlg\":\"52430.1\",\"spd\":\"8.0\",\"createTime\":\"\"," +
                 "\"lastUpdateTime\":\"\"},{\"agl\":\"306\",\"gtm\":\"20230106082248\"," +
                 "\"hgt\":\"779\",\"lat\":\"27.67479\",\"lon\":\"104.743025\",\"mlg\":\"52430" +
                 ".1\",\"spd\":\"10.0\",\"createTime\":\"\",\"lastUpdateTime\":\"\"}]";

        List<TrackDetailDTO> list = JSONArray.parseArray(s, TrackDetailDTO.class);
        return list;
    }

    private void sparseAndCompress(List<TrackDetailDTO> list, WaybillDTO waybillDTO) {
        //抽稀粒度为15米
        log.info("抽稀前轨迹点数据,条数{}", list.size());
        GisDouglasUtil.douglasPeucker(list, SPARSE_D_MAX);
        log.info("抽稀力度{}米后轨迹点数据,条数{}",SPARSE_D_MAX, list.size());

        //保留6位小数
        TrjCompressor trjCompressor = new TrjCompressor(TrackDetailDTO.class);

        String encode = trjCompressor.encode(list);
        log.info("第一次压缩后长度{}",  encode.length());
        String zipCompress = ZipHelper.compress(encode);
        log.info("第二次压缩后长度{}", zipCompress.length());

        WaybillHistoryTrackCompressDTO historyTrackDTO = new WaybillHistoryTrackCompressDTO();
        historyTrackDTO.setWaybillId(String.valueOf(waybillDTO.getId()));
        historyTrackDTO.setDriverPhone(waybillDTO.getDriverPhone());
        historyTrackDTO.setVehiclePlateNo(waybillDTO.getPlateNo());
        historyTrackDTO.setCompressBeginTime(waybillDTO.getLoadTime());
        historyTrackDTO.setCompressEndTime(waybillDTO.getUnloadTime());
        historyTrackDTO.setCreatedTime(new Date());
        historyTrackDTO.setCompressContent(zipCompress);

        Criteria criteria = Criteria.where(WaybillHistoryTrackCompressDTO.WAYBILL_ID).is(waybillDTO.getId());
        Query query = new Query(criteria);
        this.mongoTemplate.remove(query, WaybillHistoryTrackCompressDTO.WAYBILL_HISTORY_TRACK_COMPRESS);
        this.mongoTemplate.insert(historyTrackDTO, WaybillHistoryTrackCompressDTO.WAYBILL_HISTORY_TRACK_COMPRESS);

    }

    private Date getDate(String s) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
        Date date = null;
        try {
            date = format.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }
}