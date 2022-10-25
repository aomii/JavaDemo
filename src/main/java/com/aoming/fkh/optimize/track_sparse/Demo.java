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

package com.aoming.fkh.optimize.track_sparse;

import com.aoming.fkh.optimize.track_compress.TripDetailData;
import com.aoming.fkh.optimize.track_compress.TrjCompressor;
import com.aoming.fkh.optimize.track_compress.ZipHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.hutool.json.JSONUtil;

/**
 * @author Wang
 * @Date 2022/9/1
 */
public class Demo {

    public static void main(String[] args) {
        List<TripDetailData> path = new ArrayList<>();
        double lat = 118.745123;
        double lng = 31.970818;
        for (int i = 0; i < 1000; i++) {
            TripDetailData tripDetailData = new TripDetailData();
            lat+=0.000001;
            lng+=0.000001;
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

            path.add(tripDetailData);
        }
        List<double[]> runtimeTrackList = new ArrayList<>();
        for (TripDetailData trackObj : path) {
            // new track 仅包含x,y，自行设定解析类
            Double longitude = trackObj.getLng();
            Double latitude = trackObj.getLat();
            if (latitude != null && longitude != null) {
                double[] temp = new double[2];
                Arrays.fill(temp, longitude);
                Arrays.fill(temp, latitude);
                runtimeTrackList.add(temp);
            }
        }
        // 抽稀算法，力度3
        runtimeTrackList = GisDouglasUtil.douglasPeucker(runtimeTrackList, 3);
        System.out.println(JSONUtil.toJsonStr(runtimeTrackList));
    }
}