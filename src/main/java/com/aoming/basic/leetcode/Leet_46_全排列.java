package com.aoming.basic.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 全排列 </p>
 *
 * @author aoming
 * @version x.x.x
 * @email "mailto:aoming@fkhwl.com"
 * @date 2023.10.11 14:14
 * @since x.x.x
 */
@Slf4j
public class Leet_46_全排列 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums != null && nums.length > 0) {
            permutationHelper(nums, 0, result);
        }
        return result;
    }

    private void permutationHelper(int[] nums, int cursor, List<List<Integer>> result) {
        if (cursor != nums.length - 1) {
            for (int j = cursor; j < nums.length; j++) {
                //判断j对应下标的元素在之前是否已交换过，如交换则调过
                if (swapAccepted(nums, cursor, j)) {
                    continue;
                }
                //交换下标为cursor和j的元素
                swap(nums, cursor, j);
                //递归调用cursor+1
                permutationHelper(nums, cursor + 1, result);
                swap(nums, cursor, j);
            }
        } else {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            result.add(list);
        }
    }

    private void swap(int[] nums, int cursor, int j) {
        if (cursor == j) {
            return;
        }
        nums[cursor] = nums[cursor] ^ nums[j];
        nums[j] = nums[cursor] ^ nums[j];
        nums[cursor] = nums[cursor] ^ nums[j];
    }

    private boolean swapAccepted(int[] nums, int cursor, int end) {
        for (int i = cursor; i < end; i++) {
            if (nums[i] == nums[end]) {
                return true;
            }
        }
        return false;
    }

    @Test
    public  void test() {
        int[] array={1,2,3,3};
        List<List<Integer>> permute = this.permute(array);
        String s = JSONUtil.toJsonStr(permute);
        log.info(s);
    }
}