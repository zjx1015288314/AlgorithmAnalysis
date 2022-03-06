package com.zjx.各公司2021笔试代码题汇总.拼多多;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 题目描述
 * 六一儿童节，老师带了很多好吃的巧克力到幼儿园。每块巧克力j的重量为w[j]，对于每个小朋友i，
 * 当他分到的巧克力大小达到h[i] (即w[j]>=h[i])，他才会上去表演节目。老师的目标是将巧克力
 * 分发给孩子们，使得最多的小孩上台表演。可以保证每个w[i]> 0且不能将多块巧克力分给一个孩子
 * 或将一块分给多个孩子。
 * 输入
 * 3
 * 2 2 3   h[i]
 * 2
 * 3 1  w[j]
 * 输出
 * 1
 * 贪心：最大的巧克力分给需求最大的孩子
 */
public class PDD2018Test巧克力 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int[] h = new int[n];
        str = br.readLine().split(" ");
        for(int i = 0; i < n; i++){
            h[i] = Integer.parseInt(str[i]);
        }

        str = br.readLine().split(" ");
        int m = Integer.parseInt(str[0]);
        int[] w = new int[m];
        str = br.readLine().split(" ");
        for(int i = 0; i < m; i++){
            w[i] = Integer.parseInt(str[i]);
        }
        System.out.println(process(h, w));
    }

    public static int process(int[] h, int[] w){
        Arrays.sort(h);
        Arrays.sort(w);
        int res = 0;
        int index = h.length - 1;
        for(int i = w.length - 1; i >= 0; i--){
            while(index >= 0 && h[index] > w[i]){
                index--;
            }
            if(index >= 0){
                res++;
                index--;
            }
        }
        return res;
    }
}
