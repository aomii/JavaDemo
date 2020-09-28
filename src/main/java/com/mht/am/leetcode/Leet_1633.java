package com.mht.am.leetcode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @classname: Leet_1633
 * @description: 机器人的运动范围
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 * @author: am
 * @create: 2020-09-25 13:36
 **/
public class Leet_1633 {
    public int movingCount(int m, int n, int k) {
        int sum=0;
        int [][] arr=new int[m][n];
        dfs(arr,0,0,k);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j <n; j++) {
                if (arr[i][j]==-1){
                    sum++;
                }
            }
        }
        return sum;

    }
    /**
    * @Description: 深度优先 dfs （my）
    * @Param: [arr, i, j, k]
    * @return: void
    * @Author: am
    * @Date: 2020/9/25
    */
    private void dfs(int[][] arr, int i, int j, int k) {

        if ( i<0 || i>=arr.length|| j<0|| j>=arr[0].length||fsh(i)+fsh(j)>k || arr[i][j]==-1) {
            return;
        } else {
            arr[i][j] = -1;
            dfs(arr, i + 1, j, k);
            dfs(arr, i, j + 1, k);
        }
    }

    private int fsh(int n) {
        if (n<10) return n;
        return n/10+n%10;
    }





    /**
    * @Description: bfs 方式2： 广度优先
    * @Param: []
    * @return: void
    * @Author: am
    * @Date: 2020/9/25
    */
    public int movingCount2(int m, int n, int k) {
        boolean[][] visited = new boolean[m][n];
        int res = 0;
        Queue<int[]> queue= new LinkedList<int[]>();
        queue.add(new int[] { 0, 0, 0, 0 });
        while(queue.size() > 0) {
            int[] x = queue.poll();
            int i = x[0], j = x[1], si = x[2], sj = x[3];
            if(i >= m || j >= n || k < si + sj || visited[i][j]) continue;
            visited[i][j] = true;
            res ++;
            queue.add(new int[] { i + 1, j, (i + 1) % 10 != 0 ? si + 1 : si - 8, sj });
            queue.add(new int[] { i, j + 1, si, (j + 1) % 10 != 0 ? sj + 1 : sj - 8 });
        }
        return res;
    }


    @Test
    public void test(){
        int i = movingCount(2, 3, 1);
        System.out.println(i);
    }
}
