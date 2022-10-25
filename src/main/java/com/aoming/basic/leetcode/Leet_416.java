package com.aoming.basic.leetcode;

/**
 * @Description: 416. 分割等和子集
 * @Author: aoming
 * @Date: 2022/4/14 17:53
 * @Version: 1.0
 *
 * 分析：1、总和必须为偶数才可能能够分割。奇数直接false
 */
public class Leet_416 {

    //动态规划 dp[i][j]表示从数组的 [0, i] 这个子区间内挑选一些正整数，每个数只能用一次，使得这些数的和恰好等于 j;存在：true
//    时间复杂度：O(NC)O(NC)：这里 NN 是数组元素的个数，CC 是数组元素的和的一半。
//    空间复杂度：O(NC)O(NC)

    public boolean canPartition(int[] nums) {
        int len=nums.length;
        int sum=0;
        for (int num : nums) {
            sum+=num;
        }
        if ((sum & 1) ==1){
            return false;
        }
        int target= sum >>> 1;
        boolean[][] dp = new boolean[len][target + 1];
        if (nums[0]<=target){
            dp[0][nums[0]]=true;
        }
        for (int i = 1; i <len ; i++) {
            for (int j = 0; j <= target; j++) {
                dp[i][j]=dp[i-1][j];
                if (nums[i]==target) {
                    dp[i][j]=true;
                    continue;
                }
                if (nums[i]<j) dp[i][j]=dp[i][j] || dp[i-1][j-nums[i]];
            }
        }
        return dp[len-1][target];
    }



    //方法二.滚动数组优化空间
//    时间复杂度：O(NC)O(NC)：这里 NN 是数组元素的个数，CC 是数组元素的和的一半；
//    空间复杂度：O(C)O(C)：减少了物品那个维度，无论来多少个数，用一行表示状态就够了。
    public boolean canPartition2(int[] nums) {
        int len = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if ((sum & 1) == 1) {
            return false;
        }

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        if (nums[0] <= target) {
            dp[nums[0]] = true;
        }
        for (int i = 1; i < len; i++) {
            for (int j = target; nums[i] <= j; j--) {
                if (dp[target]) {
                    return true;
                }
                dp[j] = dp[j] || dp[j - nums[i]];
            }
        }
        return dp[target];
    }
}
