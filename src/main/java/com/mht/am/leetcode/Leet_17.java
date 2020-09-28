package com.mht.am.leetcode;

/**
 * @classname: Leet_17
 * @description: 剑指 Offer 17. 打印从1到最大的n位数
 * @author: am
 * @create: 2020-09-28 15:26
 **/
public class Leet_17 {
    public int[] printNumbers(int n) {
        int max=(int) myPow2(10,n);
        int [] arr=new int[max-2];
        for(int i=0;i<arr.length;i++){
            arr[i]=i+1;
        }
        return arr;
    }
    public double myPow2(double x, int n) {
        if (n==0){
            return 1;
        }
        if (n<0) return 1/x*myPow2(1/x,-n-1);
        return n%2==0?myPow2(x*x,n/2):x*myPow2(x*x,n/2);
    }
}
