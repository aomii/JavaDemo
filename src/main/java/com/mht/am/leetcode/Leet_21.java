package com.mht.am.leetcode;

import com.mht.am.leetcode.common.Utils;

/**
 * @classname: Leet_21
 * @description: 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
 * @author: am
 * @create: 2020-09-30 09:55
 **/
public class Leet_21 {
    public int[] exchange(int[] nums) {
        int i=0;
        int j=nums.length-1;

        while(i<j ){
            if((nums[i]&1)==1){
                i++;
                continue;
            }
            if((nums[j]&1)==0){
                j--;
                continue;
            }
            nums[i]=nums[i]^nums[j];
            nums[j]=nums[i]^nums[j];
            nums[i]=nums[i]^nums[j];
        }
        return nums;
    }

    /**
    * @Description: 方式二 ，快慢指针
    * @Param: [nums]
    * @return: int[]
    * @Author: am
    * @Date: 2020/9/30
    */
    public int[] exchange2(int[] nums) {
        int low = 0, fast = 0;
        while (fast < nums.length) {
            if ((nums[fast] & 1)==1) {
                Utils.swap(nums,low,fast);
                low ++;
            }
            fast ++;
        }
        return nums;
    }

}
