package com.mht.am.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 任务调度器
 * @Author: aoming
 * @Date: 2022/3/31 15:48
 * @Version: 1.0
 */
public class leet_621 {

    @Test
    public void test() {
        char[] chars={'A','A','A','B','B','B'};
        System.out.println(leastInterval(chars, 2));
    }

    /**
     * @author aoming
     *
     * 时间复杂度：O(|\textit{task}| + |\Sigma|)O(∣task∣+∣Σ∣)，其中 |\Sigma|∣Σ∣ 是数组 \textit{task}task 中出现任务的种类，
     *           在本题中任务用大写字母表示，因此 |\Sigma|∣Σ∣ 不会超过 2626。
     * 空间复杂度：O(|\Sigma|)O(∣Σ∣)。
     *
     *
     *
     *
     * @date 2022/3/31 15:59 
     * @param  [tasks, n] 
     * @return int
     */
    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> map = new HashMap<>();
        int maxTask=0;
        for (char task : tasks) {
            int value=map.getOrDefault(task,0)+1;
            map.put(task,value);
            maxTask=Math.max(maxTask,value);
        }
        int maxCount=0;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue()==maxTask){
                maxCount++;
            }
        }
        return Math.max(tasks.length,(maxTask-1)*(n+1)+maxCount);
    }
}
