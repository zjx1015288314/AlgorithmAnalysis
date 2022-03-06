package com.zjx.各公司2021笔试代码题汇总.猿辅导;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 猿辅导2020校招笔试（二）
 * 猿辅导课堂上老师提供了一些角色，学生可以从中选择一个自己喜欢的角色扮演，每3个不同的角色就可以组成一个小组，进行分组对话。
 * 当老师点击开始分组对话按钮的时候，服务器会为已经选择自己角色的同学分配对话小组，请问最多能组成多少个对话小组？
 * 第一行为测试用例数量C(C<=100)，接下来的C行每行为一个测试用例
 * 每个用例的第一个数字表示可供选择的角色数量T(T<=1000)，接下来的T个数字表示每个角色的选择人数Pi(Pi<=500)
 * 一共C行，每行表示一个测试用例中的最大对话小组数量。
 * 输入例子1:
 * 3
 * 3 1 1 1
 * 3 2 2 3
 * 4 0 2 3 99
 * 输出例子1:
 * 1
 * 2
 * 2
 */
public class YFD2020Test角色分组 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] str = null;

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < N; i++) {
            str = br.readLine().split(" ");
            int M = Integer.parseInt(str[0]);
            int[] arr = new int[M];
            for (int j = 0; j < M; j++) {
                arr[j] = Integer.parseInt(str[j + 1]);
            }
            int res = process(arr);
            sb.append(res + "\n");
        }
        System.out.println(sb.toString());
    }

    public static int process(int[] arr) {
        int res = 0;
        Arrays.sort(arr);
        int first = arr.length - 1;
        int second = arr.length - 2;
        int third = arr.length - 3;
        while (arr[third] != 0) {
            res += 1;
            arr[first] -= 1;
            arr[second] -= 1;
            arr[third] -= 1;
            Arrays.sort(arr);
        }
        return res;
    }
}
