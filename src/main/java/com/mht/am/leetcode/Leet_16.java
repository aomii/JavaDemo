package com.mht.am.leetcode;

/**
 * @classname: Leet_16
 * @description: 数值的整数次方
 * @author: am
 * @create: 2020-09-28 14:43
 **/
public class Leet_16 {
    public double myPow(double x, int n) {
        double result = 1.0;
        for (int i = n; i != 0; i /= 2, x *= x) {
            if (i % 2 != 0) {
                //i是奇数
                result *= x;
            }
        }
        return n < 0 ? 1.0 / result : result;
    }

    /**
    * @Description: 递归
    * @Param: [x, n]
    * @return: double
    * @Author: am
    * @Date: 2020/9/28
    */
    public double myPow2(double x, int n) {
        if (n==0){
            return 1;
        }
        if (n<0) return 1/x*myPow2(1/x,-n-1);
        return n%2==0?myPow2(x*x,n/2):x*myPow2(x*x,n/2);
    }

}
