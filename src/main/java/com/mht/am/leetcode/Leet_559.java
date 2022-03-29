package com.mht.am.leetcode;

import org.junit.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *两个列表的最小索引总和
 * @author ao921
 * @version 1.0
 * @date 2022/3/14 11:51
 */
public class Leet_559 {
    @Test
    public void test(){
        String [] list1={"Shogun","Tapioca Express","Burger King","KFC"};
        String [] list2={"KFC","Shogun","Burger King"};
        System.out.println(findRestaurant(list1, list2));
    }


    /*
     *
     * todo O(m*n)
     * @param list1
     * @param list2
     * @author ao921
     * @date 2022/3/14 12:26
     * @return java.lang.String[]
     */
    public String[] findRestaurant(String[] list1, String[] list2) {
        int indexNum=Integer.MAX_VALUE;
        ArrayList<String> strings = new ArrayList<String>();
        for (int i = 0; i < list1.length; i++) {
            for (int j = 0; j < list2.length; j++) {
                if (list1[i].equals(list2[j])){
                    if (i+j<indexNum){
                        indexNum=i+j;
                        strings.clear();
                        strings.add(list1[i]);
                    }else if (i+j==indexNum){
                        strings.add(list1[i]);
                    }
                }
            }
        }
        return strings.toArray(new String[strings.size()]);
    }

    /**
     * todo O(max(m,n)) 更好
     * @param list1
     * @param list2
     * @author ao921
     * @date 2022/3/14 12:25
     * @return java.lang.String[]
     */
    public String[] findRestaurant2(String[] list1, String[] list2) {
        Map<String, Integer> index = new HashMap<String, Integer>();
        for (int i = 0; i < list1.length; i++) {
            index.put(list1[i], i);
        }
        List<String> ret = new ArrayList<String>();
        int indexSum = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length; i++) {
            if (index.containsKey(list2[i])) {
                int j = index.get(list2[i]);
                if (i + j < indexSum) {
                    ret.clear();
                    ret.add(list2[i]);
                    indexSum = i + j;
                } else if (i + j == indexSum) {
                    ret.add(list2[i]);
                }
            }
        }
        return ret.toArray(new String[ret.size()]);
    }
}
