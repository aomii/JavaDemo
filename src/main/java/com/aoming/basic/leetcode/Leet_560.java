package com.aoming.basic.leetcode;

import java.util.HashMap;

/**
 * @Description: 和为k的子数组 个数
 *
 *  思路：前缀和+ 两数之和hash
 *
 * @Author: aoming
 * @Date: 2022/4/1 16:46
 * @Version: 1.0
 */
public class Leet_560 {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> preSumFreq  = new HashMap<>();
        preSumFreq.put(0,1);
        int preSum =0;
        int count=0;
        for (int num : nums) {
            preSum+=num;
            if (preSumFreq.containsKey(preSum-k)){
                count+=preSumFreq.get(preSum-k);
            }
            preSumFreq.put(preSum,preSumFreq.getOrDefault(preSum,0)+1);

        }
        return count;
    }
}
