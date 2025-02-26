package com.aoming.basic.algorithm;

public class LongestMountain {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 最长山脉的长度
     * @param nums int整型一维数组 每个元素表示一座山的高度
     * @return int整型
     */
    public int maxLength (int[] nums) {
        // write code here
        int[] numlengths = new int[nums.length];
        numlengths[0]=1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1]<nums[i]){
                numlengths[i]=numlengths[i-1]+1;
            }else if (nums[i-1]>nums[i]){
                numlengths[i]=1;
                int j=i;
                while (j>=1){
                    if (numlengths[--j]>1){
                        numlengths[j]++;
                        break;
                    }
                }
            }else if (nums[i-1]==nums[i]  || (i==nums.length-1 && numlengths[i]>1)){
                numlengths[i]=1;
                int j=i;
                while (numlengths[--j]>1){
                    numlengths[j]=1;
                }
            }
        }

        int maxlength=1;
        for (int i = 1; i < numlengths.length-1; i++) {
            if (numlengths[i]>maxlength){
                maxlength=numlengths[i];
            }
        }
        if (maxlength<3){
            return 0;
        }else {
            return maxlength;
        }
    }

    public static void main(String[] args) {
//        System.out.println(new Test().maxLength(new int[]{2, 5, 2, 1, 5}));
//        System.out.println(new Test().maxLength(new int[]{2, 5, 2, 1, 5,6,7,6,5,6}));
//        System.out.println(new Test().maxLength(new int[]{2, 5,1}));
        System.out.println(new LongestMountain().maxLength(new int[]{4,5,5,5,5}));
        System.out.println(new LongestMountain().maxLength2(new int[]{4,5,5,5,5}));
    }


    //方式二，ai优化后
    public int maxLength2(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }

        int n = nums.length;
        int[] up = new int[n];
        int[] down = new int[n];

        // 从左到右，记录递增长度
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up[i] = up[i - 1] + 1;
            }
        }

        // 从右到左，记录递减长度
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                down[i] = down[i + 1] + 1;
            }
        }

        int maxLength = 0;
        // 计算最大山脉长度
        for (int i = 0; i < n; i++) {
            if (up[i] > 0 && down[i] > 0) {
                maxLength = Math.max(maxLength, up[i] + down[i] + 1);
            }
        }

        return maxLength >= 3 ? maxLength : 0;
    }
}
