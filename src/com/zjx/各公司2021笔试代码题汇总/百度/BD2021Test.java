package com.zjx.各公司2021笔试代码题汇总.百度;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//1
//10 2
//3
//1 2
//4 5
//8 8
//2
//1 4
//6 8   未完成
public class BD2021Test {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int T = Integer.parseInt(str[0]);
        for (int t = 0; t < T; t++) {
            str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[0]);
            Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
            for (int i = 0; i < m; i++) {
                str = br.readLine().split(" ");
                int k = Integer.parseInt(str[0]);
                for (int j = 0; j < k; j++) {
                    str = br.readLine().split(" ");
                    if (!map.containsKey(k)) {
                        map.put(k, new HashMap<>());
                    }
                    int l = Integer.parseInt(str[0]);
                    int r = Integer.parseInt(str[1]);
                    map.get(k).put(l, r);
                }
            }
//            process(map, n);
        }
    }

    private static void process(Map<Integer, int[]> map, int n) {
        HashSet<Integer> set = new HashSet<>();
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int[] lr = entry.getValue();
            if (set.isEmpty()) {
                int l = lr[0];
                int r = lr[1];
                for (int i = l; i <= r; i++) {
                    set.add(i);
                }
            } else {
                int l = lr[0];
                int r = lr[1];
                for (Integer item : set) {
                    if (item < l || item > r) {
                        set.remove(item);
                    }
                }
            }
        }
    }
}
