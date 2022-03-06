package com.zjx.各公司2021笔试代码题汇总.贝壳;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * n块栅栏涂颜料   一共m种颜料
 * 每种颜料都有自己的推荐方案 第i种颜料写着：在这种颜料后不能紧跟c1,c2,..ck这种颜料中的一个
 * 求整体的涂颜料方案总数
 * 输入：
 * 1
 * 2 2 1
 * 1
 * 1
 * 输出
 * 2
 * 输入：
 * 1
 * 4 3 2
 * 1 2
 * 2 3
 * 1 1
 * 输出：
 * 9
 */
public class BK2021Test {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int T = Integer.parseInt(str[0]);

        for (int i = 0; i < T; i++) {
            str = br.readLine().split(" ");
            int n = Integer.parseInt(str[0]);
            int m = Integer.parseInt(str[1]);
            int k = Integer.parseInt(str[2]);
            Map<Integer, HashSet<Integer>> map = new HashMap<>();
            for (int j = 1; j <= m; j++) {
                str = br.readLine().split(" ");
                if(map.get(j) == null){
                    map.put(j,new HashSet<>());
                }
                for (int l = 0; l < k; l++) {
                    map.get(j).add(Integer.parseInt(str[l]));
                }
            }
            System.out.println(process(n,m,map,0,1));
        }
    }

    private static long process(int n, int m, Map<Integer, HashSet<Integer>> map,int before,int num) {
        if(num > n) return 1;
        long res = 0;
        for (int i = 1; i <= m; i++) {
            if (map.get(before) == null || !map.get(before).contains(i)){
                res = (res + process(n,m,map,i,num + 1)) % 1000000007;
            }
        }
        return res % 1000000007;
    }
}
