package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.连续子数组相关;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhaojiexiong
 * @create 2020/6/27
 * @since 1.0.0
 */
public class 数组中连续乘积的最大值 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        double[] arr = new double[len];
        for(int i = 0; i < len; i++){
            arr[i] = Double.parseDouble(input[i]);
        }
            System.out.print(String.format("%.2f",getMaxProduct(arr)));
    }

    private static double getMaxProduct(double[] arr){
        if(arr == null || arr.length == 0) return 0;
        double max = 1.0;
        double min = 1.0;
        double res = Double.MIN_VALUE;
        for(int i = 0; i < arr.length; i++){
            double maxEnd = max * arr[i];
            double minEnd = min * arr[i];
            max = Math.max(Math.max(maxEnd,minEnd),arr[i]);
            min = Math.min(Math.min(maxEnd,minEnd),arr[i]);
            res = Math.max(max,res);
        }
        return res;
    }
}
