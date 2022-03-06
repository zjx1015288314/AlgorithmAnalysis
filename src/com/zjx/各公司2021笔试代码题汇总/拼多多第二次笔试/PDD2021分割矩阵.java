package com.zjx.各公司2021笔试代码题汇总.拼多多第二次笔试;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 将N*N的矩阵分割为8个区域，从右上角1号区域编号，逆时针编号2,3...,8
 * 逐行打印该矩阵
 */
public class PDD2021分割矩阵 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        process(N);
    }

    public static void process(int n){
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(i == j || i + j == n + 1 || (n % 2 != 0 && (i == (n + 1) / 2 || j == (n + 1) / 2))){
                    System.out.print(0);
                }else if (j < n + 1 - i && j > (n + 1) / 2.0){
                    System.out.print(1);
                }else if(j > i && j < (n + 1) / 2.0){
                    System.out.print(2);
                }else if(j < i && i < (n + 1) / 2.0){
                    System.out.print(3);
                }else if(j < n + 1 - i && i > (n + 1) / 2.0){
                    System.out.print(4);
                }else if(j > n + 1 - i && j < (n + 1) / 2.0){
                    System.out.print(5);
                }else if(j < i && j > (n + 1) / 2.0){
                    System.out.print(6);
                }else if(j > i && i > (n + 1) / 2.0){
                    System.out.print(7);
                }else if(j > n + 1 - i && i < (n + 1) / 2.0){
                    System.out.print(8);
                }
                if (j == n){
                    System.out.println();
                }else{
                    System.out.print(" ");
                }
            }
        }
    }
}
