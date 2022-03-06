package com.zjx.各公司2021笔试代码题汇总.字节;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 小强有一个长度为n的整数数组a和一个数字m，他要在数组a中选择一个子序列，
 * 使得这个子序列的和相对m取模后的结果最大，子序列可以为空，请问这个结果最大为多少
 * input:
 *     第一行 两个正整数n, m
 * 	第二行 n个正整数a1,a2,a3,...,an
 * 	1 <= n <= 35, 1 <= m <= 1000000000, 1 <= ai <= 1000000000
 *
 * output:
 *     输出一行一个整数，代表答案
 * https://blog.csdn.net/xiao_ma_nong_last/article/details/105734193
 */
public class BD2021Test数组最大取余 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);

        int[] arr = new int[N];
        str = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }
        process(arr,0, 0, M,new ArrayList<>());
        System.out.println(res);
        System.out.println(maxValue);
    }

    private static List<Integer> res = null;
    private static int maxValue = Integer.MIN_VALUE;
    private static void process(int[] arr, long sum,int i, int dest,List<Integer> list) {
        if (i == arr.length){
            if(sum % dest > maxValue){
                maxValue = (int) (sum % dest);
                res = new ArrayList<>(list);
            }
            return ;
        }
        list.add(arr[i]);
        process(arr,sum + arr[i],i + 1, dest,list);
        list.remove(list.size() - 1);
        process(arr, sum, i + 1, dest,list);
    }
}
