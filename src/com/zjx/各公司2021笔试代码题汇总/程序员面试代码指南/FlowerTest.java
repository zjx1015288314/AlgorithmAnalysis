package com.itzjx.mmall_test;

import java.util.Scanner;

/**
 * @author zhaojiexiong
 * @create 2020/6/7
 * @since 1.0.0
 */
public class FlowerTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = in.nextInt();
        }
        int idx = -1;
        int num = 0;
        int res = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i] == 1){
                num = i - idx - 1;
                res += (num - 1)/2;
                idx = i;
            }
        }
        System.out.println(res);
    }
}
