package com.mht.am.leetcode;

import org.junit.Test;

/**
 * @author com.mhout.am
 * @version 1.0.0
 * @ClassName: Leet_10
 * @Description: TODO* 正则表达  我们用 f[i][j]f[i][j] 表示 s 的前 i 个字符与 p中的前 j 个字符是否能够匹配
 * @date 2021/1/8
 */
public class Leet_10 {
    @Test
    public void test(){
        System.out.println(isMatch("abccca", "a.c*a"));

    }


    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] f = new boolean[m + 1][n + 1];
        f[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (p.charAt(j - 1) == '*') {
                    f[i][j] = f[i][j - 2];
                    if (matches(s, p, i, j - 1)) {
                        f[i][j] = f[i][j] || f[i - 1][j];
                    }
                } else {
                    if (matches(s, p, i, j)) {
                        f[i][j] = f[i - 1][j - 1];
                    }
                }
            }
        }
        return f[m][n];
    }

    public boolean matches(String s, String p, int i, int j) {
        if (i == 0) {
            return false;
        }
        if (p.charAt(j - 1) == '.') {
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }







    public boolean isMatch2(String s, String p) {
        int m=s.length();
        int n=s.length();
        boolean [][] f=new boolean[m+1][n+1];
        f[0][0]=true;
        for (int i = 0; i <=m ; i++) {
            for (int j = 1; j <=n; j++) {
                if (p.charAt(j-1)=='*'){
                    if (s.charAt(i)==p.charAt(j-1)){
                        f[i][j]=f[i-1][j] || f[i][j-2];
                    }else {
                        f[i][j]=f[i][j-2];
                    }
                }else {
                    if (matches(s,p,i,j)){
                        f[i][j]=f[i-1][j-1];
                    }
                }
            }
        }
        return f[m][n];

    }
}
