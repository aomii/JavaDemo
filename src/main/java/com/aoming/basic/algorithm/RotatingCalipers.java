package com.aoming.basic.algorithm;


/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 旋转卡壳: 求凸多边形直径（最远距离的两点的距离）</p>
 *
 * @author aoming
 * @version x.x.x
 * @email "mailto:aoming@fkhwl.com"
 * @date 2023.10.17 11:52
 * @since x.x.x
 */
public class RotatingCalipers {
    public static double rotatingCalipers(Point2[] point2s) {
        int n = point2s.length;
        if (n <= 1) {
            return 0;
        } else if (n == 2) {
            return point2s[0].distance(point2s[1]);
        } else {
            point2s = convexHull(point2s);
            n = point2s.length;
            if (n == 2) {
                return point2s[0].distance(point2s[1]);
            } else {
                double ans = 0;
                int j = 2;
                for (int i = 0; i < n; i++) {
                    while ((point2s[i].subtract(point2s[(i + 1) % n])).cross(point2s[j % n].subtract(point2s[(i + 1) % n])) < (point2s[i].subtract(point2s[(i + 1) % n])).cross(point2s[(j + 1) % n].subtract(point2s[(i + 1) % n]))) {
                        j++;
                    }
                    ans = Math.max(ans, Math.max(point2s[i].distance(point2s[j % n]), point2s[(i + 1) % n].distance(point2s[j % n])));
                }
                return ans;
            }
        }
    }

    public static Point2[] convexHull(Point2[] point2s) {
        // 这里省略了凸包的计算过程，你可以使用Graham扫描或者其他的凸包算法
        return point2s;
    }
}

class Point2 {
    double x, y;

    public Point2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point2 p) {
        return Math.hypot(x - p.x, y - p.y);
    }

    public Point2 subtract(Point2 p) {
        return new Point2(x - p.x, y - p.y);
    }

    public double cross(Point2 p) {
        return x * p.y - y * p.x;
    }
}