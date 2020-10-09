package com.mht.am.leetcode.common;

/**
 * @classname: Utils
 * @description: 工具类
 * @author: am
 * @create: 2020-09-30 10:39
 **/
public class Utils {
    public static void swap(int[] arr,int pre,int last){
        if (pre==last) return;
        arr[pre]=arr[last]^arr[pre];
        arr[last]=arr[last]^arr[pre];
        arr[pre]=arr[last]^arr[pre];
    }
}
