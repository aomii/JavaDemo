package com.aoming.interview.huawei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * <p>Company: 成都返空汇网络技术有限公司</p>
 * <p>Description:  </p>
 *
 * @author aoming
 * @version 1.0.0
 * @email "mailto:aoming@fkhwl.com"
 * @date 2025.08.04 16:16
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String s=in.nextLine();

        StringBuffer strbuf=new StringBuffer();

        strbuf.append(s).reverse();

        Map<Character,Integer> map=new HashMap<Character,Integer>();

        for(int i=0;i<strbuf.length();i++){
            if(map.containsKey(strbuf.charAt(i))){
                continue;
            }else{
                map.put(strbuf.charAt(i),1);
                System.out.print(strbuf.charAt(i));
            }
        }
    }
}
