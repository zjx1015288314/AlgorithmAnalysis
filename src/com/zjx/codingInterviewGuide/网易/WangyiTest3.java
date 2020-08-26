package com.zjx.codingInterviewGuide.网易;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 给出n个物品，每个物品都有自己的价值，每个物品只有一件，这些物品需要分给两个人，
 * 要求分配完之后，两个人的物品价值相同。分配完成之后，会丢弃剩下的物品，求最少要丢弃多少物品。
 * 输入
 * 输入第一行为总的测试数据个数，第二行为物品个数n，第三行为n个物品的价值。
 * 1
 * 5
 * 30 60 5 15 30
 * 输出
 * 20 丢弃5和15，把60分配给第一个人，2个30分配给第二个人。
 */
public class WangyiTest3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int T = Integer.parseInt(str[0]);
        int[] arr = null;
        for (int i = 0; i < T; i++) {
            str = br.readLine().split(" ");
            int n = Integer.parseInt(str[1]);
            arr = new int[n];
            str = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(str[j]);
            }
            process(0,0,0,0,arr);
        }
    }
    /**
     * @param index arr[]索引
     * @param num1 第一个人分到的商品的总价值
     * @param num2 第二个人分到的商品的总价值
     * @param remain 舍弃商品的总价值
     * @param arr
     * @return 返回最少舍弃的价值
     */
    private static int res = 0;
    public static void process(int index, int num1, int num2, int remain, int[] arr) {
        if (index == arr.length) {
            if (num1 == num2)
                res = Math.min(remain, res);
            return;
        }
        process(index + 1, num1 + arr[index], num2, remain, arr);
        process(index + 1, num1, num2 + arr[index], remain, arr);
        process(index + 1, num1, num2, remain + arr[index], arr);
    }
}
