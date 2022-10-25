package com.aoming.basic.leetcode;

/**
 * @classname: Leet_1630
 * @description: 青蛙跳台阶问题
 * @author: am
 * @create: 2020-09-25 09:43
 **/
public class Leet_1630 {
    public int numWays(int n) {
        if (n<2){
            return 1;
        }
        int pre=1;
        int curr=1;
        for (int i = 2; i <=n ; i++) {
            int sum=(pre+curr)%1000000007;
            pre=curr;
            curr=sum;
        }
        return curr;
    }

    /**
    * @Description: 动态规划
    * @Param:
    * @return:
    * @Author: am
    * @Date: 2020/9/25
    */
    public int numWays2(int n) {
        if (n <= 1)
            return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            dp[i] %= 1000000007;
        }
        return dp[n];
    }
}
