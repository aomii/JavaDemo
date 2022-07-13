package com.mht.am.leetcode.thread;/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description: 394. 字符串解码 </p>
 *
 * @author aoming
 * @version x.x.x
 * @email "mailto:aoming@fkhwl.com"
 * @date 2022.06.01 21:22
 * @since x.x.x
 *
 *
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 *
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 *
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 *
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 *
 *
 */


import org.junit.Test;

import java.util.Stack;

/**
 * @Description: 394. 字符串解码
 * @Author: aoming
 * @Date: 2022/6/1 21:22
 * @since: 1.0.0
 */
public class Leet_394 {
    @Test
    public void test(){
        String s = decodeString("abc3[cd]xyz");
        System.out.println(s);
    }

    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i <s.length() ; i++) {
            if (s.charAt(i)==']'){
                String temp="";
                while (stack.peek()!='['){
                    temp=stack.pop()+temp;
                }
                stack.pop();//去掉’[‘
                String temp2="";
                while (!stack.empty() && stack.peek()>=48 && stack.peek()<=57){
                    temp2=stack.pop()+temp2;
                }
                int n= Integer.parseInt(temp2);

                for (int j = 0; j < n; j++) {
                    for (int k = 0; k <temp.length() ; k++) {
                        stack.push(temp.charAt(k));
                    }
                }
            }else {
                stack.push(s.charAt(i));
            }
        }

        String result="";
        while (!stack.empty()){
            result=stack.pop()+result;
        }
        return result;
    }
}
