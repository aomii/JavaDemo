package com.aoming.basic.leetcode;

import org.junit.Test;

/**
 * @classname: Leet_1623
 * @description: 数组中重复的数字
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 *

 * @author: am
 * @create: 2020-09-23 15:37
 **/
public class Leet_1623 {
    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i]!=i){
                int temp=nums[i];
                if (nums[temp]==temp){
                    return temp;
                }
                nums[i]=nums[temp];
                nums[temp]=temp;
            }

        }
        return -1;
    }
    @Test
    public void test(){
        System.out.println(findRepeatNumber(new int[]{3,2,4,4,2,0}));
    }

}
