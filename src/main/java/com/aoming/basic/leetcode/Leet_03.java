package com.aoming.basic.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 无重复字符的最长子串:todo 滑动窗口
 * @Author: aoming
 * @Date: 2022/4/6 15:54
 * @Version: 1.0
 */
public class Leet_03 {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left=0;
        int max=0;
        if (s.length()==0)return max;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))){
                left=Math.max(left,map.get(s.charAt(i))+1);
            }
            map.put(s.charAt(i),i);
            max=Math.max(max,i-left+1);
        }
        return max;
    }
}
