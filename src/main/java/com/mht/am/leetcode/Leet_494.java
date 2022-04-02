package com.mht.am.leetcode;

/**
 * @Description: 目标和
 *
 *
 * 给你一个整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 *
 * @Author: aoming
 * @Date: 2022/4/2 17:21
 * @Version: 1.0
 */
public class Leet_494 {

    int count=0;
    public int findTargetSumWays(int[] nums, int target) {
        backtrack(nums,target,0,0);
        return count;
    }

    private void backtrack(int[] nums, int target, int index, int sum) {
        if (index==nums.length){
            if (sum== target){
                count++;
            }
        }else {
            backtrack(nums,target,index+1,sum+nums[index]);
            backtrack(nums,target,index+1,sum-nums[index]);
        }
    }


    //法二 动态规划
    public int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int neg = diff / 2;
        int[] dp = new int[neg + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = neg; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[neg];
    }

}
