package com.zjx.各公司2021笔试代码题汇总.拼多多第三次笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PDD2021Test2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int K = Integer.parseInt(str[0]);

        for (int j = 0; j < K; j++) {
            str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int k = Integer.parseInt(str[1]);

            int[] num = new int[n + 1];
            str = br.readLine().split(" ");
            for (int i = 1; i <= n; i++) {
                num[i] = Integer.parseInt(str[i - 1]);
            }

            Map<Integer, List> map = new HashMap<>();
            for (int i = 0; i < n - 1; i++) {
                str = br.readLine().split(" ");
                int first = Integer.parseInt(str[0]);
                int second = Integer.parseInt(str[1]);
                if (!map.containsKey(first)) {
                    ArrayList<Integer> tmp = new ArrayList<>();
                    map.put(first, tmp);
                }
                map.get(first).add(second);
                if (!map.containsKey(second)) {
                    ArrayList<Integer> tmp = new ArrayList<>();
                    map.put(second, tmp);
                }
                map.get(second).add(first);
            }
            process(num, map, n, k,1,new LinkedList<>());
            System.out.println(maxAns);
        }
    }

    private static List<Integer> res = new ArrayList<>();
    private static int maxAns = Integer.MIN_VALUE;

    private static void process(int[] num, Map<Integer, List> map, int n, int k, int index,LinkedList<Integer> list) {
        if (index > n){
            if (list.size() == k){
                int sum = 0;
                for (Integer item : list) {
                    sum += num[item];
                    if (sum > maxAns){
                        maxAns = sum;
                    }
                }
            }
            return;
        }

        for (int i = index; i < num.length; i++) {
            boolean flag = false;
            for (Integer personNum : list) {
                if (map.get(personNum) != null && map.get(personNum).contains(i)){
                    flag = true;
                    break;
                }
            }

            if (!flag){
                list.addLast(i);
                process(num,map,n,k,index + 1,list);
                list.removeLast();
                process(num,map,n,k,index + 1,list);
            }

        }
    }
}
