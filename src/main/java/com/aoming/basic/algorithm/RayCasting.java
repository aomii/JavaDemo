package com.aoming.basic.algorithm;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 射线法 </p>
 *
 * @author aoming
 * @version x.x.x
 * @email "mailto:aoming@fkhwl.com"
 * @date 2023.10.17 11:52
 * @since x.x.x
 */


public class RayCasting {

    public static boolean isPointInPolygon(Point2D.Double point, List<Point2D.Double> polygon) {
        int count = 0;
        int size = polygon.size();
        for (int i = 0; i < size; i++) {
            Point2D.Double p1 = polygon.get(i);
            Point2D.Double p2 = polygon.get((i + 1) % size);
            if (point.x == p1.x && point.y == p1.y) {
                return true;
            } else if (point.x == p2.x && point.y == p2.y) {
                return true;
            }
            if (p1.y == p2.y) {
                continue;
            }
            if (point.y < Math.min(p1.y, p2.y)) {
                continue;
            }
            if (point.y >= Math.max(p1.y, p2.y)) {
                continue;
            }
            double x = (point.y - p1.y) * (p2.x - p1.x) / (p2.y - p1.y) + p1.x;
            if (x > point.x) {
                count++;
            } else if (x == point.x) {
                return true;
            }
        }
        return count % 2 == 1;
    }

    public static void main(String[] args) {
        List<Point2D.Double> doubles = new ArrayList<>();
        doubles.add(new Point2D.Double(0, 3));
        doubles.add(new Point2D.Double(4, 4));
        doubles.add(new Point2D.Double(3, 1));
        doubles.add(new Point2D.Double(0, 0));
        // Point2D.Double point = new Point2D.Double(1, 1);
        Point2D.Double point = new Point2D.Double(4, 5);
        // Point2D.Double point = new Point2D.Double(-1, -2);
        System.out.println(isPointInPolygon(point,doubles));
    }
}
