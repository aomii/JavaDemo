/*
 *
 *  *
 *  *  * ******************************************************************************
 *  *  *
 *  *  *                       Woodare PROPRIETARY INFORMATION
 *  *  *
 *  *  *           The information contained herein is proprietary to Woodare
 *  *  *            and shall not be reproduced or disclosed in whole or in part
 *  *  *                     or used for any design or manufacture
 *  *  *               without direct written authorization from FengDa.
 *  *  *
 *  *  *             Copyright (c) 2021 by Woodare.  All rights reserved.
 *  *  *
 *  *  * ******************************************************************************
 *  *
 *  
 *  
 */

package com.aoming.fkh.optimize.track_compress;

import cn.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Wang
 * @Date 2022/9/1
 */
public class Demo {

    public static void main(String[] args) {
        List<TripDetailData> path = new ArrayList<>();
        double lat = 118.745123;
        double lng = 31.970818;
        for (int i = 0; i < 100; i++) {
            TripDetailData tripDetailData = new TripDetailData();
            lat+=0.000001;
            lng+=0.000001;
            tripDetailData.setUid(UUID.randomUUID().toString());
            tripDetailData.setLat(lat);
            tripDetailData.setLng(lng);
            tripDetailData.setIdx((long) (i + 1));
            tripDetailData.setBearing((double) (i + 1));
            tripDetailData.setSpeed((long) (i + 1));
            tripDetailData.setTimestamp(1661942489000L + i);

            tripDetailData.setRpm((long) (i + 100));
            tripDetailData.setAcceleration((long) (i + 666));
            tripDetailData.setThrottle((double) (i + 10));
            tripDetailData.setWaterTemp((double) (i + 10));
            tripDetailData.setCvtTemp((double) (i + 10));
            tripDetailData.setBetteryVolt((double) (i + 10));
            tripDetailData.setGpsHeight((double) (i + 10));

            // if (i==0 || i==1 || i==2){
                path.add(tripDetailData);
            // }
        }
        // System.out.println(JSONUtil.toJsonStr(path));
        TrjCompressor trjCompressor = new TrjCompressor(6);
        System.out.printf("==============第一次压缩====================================");
        String encode = trjCompressor.encode(path);
        System.out.println(encode);

        System.out.printf("==============第二次压缩======================================");
        String zipCompress = ZipHelper.compress(encode);
        System.out.println(zipCompress);

        System.out.println("=============解压=================================================");
        encode = ZipHelper.decompress(zipCompress);

        List<TripDetailData> decode = trjCompressor.decode(encode);
        System.out.println(JSONUtil.toJsonStr(decode));
    }
}