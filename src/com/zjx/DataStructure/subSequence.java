package com.zjx.DataStructure;

public class subSequence {

    /*
    *最小子序列和，结合课本P30页算法4的最大子序列和而来
    *
    */
    public static int getMinSeq(int[] arr){
        int minSum = 0;
        if (arr != null && arr.length > 0){
            minSum = arr[0];
            int thisSum = 0;
            for (int i = 0; i < arr.length; i++) {
                thisSum += arr[i];
                if (thisSum < minSum)
                    minSum = thisSum;
                else if (thisSum > 0)
                    thisSum = 0;
            }
        }
        return minSum;
    }
    /*
    *
    * 最小正子序列和问题待解决
    */
    public static int getMinPositiveSeq(int[] arr){
        int minSum = 0;
        int len = arr.length - 1;
        int[] newArr = new int[len];
        for (int i = 0; i < len; i++) {
            minSum += arr[i];
            newArr[i] = minSum;
        }
        quickSort(newArr,0,len - 1);
        int min = newArr[0] >= 0? newArr[0]: newArr[len-1];
        for (int i = 1 ; i < len ; i++) {
            if (newArr[i-1] < newArr[i]){
                int tem = newArr[i] - newArr[i-1];
                if (tem < min)
                    min = tem;
            }
        }
        return min;
    }

    private static void quickSort(int[] arr,int low,int high){
        //最小正子序列和问题待解决    https://blog.csdn.net/Bonbon_wen/article/details/81353707
    }

    /**
     * 最大子序列乘积
     * 方法一：动态规划，每一步只需要记住其前一步的整数最大值和负数的最小值。
     * @param arr
     * @return
     */
    public static int getMaxSeqMulti(int[] arr){
        if(arr == null || arr.length < 0){
            return 0;
        }
        int maxMulti = arr[0];
        int posMax = arr[0];
        int negMin = arr[0];
        for(int i = 1; i < arr.length; i++){
            int tempPosMax = posMax;
            int tempNegMin = negMin;
            posMax = Math.max(arr[i],Math.max(tempPosMax * arr[i],tempNegMin * arr[i]));
            negMin = Math.min(arr[i],Math.min(tempPosMax * arr[i],tempNegMin * arr[i]));
            if(Math.max(posMax,negMin) > maxMulti){
                maxMulti = Math.max(posMax,negMin);
            }
        }
        return maxMulti;
    }
    /**
     * 最大子序列乘积
     * 方法二：
     * @param arr
     * @return
     */
    public static int getMaxSeqMulti2(int[] arr){
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < arr.length; i++){
            int temp = arr[i];
            for(int j = i + 1; j < arr.length; j++){
                max = Math.max(max,temp);
                temp *= arr[j];
            }
            max = Math.max(max,temp);
        }
        return max;
    }

}
