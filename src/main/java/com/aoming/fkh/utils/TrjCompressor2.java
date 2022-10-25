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

package com.aoming.fkh.utils;


import com.aoming.fkh.entity.po.LogisticsDriverTrack;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Wang
 * @Date 2022/9/1
 */
public class TrjCompressor2 {
    private double factor;

    public TrjCompressor2(int precision) {
        factor = Math.pow(10, precision);
    }


    public String encode(List<LogisticsDriverTrack> points) {
        List<Long> output = new ArrayList<>();
        LogisticsDriverTrack prev = new LogisticsDriverTrack();
        prev.setCenterLat(0.0);
        prev.setCenterLng(0.0);

        for (int i = 0; i < points.size(); i++) {
            if (i > 0) {
                prev = points.get(i - 1);
            }
            write(output, points.get(i).getCenterLat(), prev.getCenterLat());
            write(output, points.get(i).getCenterLng(), prev.getCenterLng());
            // write(output, points.get(i).getTimestamp(), prev.getTimestamp());

        }
        return toASCII(output);
    }

    public List<LogisticsDriverTrack> decode(String trjCode) {
        long lat = 0, lng = 0, timestamp = 0, idx = 0, bearing = 0, speed = 0, rpm = 0, acceleration = 0, throttle = 0, waterTemp = 0, cvtTemp = 0, betteryVolt = 0, gpsHeight = 0,
                latD, lngD, timestampD, idxD, bearingD, speedD,
                rpmD, accelerationD = 0, throttleD, waterTempD, cvtTempD, betteryVoltD, gpsHeightD;
        List<LogisticsDriverTrack> points = new ArrayList<>();
        for (int i = 0; i < trjCode.length(); ) {
            Tuple tuple = read(trjCode, i);
            latD = tuple.result;
            i = tuple.index;

            tuple = read(trjCode, i);
            lngD = tuple.result;
            i = tuple.index;

            lat += latD;
            lng += lngD;

            LogisticsDriverTrack point = new LogisticsDriverTrack();
            point.setCenterLat((double) lat / factor);
            point.setCenterLng((double) lng / factor);
            points.add(point);
        }
        return points;
    }

    private void write(List<Long> output, Object currValue, Object prevValue) {
        long currV, prevV;
        if (currValue instanceof Long) {
            currV = (Long) currValue;
            prevV = (Long) prevValue;
        } else if (currValue instanceof Double) {
            currV = getRound((Double) currValue * factor);
            prevV = getRound((Double) prevValue * factor);
        } else {
            throw new IllegalArgumentException("The type parameters must be long or double!");
        }
        long offset = currV - prevV;
        offset <<= 1;
        if (offset < 0) {
            offset = ~offset;
        }
        while (offset >= 0x20) {
            output.add((0x20 | (offset & 0x1f)) + 63);
            offset >>= 5;
        }
        output.add((offset + 63));
    }

    private Tuple read(String s, int i) {
        long b = 0x20;
        long result = 0, shift = 0, comp = 0;
        while (b >= 0x20) {
            b = s.charAt(i) - 63;
            i++;
            result |= (b & 0x1f) << shift;
            shift += 5;
            comp = result & 1;
        }
        result >>= 1;
        if (comp == 1) {
            result = ~result;
        }
        return new Tuple(i, result);
    }

    private String toASCII(List<Long> nums) {

        StringBuilder result = new StringBuilder();
        for (long i : nums) {
            result.append((char) i);
        }
        return result.toString();
    }

    private long getRound(double x) {
        return (long) Math.copySign(Math.round(Math.abs(x)), x);
    }
}

class Tuple {
    long result;
    int index;

    Tuple(int index, long result) {
        this.index = index;
        this.result = result;
    }
}