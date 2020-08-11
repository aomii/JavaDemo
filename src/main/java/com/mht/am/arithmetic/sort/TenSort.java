package com.mht.am.arithmetic.sort;

/**
 * @classname: TenSort
 * @description: 排序
 * @author: am
 * @create: 2020-08-11 09:57
 **/
public class TenSort {
    public static void main(String[] args) {
        //1冒泡
        int[] arr={4,6,1,3,2,5,9,8,7,0};
        bubble(arr);

        //2选择
        select(arr);

        //3.插入
        insert(arr);

        //4.二分插入
        binaryInsert(arr);
    }

    private static void binaryInsert(int[] arr) {
    }

    private static void insert(int[] arr) {
    }

    /** 
    * @Description:  
    * @Param: [arr] 
    * @return: void 
    * @Author: am 
    * @Date: 2020/8/11 
    */ 
    private static void select(int[] arr) {

    }


    /** 
    * @Description:  
    * @Param: [arr] 
    * @return: void 
    * @Author: am 
    * @Date: 2020/8/11 
    */ 
    private static void bubble(int[] arr) {
        for (int i = 0; i <arr.length-1 ; i++) {
            for (int j = 0; j <arr.length-i-1 ; j++) {
                if (arr[j]>arr[j+1]){
                    //下标为j和j+1交换位置
                    arr[j]=arr[j]^arr[j+1];
                    arr[j+1]=arr[j]^arr[j+1];
                    arr[j]=arr[j]^arr[j+1];
                }
            }
        }
        //打印
        println(arr);
    }


    /** 
    * @Description:
    * @Param: [arr] 
    * @return: void 
    * @Author: am 
    * @Date: 2020/8/11 
    */ 
    private static void println(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i:arr
        ) {
            sb.append(arr[i]+",");
        }
        sb.deleteCharAt(sb.length()-1).append("]");
        System.out.println(sb.toString());

    }
}
