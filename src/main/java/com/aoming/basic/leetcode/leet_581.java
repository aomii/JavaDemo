package com.aoming.basic.leetcode;

/**
 * @Description: 最短无序连续子数组
 *
 * 给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 *
 * 请你找出符合题意的 最短 子数组，并输出它的长度。
 *
 *  todo 正序和倒序插入排序最后一个需要移动的值 为左右边界 因为后面的值都是有序的不需要再排列了
 *
 * 示例 1：
 *
 * 输入：nums = [2, 6,4,8,10,9,15]
 * 输出：5
 * 解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 *
 *
 * @Author: aoming
 * @Date: 2022/4/2 15:54
 * @Version: 1.0
 */
public class leet_581 {
    public int findUnsortedSubarray(int[] nums) {
        int len=nums.length;
        int begin=0;
        int end=-1;
        int min=nums[len-1];
        int max=nums[0];

        for (int i = 1; i < len; i++) {
            if (nums[i]>=max){
                max=nums[i];
            }else {
                end=i;
            }

            if (nums[len-i-1]<= min){
                min=nums[len-i-1];
            }else {
                begin=len-i-1;
            }
        }
        return end-begin+1;
    }
}
