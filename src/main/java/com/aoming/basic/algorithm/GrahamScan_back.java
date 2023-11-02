package com.aoming.basic.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:  </p>
 *
 * @author aoming
 * @version x.x.x
 * @email "mailto:aoming@fkhwl.com"
 * @date 2023.10.16 15:09
 * @since x.x.x
 */


public class GrahamScan_back {
    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int L = Integer.parseInt(st.nextToken());




        final int[][] point = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            point[i][0] = Integer.parseInt(st.nextToken());
            point[i][1] = Integer.parseInt(st.nextToken());
        }

        final LinkedList targetPointsPosition = findPoints(point);
        double result = 0;
        final int size = targetPointsPosition.size();
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                result += getDistance(
                    becomeVector(point[(int) targetPointsPosition.get(i)], point[(int) targetPointsPosition.get(0)]));
                continue;
            }
            result += getDistance(
                becomeVector(point[(int) targetPointsPosition.get(i)], point[(int) targetPointsPosition.get(i + 1)]));
        }

        result += 2 * Math.PI * L;
        System.out.println(Math.round(result));
    }

    private static LinkedList findPoints(int[][] point) {
        final int[] targetPointsPosition = new int[point.length];
        final int[] leftBottom = getFirstPoint(point);
        // 对点进行排序预处理
        Arrays.sort(point, new Comparator<int[]>() {
            @Override
            public int compare(int[] ints, int[] t1) {
                final int[] o1 = becomeVector(leftBottom, ints);
                final int[] o2 = becomeVector(leftBottom, t1);
                // 比较角度的结果
                final int compareResult = myCompare(leftBottom, ints, t1);
                if (compareResult == 0) {
                    // 角度相同的时候,将距离P0点近的排在前面
                    final double dist1Pow = Math.pow(o1[0], 2) + Math.pow(o1[1], 2);
                    final double dist2Pow = Math.pow(o2[0], 2) + Math.pow(o2[1], 2);
                    return dist1Pow - dist2Pow > 0 ? 1 : -1;
                }
                return compareResult;
            }
        });

        // 执行点的扫描
        final LinkedList stack = new LinkedList();
        stack.push(0);
        stack.push(1);
        stack.push(2);

        // 角度的正负使用向量的sin值,sin在(-Π,0)为负数,(0,Π)为正数----向量的点乘
        int[] p0, p1, p2;
        int symbolNum;
        for (int i = 3; i < point.length; i++) {
            while (true) {
                final int checkPoint = (int) stack.pop();
                p0 = point[checkPoint];
                p1 = point[(int) stack.peek()];
                p2 = point[i];
                symbolNum = symbol(p0, p1, p1, p2);
                if (symbolNum == 1) {
                    stack.push(checkPoint);
                    break;
                }
            }
            stack.push(i);
        }

        return stack;
    }

    private static int[] getFirstPoint(int[][] point) {
        int[] result = new int[]
            {Integer.MAX_VALUE, Integer.MAX_VALUE};
        for (final int[] ints : point) {
            if (ints[0] < result[0]) {
                result = ints;
            } else if (ints[0] == result[0]) {
                if (ints[1] < result[1]) {
                    result = ints;
                }
            }
        }
        return result;
    }

    // 构造向量<p0,p1>
    private static int[] becomeVector(int[] p0, int[] p1) {
        return new int[]
            {p1[0] - p0[0], p1[1] - p0[1]};
    }

    // 用于点的排序的比较器
    private static int myCompare(int[] p0, int[] p1, int[] p2) {
        final int[] vector01 = becomeVector(p0, p1);
        final int[] vector02 = becomeVector(p0, p2);
        final double cos01 = vector01[1] / getDistance(vector01);
        final double cos02 = vector02[1] / getDistance(vector02);
        if (Math.abs(cos01 - cos02) < 1e-6) {
            return 0;
        }
        // cos在[0,Π]单调递减
        return cos01 - cos02 > 0 ? -1 : 1;
    }

    // <p0,p1>,<p0,p2>向量夹角---sin---向量叉乘
    private static int symbol(int[] p0, int[] p1, int[] p2, int[] p3) {
        final int[] vector01 = becomeVector(p0, p1);
        final int[] vector23 = becomeVector(p2, p3);
        final int result = vector01[0] * vector23[1] - vector01[1] * vector23[0];
        return result >= 0 ? 1 : -1;
    }

    private static double getDistance(int[] vector) {
        return Math.sqrt(Math.pow(vector[0], 2) + Math.pow(vector[1], 2));
    }
}

