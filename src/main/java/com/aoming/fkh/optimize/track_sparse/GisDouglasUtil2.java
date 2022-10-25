package com.aoming.fkh.optimize.track_sparse;

import com.aoming.fkh.entity.base.BaseTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * 坐标处理类 GPS点的抽稀-道格拉斯算法
 *
 * @author Elinx
 * @since 2021-05-31 10:28
 */
public class GisDouglasUtil2 {

    /**
     * 计算两点距离
     *
     * @param point1 点1
     * @param point2 点2
     * @return double
     */
    private static double calculationDistance(BaseTrack point1, BaseTrack point2) {
        double lat1 = point1.getCenterLat();
        double lat2 = point2.getCenterLat();
        double lng1 = point1.getCenterLng();
        double lng2 = point2.getCenterLng();
        double radLat1 = lat1 * Math.PI / 180.0;
        double radLat2 = lat2 * Math.PI / 180.0;
        double a = radLat1 - radLat2;
        double b = (lng1 * Math.PI / 180.0) - (lng2 * Math.PI / 180.0);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        return s * 6370996.81;

    }

    /**
     * 计算点pX到点pA和pB所确定的直线的距离
     *
     * @param start  开始
     * @param end    结束
     * @param center 中心
     * @return double
     */
    private static double distToSegment(BaseTrack start, BaseTrack end, BaseTrack center) {
        double a = Math.abs(calculationDistance(start, end));
        double b = Math.abs(calculationDistance(start, center));
        double c = Math.abs(calculationDistance(end, center));
        double p = (a + b + c) / 2.0;
        double s = Math.sqrt(Math.abs(p * (p - a) * (p - b) * (p - c)));
        return s * 2.0 / a;
    }

    /**
     * 递归方式压缩轨迹
     *
     * @param coordinate 坐标集
     * @param result     结果集
     * @param start      开始
     * @param end        结束
     * @param dMax       抽稀力度
     * @return List<double [ ]>
     */
    private static List<BaseTrack> compressLine(List<? extends BaseTrack> coordinate, List<BaseTrack> result, int start, int end, int dMax) {
        if (start < end) {
            double maxDist = 0;
            int currentIndex = 0;
            BaseTrack startPoint = coordinate.get(start);
            BaseTrack endPoint = coordinate.get(end);
            for (int i = start + 1; i < end; i++) {
                double currentDist = distToSegment(startPoint, endPoint, coordinate.get(i));
                if (currentDist > maxDist) {
                    maxDist = currentDist;
                    currentIndex = i;
                }
            }
            if (maxDist >= dMax) {
                //将当前点加入到过滤数组中
                result.add(coordinate.get(currentIndex));
                //将原来的线段以当前点为中心拆成两段，分别进行递归处理
                compressLine(coordinate, result, start, currentIndex, dMax);
                compressLine(coordinate, result, currentIndex, end, dMax);
            }
        }
        return result;
    }

    /**
     * @param coordinate 原始轨迹Array<{longitude, latitude}>
     * @param dMax       允许最大距离误差
     * @return douglasResult 抽稀后的轨迹
     */
    public static <T> List<T> douglasPeucker(List<? extends  BaseTrack<T>> coordinate, int dMax) {
        //抽稀点数量需要大于2
        if (coordinate == null || coordinate.size() <= 2) {
            return null;
        }

        for (int i = 0; i < coordinate.size(); i++) {
            coordinate.get(i).setSort(i);
        }
        List<BaseTrack> result = new ArrayList<>();
        result = compressLine(coordinate, result, 0, coordinate.size() - 1, dMax);

        result.add(coordinate.get(0));
        result.add(coordinate.get(coordinate.size() - 1));

        Collections.sort(result, new Comparator<BaseTrack>() {
            @Override
            public int compare(BaseTrack u1, BaseTrack u2) {
                if (u1.getSort() > u2.getSort()) {
                    return 1;
                } else if (u1.getSort() < u2.getSort()) {
                    return -1;
                }
                return 0; //相等为0
            }
        });

        return (List<T>)result;
    }

}


