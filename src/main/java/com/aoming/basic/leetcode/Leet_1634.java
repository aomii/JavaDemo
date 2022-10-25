package com.aoming.basic.leetcode;

/**
 * @classname: Leet_1634
 * @description: 剪绳子
 * @author: am
 * @create: 2020-09-25 15:10
 **/
public class Leet_1634 {
    public int cuttingRope(int n) {
        int[] dp=new int[n+1];
        dp[1]=1;
        for (int i = 2; i <=n ; i++) {
            for (int j = 1; j <i ; j++) {
                dp[i]=Math.max(dp[i],Math.max(j,dp[j])*Math.max(i-j,dp[i-j]));
            }
        }
        return dp[n];
    }

    public int cuttingRope2(int n) {
        if (n == 2 || n == 3)
            return n - 1;
        int constant = 1000000007;
        long res = 1;
        while (n > 4) {
            //如果n大于4，我们不停的让他减去3
            n = n - 3;
            //计算每段的乘积
            res = res * 3 % constant;
        }
        return (int) ((res * n) % constant);
    }
}
