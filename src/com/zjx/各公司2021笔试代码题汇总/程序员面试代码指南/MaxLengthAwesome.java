package com.itzjx.mmall_test;

import java.util.Scanner;

/**
 * @author zhaojiexiong
 * @create 2020/6/13
 * @since 1.0.0
 */
public class MaxLengthAwesome {


    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int k = in.nextInt();   //目标值
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = in.nextInt();
        }
        //此处求累加和小于等于目标值，需要用到以i为开头的最小累加和
        int[] minSum = new int[len];
        int[] minSumRightEdge = new int[len];
        minSum[len - 1] = arr[len - 1];
        minSumRightEdge[len - 1] = len - 1;
        for (int i = len - 2; i >= 0; i--) {
            minSum[i] = minSum[i + 1] < 0 ? arr[i] + minSum[i + 1] : arr[i];
            minSumRightEdge[i] = minSum[i + 1] < 0 ? minSumRightEdge[i + 1] : i;
        }

        int end = 0;  //窗口的右边界
        int sum = 0;  //窗口中元素累加和
        int nums = 0; //满足题目条件的最小累加和
        for (int i = 0; i < len; i++) {

            while (end < len && sum + minSum[end] <= k) {
                sum += minSum[end];
                end = minSumRightEdge[end] + 1;
            }
            nums = Math.max(nums, end - i);
            if (end > i) {
                sum -= arr[i];   //弹出最左边
            } else {
                end = i + 1;
            }
        }
        System.out.print(nums);
    }
}
