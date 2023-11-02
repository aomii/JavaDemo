package com.aoming.basic.algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 凸包问题算法的Java实现 </p>
 *
 * @author aoming
 * @version x.x.x
 * @email "mailto:aoming@fkhwl.com"
 * @date 2023.10.16 14:03
 * @since x.x.x
 */


class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
               "x=" + x +
               ", y=" + y +
               '}';
    }
}

@Slf4j
public class GrahamScan {
    Point p0;

    // A utility function to find next to top in a stack
    Point nextToTop(Stack<Point> S) {
        Point p = S.pop();
        Point res = S.peek();
        S.push(p);
        return res;
    }

    // A utility function to swap two points
    void swap(Point[] P, int i, int j) {
        Point temp = P[i];
        P[i] = P[j];
        P[j] = temp;
    }

    // A utility function to return square of distance between p1 and p2
    int distSq(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    // To find orientation of ordered triplet (p, q, r).
    // The function returns following values
    // 0 --> p, q and r are colinear
    // 1 --> Clockwise
    // 2 --> Counterclockwise
    int orientation(Point p, Point q, Point r) {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);

        if (val == 0) {
            return 0;  // colinear
        }
        return (val > 0) ? 1 : 2; // clock or counterclock wise
    }


    // Prints convex hull of a set of n points.
    void convexHull(Point[] points, int n) {
        // Find the bottommost point
        int ymin = points[0].y, min = 0;
        for (int i = 1; i < n; i++) {
            int y = points[i].y;

            // Pick the bottom-most or chose the left most point in case of tie
            if ((y < ymin) || (ymin == y && points[i].x < points[min].x)) {
                ymin = points[i].y;
                min = i;
            }
        }

        // Place the bottom-most point at first position
        swap(points, 0, min);

        // Sort n-1 points with respect to the first point.  A point p1 comes
        // before p2 in sorted output if p2 has larger polar angle (in counterclockwise
        // direction) than p1
        p0 = points[0];

        System.out.print("选最左下起点后排序");
        printPoints(points);
        Arrays.sort(points, 1, n, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                // If two points have same orientation, the point closer to p0 is selected
                int o = orientation(p0, p1, p2);
                if (o == 0) {
                    return (distSq(p0, p2) >= distSq(p0, p1)) ? -1 : 1;
                } else {
                    return o == 1 ? 1 : -1;
                }
            }
        });
        System.out.print("按角度逆时针排序后");
        printPoints(points);

        // Create an empty stack and push first three points to it.
        Stack<Point> S = new Stack<Point>();
        S.push(points[0]);
        S.push(points[1]);
        S.push(points[2]);

        // Process remaining n-3 points
        for (int i = 3; i < n; i++) {
            // Keep removing top while the angle formed by points next-to-top,
            // top, and points[i] makes a non-left turn
            while (S.size() > 1 && orientation(nextToTop(S), S.peek(), points[i]) != 2) {
                S.pop();
            }
            S.push(points[i]);
        }

        // Now stack has the output points, print contents of stack
        System.out.print("最终结果");
        while (!S.empty()) {
            Point p = S.peek();
            System.out.print("(" + p.x + ", " + p.y + ")");
            S.pop();
        }
    }

    private void printPoints(Point[] points) {
        for (Point point : points) {
            System.out.print("(" + point.x + ", " + point.y + ")");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Point[] points = {new Point(0, 3), new Point(1, 1), new Point(2, 2), new Point(4, 4),
                          new Point(0, 0), new Point(1, 2), new Point(3, 1), new Point(3, 3)};
        int n = points.length;
        GrahamScan g = new GrahamScan();
        g.convexHull(points, n);
    }
}
