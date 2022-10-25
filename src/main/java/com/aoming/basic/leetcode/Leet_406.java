package com.aoming.basic.leetcode;/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 406. 根据身高重建队列 </p>
 *
 * @author aoming
 * @version x.x.x
 * @email "mailto:aoming@fkhwl.com"
 * @date 2022.04.18 17:46
 * @since x.x.x
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description: 406. 根据身高重建队列
 * @Author: aoming
 * @Date: 2022/4/18 17:46
 * @Version: 1.0
 */
public class Leet_406 {

    @Test
    public void tes(){
        int [][] arrs = {{7,0},{4,4},{7,1},{5,0},{6,1},{5,2}};
        reconstructQueue(arrs);
    }


    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person2[0]-person1[0];
                } else {
                    return person1[1]-person2[1];
                }
            }
        });
        ArrayList<int[]> list = new ArrayList<int[]>();
        for (int[] p : people) {
            if (list.size() <= p[1]){
                list.add(p);
            }else {
                list.add(p[1],p);
            }
        }
        return  list.toArray(new int[list.size()][2]);

    }
}


