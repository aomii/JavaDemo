package com.aoming.basic.leetcode;

import java.util.PriorityQueue;

// 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间
//[[s1，e1]，[s2，e2],],为避免会议冲突，同时要虑充分利用
//会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
public class _253_会议室2 {

    public int room(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        // 创建一个最小堆（存放每一个会议的结束时间）
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        // 添加0号会议的结束时间
        heap.add(intervals[0][1]);
        // 堆顶的含义：目前占用的会议室中最早结束的时间
        for (int i = 1; i < intervals.length; i++) { // nlogn
            // i号会议的开始时间 >= 堆顶
            if (intervals[i][0] >= heap.peek()) {
                heap.remove();
            }
            // 将i号会议的结束时间加入堆中
            heap.add(intervals[i][1]);
        }

        return heap.size();
    }
}
