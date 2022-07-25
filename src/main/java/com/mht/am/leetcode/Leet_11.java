package com.mht.am.leetcode;
/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 11. 盛最多水的容器 </p>
 *
 * @author aoming
 * @version x.x.x
 * @email "mailto:aoming@fkhwl.com"
 * @date 2022.07.21 22:10
 * @since x.x.x
 */

import org.junit.Test;

/**输入：[1,8,6,2,5,4,8,3,7]
 输出：49
 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 *
 * @Description: 11. 盛最多水的容器
 * @Author: aoming
 * @Date: 2022/7/21 22:10
 * @since: 1.0.0
 */
public class Leet_11 {
    @Test
    public void test(){
        int[] arr={1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea2(arr));
    }

    //暴力-要超时
    public int maxArea(int[] height) {
        int l = height.length;
        int max=0;
        for (int i = 0; i < l; i++) {
            for (int j = i+1; j <l ; j++) {
                int h=Math.min(height[i],height[j]);
                int temp=(j-i)*h;
                max=Math.max(max,temp);
            }
        }
        return max;
    }

    //双指针
    public int maxArea2(int[] height) {
        int left=0;
        int right=height.length-1;
        int ans=0;
        while (left<right){
            int ares=Math.min(height[left],height[right])*(right-left);
            ans=Math.max(ares,ans);
            if (height[left]<=height[right]){
               left++;
            }else {
                right--;
            }
        }
        return ans;
    }
}
