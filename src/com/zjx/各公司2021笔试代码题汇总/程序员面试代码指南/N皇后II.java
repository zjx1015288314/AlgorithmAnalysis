package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南;

/**
 * @author zhaojiexiong
 * @create 2020/7/25
 * @since 1.0.0
 */
public class N皇后II {
    public static void main(String[] args) {
        System.out.println(num1(3));
    }

    public static int num1(int n){
        if(n < 1) return 0;
        int[] record = new int[n];
        return process(0,record,n);
    }
    public static int process(int i, int[] record, int n){
        if(i == n){
            return 1;
        }
        int res = 0;
        for(int j = 0; j < n; j++){
            if(isValid(record,i,j)){
                record[i] = j;
                res += process(i + 1, record, n);
            }
        }
        return res;
    }

    public static boolean isValid(int[] record, int i, int j){
        for (int k = 0; k < i; k++){
            if(j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)){
                return false;
            }
        }
        return true;
    }
}
