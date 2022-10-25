package com.aoming.basic.leetcode;

/**
 * @classname: Leet_1636
 * @description: 二进制中1的个数
 * @author: am
 * @create: 2020-09-25 17:14
 **/
public class Leet_1636 {
    public int hammingWeight(int n) {
        int res = 0;
        while(n != 0) {
            res += n & 1;
            n >>>= 1;
        }
        return res;
    }
}
