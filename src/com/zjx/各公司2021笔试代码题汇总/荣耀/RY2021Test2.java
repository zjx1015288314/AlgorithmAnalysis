package com.zjx.各公司2021笔试代码题汇总.荣耀;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RY2021Test2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int x = Integer.parseInt(str[0]);        // 背包的容量为V
        // 物品的数量为N
        int N = 8;
        // 一个长度为N的数组，第i个元素表示第i个物品的体积；
        int[] v = {0,20,30,50,30,50,30,40,10};
        // 一个长度为N的数组，第i个元素表示第i个物品的价值；
        int[] w = {0,300,500,620,370,400,450,380,150};

        int[] dp = new int[x + 1];
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = x; j >= 0; j--) {
                if (j >= v[i]) {
                    dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
                }
            }
        }
        System.out.print(dp[x]);
    }

//    public static void main(String[] args) throws IOException {
//
//
//        List<Processor> arr = new ArrayList<>();
//        Processor max = null;
//        int[] a = {300,500,620,370,400,450,380,150};
//        int[] b = {20,30,50,30,50,30,40,10};
//        for (int i = 0; i < 10; i++) {
//            Processor p = new Processor(a[i],b[i],a[i]/b[i]);
//            arr.add(p);
//        }
//
//
//        if(x < 10){
//            System.out.println("error");
//        }else if (x < 20){
//            System.out.println(150);
//        }else if (x < 30){
//            System.out.println(300);
//        }else if (x < 40){
//            System.out.println(500); //30
//        }else if (x < 50){
//            System.out.println(650);  //10+30
//        }else if (x < 60){
//            System.out.println(800);   //20+30
//        }else if (x < 70){
//            System.out.println(950); //30+30
//        }else if (x < 80){
//
//        }
//    }
}
