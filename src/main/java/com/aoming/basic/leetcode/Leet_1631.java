package com.aoming.basic.leetcode;

/**
 * @classname: Leet_1631
 * @description: 旋转数组的最小数字
 * @author: am
 * @create: 2020-09-25 10:23
 **/
public class Leet_1631 {
    public int minArray(int[] numbers) {
        int pre=0;
        int last=numbers.length-1;
        while (pre<last){
            int mid=pre+(last-pre)/2;
            if (numbers[mid]>numbers[last]){
                pre=mid+1;
            }else if (numbers[mid]<numbers[last]){
                last=mid;
            }else {
                last--;
            }
        }
        return numbers[pre];
    }
}
