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

    public static int getIndex(int[] array, int num) {
        return getIndex(array,num,1);
    }

    public static int getIndex(int[] arr, int num, int key)
    {
        //遍历数组
        int count=0;
        for(int x=0;x<arr.length;x++)
        {
            if(arr[x]== num)
            {
                if (++count==key){
                    return x;
                }
            }
        }
        //没找到返回-1
        return -1;
    }

    //二分查找
    public static int binaryFind(int[]nums ,int begin, int end, int target) {
        while (begin<=end){
            int mid=begin+((end-begin) >>> 1);
            if (nums[mid]<target){
                begin++;
            }else if (nums[mid]>target){
                end--;
            }else {
                return mid;
            }
        }
        return -1;//表示没找到
    }


}
