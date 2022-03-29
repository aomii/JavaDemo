package com.mht.am.leetcode;

import com.mht.am.leetcode.common.Utils;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * TODO
 * 两数之和
 * @author ao921
 * @version 1.0
 * @date 2022/3/14 11:39
 */
public class Leet_01 {

    @Test
    public void test(){
        int [] mums={-1,-2,-3,-4,-5};
        int[] result = twoSum2(mums, -8);
        for (int i : result) {
            System.out.println(i+" ");
        }
    }

    //leetCode 01 两数之和01  O(N平方)

    public int[] twoSum(int[] nums, int target) {
        int [] result=new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j <nums.length ; j++) {
                if (nums[i]+nums[j]==target){
                    result[0]=i;
                    result[1]=j;
                }
            }
        }
        return result;
    }

    //leetCode 01 两数之和01  O(NlogN)

    public int[] twoSum2(int[] nums, int target) {
        int[] array = new int[nums.length];
        System.arraycopy(nums,0,array,0,nums.length);
        Arrays.sort(nums);
        int [] result={-1,-1};
        for (int i = 0; i < nums.length-1; i++) {
//            int j= Utils.binaryFind(nums,i+1,nums.length-1,target-nums[i]);
            int j= Arrays.binarySearch(nums,i+1,nums.length,target-nums[i]);
            if (j>=0){
                result[0]= Utils.getIndex(array, nums[i]);
                if (nums[j]==nums[i]){
                    result[1]=Utils.getIndex(array,nums[j],2);
                }else {
                    result[1]=Utils.getIndex(array, nums[j]);
                }
                break;
            }
        }
        return result;
    }




    /**
     *   O(n)
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum3(int[] nums, int target) {
        // 建立k-v ，一一对应的哈希表
        int[] indexs = new int[2];
        HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (hash.containsKey(nums[i])) {
                indexs[0] = i;
                indexs[1] = hash.get(nums[i]);
                return indexs;
            }
            // 将数据存入 key为补数 ，value为下标
            hash.put(target - nums[i], i);
        }
        return indexs;
    }
}
