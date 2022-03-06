package com.zjx.各公司2021笔试代码题汇总.拼多多;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 第二题是给n个长度为6的list，每个代表一个骰子，分为【前，后，左，右，上，下】（大致是这样，重点是二二分组）。然后判断骰子是否可以经过翻转变为同一个骰子。
 * 题目的重点就是两个list如何判断是同一个骰子：
 * 3 // 色子个数
 * 1 2 3 4 5 6 // 上下前后左右点数
 * 1 2 6 5 3 4 // 可旋转为上面这个
 * 1 2 3 4 6 5 // 不可旋转成上面的形式
 * 将返回
 * 2   // 两种色子
 * 2 1 // 每种分别是2个 1个
 */
public class PDD2021Test2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] arr = new String[N];
        String[] str = null;
        int[] tmp = null;
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }
        Map<String,Integer> res = check(arr);
        PriorityQueue<String> queue = new PriorityQueue((o1,o2) -> res.get(o2) - res.get(o1));
        for (Map.Entry<String, Integer> entry : res.entrySet()){
            String key = entry.getKey();
            queue.add(key);
        }
        System.out.println(res.size());
        while(!queue.isEmpty()){
            System.out.print(res.get(queue.poll()) + " ");
        }
    }

    public static Map<String, Integer> check(String[] arr) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            boolean flag = false;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                if (isContains(key, arr[i])) {
                    flag = true;
                    map.put(key, value + 1);
                }
            }
            if (!flag) {
                map.put(arr[i], 1);
            }
        }
        return map;
    }

    public static boolean isContains(String s1, String s2) {
        String[] str1 = s1.split(" ");
        String[] str2 = s2.split(" ");

        int[] arr1 = new int[3];
        int[] arr2 = new int[3];

        //将两种骰子的每种组合的第一位加入
        int s = 0,m = 0,n = 0;
        for (int i = 0; i < str1.length; i += 2) {
            arr1[i / 2] = Integer.parseInt(str1[i]);
            arr2[i / 2] = Integer.parseInt(str2[i]);
            m += arr1[i / 2];
            n += arr2[i / 2];
        }
        s = Math.abs(m - n);
        //计算组内顺序的次数s和组间逆序的次数L
        int L = 0, L1 = 0, L2 = 0;
        for (int i = 0; i < arr1.length; i++) {
            for (int j = i + 1; j < arr1.length; j++) {
                if (arr1[i] < arr1[j]){
                    L1++;
                }
                if (arr2[i] < arr2[j]){
                    L2++;
                }
            }
        }
        L = Math.abs(L1 - L2);
        return L == 0 ? (s % 2 == 0) : ((L - 1 + s) % 2 == 0);
    }
}
