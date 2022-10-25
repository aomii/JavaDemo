package com.aoming.basic.leetcode;

/**
 * @classname: Leet_19
 * @description:正则表达式匹配
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 *
 * @author: am
 * @create: 2020-09-28 16:22
 **/
public class Leet_19 {

    /**
    * @Description: 递归
    * @Param: [s, p]
    * @return: boolean
    * @Author: am
    * @Date: 2020/9/28
    */
    public boolean isMatch(String s, String p) {
        if (p.length() == 0) {
            return s.length() == 0;
        }
        if (p.length() > 1 && p.charAt(1) == '*') {
            // p的第二个字符是 '*'
            //1,字符"*"把前面的字符消掉，也就是匹配0次
            //2,字符"*"匹配1次或多次
            return isMatch(s, p.substring(2)) || (s.length() > 0 && comp(s, p)) && isMatch(s.substring(1), p);
        } else {
            // p的第二个字符不是 '*'，判断首字符是否相同，如果相同再从第二位继续比较
            return s.length() > 0 && comp(s, p) && (isMatch(s.substring(1), p.substring(1)));
        }
    }

    //比较s的首字符和p的首字符是否匹配
    private boolean comp(String s, String p) {
        return s.charAt(0) == p.charAt(0) || p.charAt(0) == '.';
    }

    /** 
    * @Description: dp 
    * @Param: [s, p] 
    * @return: boolean 
    * @Author: am 
    * @Date: 2020/9/28 
    */ 
    public boolean isMatch2(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++)
            for (int j = 1; j <= n; j++)
                if (p.charAt(j - 1) == '*')
                    dp[i][j] = dp[i][j - 2] || (i > 0 && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') && dp[i - 1][j]);
                else
                    dp[i][j] = i > 0 && dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
        return dp[m][n];
    }
}
