package com.mht.am.arithmetic.sort;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * @classname: TenSort
 * @description: 排序
 * @author: am
 * @create: 2020-08-11 09:57
 **/
public class TenSort {

    public static void main(String[] args) {

        long beginTime=System.nanoTime();
        int[] arr={4,6,1,3,2,5,9,8,7,0};
     //   int[] arr={0,1,2,3,4,5,6,7,8,9};

        //1冒泡
//        bubble(arr);

        //2选择
//        select(arr);

        //3.插入
//        insert(arr);

        //4.二分插入
//        binaryInsert(arr);

        //5.希尔
//        xier(arr);

        //6.堆
//        heap(arr);

        //7.快排
//        quick(arr);

        //8.归并
//        arr=merge(arr);

        //9.基数
//        radix(arr);

        //10.计数
//        count(arr);


        //打印
        println(arr,beginTime);

    }

    private static int[] count(int[] arr) {
        if (arr==null || arr.length<2) return arr;
        int max=arr[0],min=arr[0];
        for (int i = 0; i < arr.length; i++) {
            max=Math.max(max,arr[i]);
            min=Math.min(min,arr[i]);
        }
        int num=max-min+1;
        int [] bucket=new int[num];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]-min]++;
        }
        int index=0,i=0;
        while (index<arr.length){
            if (bucket[i]!=0){
                arr[index++]=i+min;
                bucket[i]--;
            }else {
                i++;
            }
        }
        return arr;


    }

    private static int[] radix(int[] arr) {
        if (arr==null || arr.length<2) return arr;
        //计算最大位数
        int max=arr[0];
        for (int i = 1; i < arr.length; i++) {
            max=Math.max(max,arr[i]);
        }
        int maxDigit=0;
        while (max!=0){
            max/=10;
            maxDigit++;
        }
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            bucketList.add(new ArrayList<Integer>());
        }
        //按位排序
        int mod=10,div=1;
        for (int i = 0; i <maxDigit ; i++,mod*=10,div*=10) {
            for (int j = 0; j <arr.length ; j++) {
                int num=arr[j]%mod/div;
                bucketList.get(num).add(arr[j]);
            }
            //收集
            int index=0;
            for (int j = 0; j < bucketList.size(); j++) {
                for (int k = 0; k < bucketList.get(j).size(); k++) {
                    arr[index++]= bucketList.get(j).get(k);
                }
                bucketList.get(j).clear();
            }
        }
        return arr;
    }

    private static int[] merge(int[] arr) {
        return new Merge().mergeSort(arr);
    }

    private static void quick(int[] arr) {
        new Quick().quickSort(arr);
    }

    private static void heap(int[] arr) {
        new Heap().heapSort(arr);
    }


    private static void xier(int[] arr) {
        int d=arr.length;
        while (true){
            d=d/2;
            for (int i = 0; i <d; i++) {
                for (int j = i; j+d <arr.length ;j+=d) {
                    for (int k = i; k+d <arr.length-j ; k+=d) {
                        if (arr[k]>arr[k+d]){
                            swap(arr,k,k+d);
                        }
                    }
                }
            }
            if (d==1) break;
        }
    }

    private static void binaryInsert(int[] arr) {
        for (int i = 1; i <arr.length ; i++) {
            int left=0,right=i-1;
            while (left<=right){
                int target=(left+right)/2;
                if (arr[i]>arr[target]){
                    left=target+1;
                }else {
                    right=target-1;
                }
            }
            if (i!=left){
                int temp=arr[i];
                for (int j = i-1; j >=left ; j--) {
                    arr[j+1]=arr[j];
                }
                arr[left]=temp;
            }

        }
    }

    private static void insert(int[] arr) {
        for (int i = 1; i <arr.length ; i++) {
            int temp=arr[i];
            for (int j = i-1; j >=0 ; j--) {
                if (temp>arr[j]){
                    arr[j+1]=temp;
                    break;
                }else {
                    arr[j+1]=arr[j];
                }
                if (j==0){
                    arr[0]=temp;
                }
            }
        }
    }

    private static void select(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            int min=arr[i];
            int minIndex=i;
            for (int j = i+1; j <arr.length ; j++) {
                if (arr[j]<min){
                    min=arr[j];
                    minIndex=j;
                }
            }
            if (i!=minIndex){
                swap(arr,i,minIndex);
            }
        }
    }

    private static void bubble(int[] arr) {
        for (int i = 0; i <arr.length-1 ; i++) {
            for (int j = 0; j <arr.length-i-1 ; j++) {
                if (arr[j]>arr[j+1]){
                    //下标为j和j+1交换位置
                    swap(arr,j,j+1);
                }
            }
        }
    }


    /** 
    * @Description:
    * @Param: [arr] 
    * @return: void 
    * @Author: am 
    * @Date: 2020/8/11 
    */ 
    private static void println(int[] arr,long beginTime) {
        StringBuilder sb = new StringBuilder("[");
        for (int i:arr
        ) {
            sb.append(arr[i]+",");
        }
        sb.deleteCharAt(sb.length()-1).append("]");
        System.out.println(sb.toString());
        System.out.println("用时:"+(System.nanoTime()-beginTime)+"ns");
    }

    /**
    * @Description: 交换元素
    * @Param: [arr, index1, index2]
    * @return: void
    * @Author: am
    * @Date: 2020/8/12
    */
    private static void swap(int[] arr, int index1, int index2) {
        if (index1==index2) return;
        arr[index1]=arr[index1]^arr[index2];
        arr[index2]=arr[index1]^arr[index2];
        arr[index1]=arr[index1]^arr[index2];
    }
}


