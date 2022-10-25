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


import java.util.ArrayList;
import java.util.List;

/**
 * @author Wang
 * @Date 2022/9/1
 */
public class TrjCompressor {
    private double factor;

    public TrjCompressor(int precision) {
        factor = Math.pow(10, precision);
    }


    public String encode(List<TripDetailData> points) {
        List<Long> output = new ArrayList<>();
        TripDetailData prev = new TripDetailData();
        prev.setLat(0.0);
        prev.setLng(0.0);
        prev.setTimestamp(0L);
        prev.setIdx(0L);
        prev.setBearing(0.0);
        prev.setSpeed(0L);

        /*新增*/
        prev.setRpm(0L);
        prev.setAcceleration(0L);
        prev.setThrottle(0.0);
        prev.setWaterTemp(0.0);
        prev.setCvtTemp(0.0);
        prev.setBetteryVolt(0.0);
        prev.setGpsHeight(0.0);
        for (int i = 0; i < points.size(); i++) {
            if (i > 0) {
                prev = points.get(i - 1);
            }
            write(output, points.get(i).getLat(), prev.getLat());
            write(output, points.get(i).getLng(), prev.getLng());
            write(output, points.get(i).getTimestamp(), prev.getTimestamp());
            write(output, points.get(i).getIdx(), prev.getIdx());
            write(output, points.get(i).getBearing(), prev.getBearing());
            write(output, points.get(i).getSpeed(), prev.getSpeed());

            /*新增*/
            write(output, points.get(i).getRpm(), prev.getRpm());
            write(output, points.get(i).getAcceleration(), prev.getAcceleration());
            write(output, points.get(i).getThrottle(), prev.getThrottle());
            write(output, points.get(i).getWaterTemp(), prev.getWaterTemp());
            write(output, points.get(i).getCvtTemp(), prev.getCvtTemp());
            write(output, points.get(i).getBetteryVolt(), prev.getBetteryVolt());
            write(output, points.get(i).getGpsHeight(), prev.getGpsHeight());
        }
        return toASCII(output);
    }

    public List<TripDetailData> decode(String trjCode) {
        long lat = 0, lng = 0, timestamp = 0, idx = 0, bearing = 0, speed = 0, rpm = 0, acceleration = 0, throttle = 0, waterTemp = 0, cvtTemp = 0, betteryVolt = 0, gpsHeight = 0,
                latD, lngD, timestampD, idxD, bearingD, speedD,
                rpmD, accelerationD = 0, throttleD, waterTempD, cvtTempD, betteryVoltD, gpsHeightD;
        List<TripDetailData> points = new ArrayList<>();
        for (int i = 0; i < trjCode.length(); ) {
            Tuple tuple = read(trjCode, i);
            latD = tuple.result;
            i = tuple.index;

            tuple = read(trjCode, i);
            lngD = tuple.result;
            i = tuple.index;

            tuple = read(trjCode, i);
            timestampD = tuple.result;
            i = tuple.index;

            tuple = read(trjCode, i);
            idxD = tuple.result;
            i = tuple.index;

            tuple = read(trjCode, i);
            bearingD = tuple.result;
            i = tuple.index;

            tuple = read(trjCode, i);
            speedD = tuple.result;
            i = tuple.index;

            /** 新增 **/
            tuple = read(trjCode, i);
            rpmD = tuple.result;
            i = tuple.index;

            tuple = read(trjCode, i);
            accelerationD = tuple.result;
            i = tuple.index;

            tuple = read(trjCode, i);
            throttleD = tuple.result;
            i = tuple.index;

            tuple = read(trjCode, i);
            waterTempD = tuple.result;
            i = tuple.index;

            tuple = read(trjCode, i);
            cvtTempD = tuple.result;
            i = tuple.index;

            tuple = read(trjCode, i);
            betteryVoltD = tuple.result;
            i = tuple.index;

            tuple = read(trjCode, i);
            gpsHeightD = tuple.result;
            i = tuple.index;

            lat += latD;
            lng += lngD;
            timestamp += timestampD;
            idx += idxD;
            bearing += bearingD;
            speed += speedD;

            /*新增*/
            rpm += rpmD;
            acceleration += accelerationD;
            throttle += throttleD;
            waterTemp += waterTempD;
            cvtTemp += cvtTempD;
            betteryVolt += betteryVoltD;
            gpsHeight += gpsHeightD;

            TripDetailData point = new TripDetailData();
            point.setLat((double) lat / factor);
            point.setLng((double) lng / factor);
            point.setTimestamp(timestamp);
            point.setIdx(idx);
            point.setBearing((double) bearing / factor);
            point.setSpeed(speed);

            /*新增*/
            point.setRpm(rpm);
            point.setAcceleration(acceleration);
            point.setThrottle(throttle / factor);
            point.setWaterTemp(waterTemp / factor);
            point.setCvtTemp(cvtTemp / factor);
            point.setBetteryVolt(betteryVolt / factor);
            point.setGpsHeight(gpsHeight / factor);

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