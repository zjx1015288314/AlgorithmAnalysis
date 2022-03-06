package com.zjx.各公司2021笔试代码题汇总.拼多多第二次笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PDD2021Test1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);

    }

    public static void process(int n){
        if (n <= 3 || n >= 200) return;
        double mid = (n - 1) / 2.0;
        boolean flag = n % 2 == 1;
        int i = 0;
        for (i = 0; i < mid; i++) {
            StringBuffer sb = new StringBuffer();
            int j = 0;
            while(j < i){
                sb.append("3 ");
                j++;
            }
            sb.append("0 ");
            j++;
            if(flag){
                sb.append("0 ");
                j++;
            }
            while (j < n - 1 - i){
                sb.append("1 ");
                j++;
            }
            sb.append("0 ");
            j++;
            while (j < n){
                sb.append("8 ");
                j++;
            }
            System.out.println(sb.toString());
        }
        if (flag){
            int j = 0;
            StringBuffer sb = new StringBuffer();
            while (j < n){
                sb.append("0 ");
                j++;
            }
            System.out.println(sb);
            i++;
        }
        for (;i < n;i++){
            StringBuffer sb = new StringBuffer();
            int j = 0;
            while (j < n - 1 -i){
                sb.append("4 ");
                j++;
            }
            sb.append("0 ");
            j++;
            while (j < mid){
                sb.append("5 ");
                j++;
            }
            if (flag){
                sb.append("0 ");
                j++;
            }
            while (j < i){
                sb.append("0 ");
                j++;
            }
            sb.append("0 ");
            j++;
            while (j < n){
                sb.append("7 ");
                j++;
            }
            System.out.println(sb);
        }

    }
}