class Heap{
    public void heapSort(int[] arr){
        if (arr==null || arr.length==0) return;
        //构建最大堆
        buildMaxHeap(arr);
        for (int i = arr.length-1; i >0 ; i--) {
            swap(arr,i,0);
            maxHeap(arr,i,0);
        }
    }

    private void buildMaxHeap(int[] arr) {
        int half=(arr.length-1)/2;
        for (int i = half; i >=0; i--) {
            maxHeap(arr,arr.length,i);
        }
    }

    private void maxHeap(int[] arr, int length, int index) {
        int left=2*index+1;
        int right=2*index+2;
        int target=index;
        if (left<length && arr[left]<arr[target]){
            target=left;
        }
        if (right<length && arr[right]<arr[target]){
            target=right;
        }
        if (target!=index){
            swap(arr,index,target);
            maxHeap(arr,length,target);
        }
    }

    private void swap(int[] arr, int index1, int index2) {
        if (index1==index2) return;
        arr[index1]=arr[index1]^arr[index2];
        arr[index2]=arr[index1]^arr[index2];
        arr[index1]=arr[index1]^arr[index2];
    }
}

class Quick{
    public void quickSort(int[] arr) {
        if (arr==null || arr.length==0) return;
        quick(arr,0,arr.length-1);
    }

    private void quick(int[] arr, int low, int high) {
        if (low<high){
            int middle=getMiddle(arr,low,high);
            quick(arr,low,middle-1);
            quick(arr,middle+1,high);
        }
    }

    private int getMiddle(int[] arr, int low, int high) {
        int temp=arr[low];
        while (low<high){
            while (low<high && arr[high]>=temp){
                high--;
            }
            arr[low]=arr[high];
            while (low<high && arr[low]<=temp){
                low++;
            }
            arr[high]=arr[low];
        }
        arr[low]=temp;
        return low;
    }
}

class Merge{
    public int[] mergeSort(int[] arr) {
        if (arr.length<2) return arr;
        int mid=arr.length/2;
        int[] left= Arrays.copyOfRange(arr,0,mid);
        int[] right=Arrays.copyOfRange(arr,mid,arr.length);
        return  merge(mergeSort(left),mergeSort(right));
    }

    private int[] merge(int[] left, int[] right) {
        int[] result=new int[left.length+right.length];
        for (int index = 0,i=0,j=0; index<result.length ; index++) {
            if (i>=left.length){
                result[index]=right[j++];
            }else if (j>=right.length){
                result[index]=left[i++];
            }else if (left[i]>right[j]){
                result[index]=right[j++];
            }else {
                result[index]=left[i++];
            }
        }
        return result;
    }
}
