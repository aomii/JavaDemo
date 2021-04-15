package com.mht.am.leetcode.labuladong;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author com.mhout.am
 * @version 1.0.0
 * @ClassName: lbld_2_12
 * @Description: TODO 高楼丢鸡蛋
 * @date 2021/1/20
 */
public class lbld_2_12 {
    HashMap<String,Integer> map = new LinkedHashMap<>();
    public int superEggDrop(int K,int N){

        return  dp(K,N);

    }

    private int dp(int k, int n) {
        if (k==1) return n;
        if (n==0) return 0;
        if (map.containsKey(k+"-"+n)) return map.get(k+"-"+n);

        int res=Integer.MAX_VALUE;
        for (int i = 1; i < n+1; i++) {
            res=Math.min(res,Math.max(dp(k,n-i),dp(k-1,i-1))+1);
        }
        map.put(k+"-"+n,res);
        return res;
    }

    @Test
    public void test(){
        System.out.println(superEggDrop(3,10));
        System.out.println("aa");
    }
}
