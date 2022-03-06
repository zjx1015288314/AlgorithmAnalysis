package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.并查集;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 现在有107个用户，编号为1- 107，现在已知有m对关系，每一对关系给你两个数x和y，
 * 代表编号为x的用户和编号为y的用户是在一个圈子中，例如：A和B在一个圈子中，
 * B和C在一个圈子中，那么A,B,C就在一个圈子中。现在想知道最多的一个圈子内有多少个用户。
 *
 * 输入描述:
 * 第一行输入一个整数T，接下来有T组测试数据。
 * 对于每一组测试数据：第一行输入1个整数n，代表有n对关系。
 * 接下来n行，每一行输入两个数x和y，代表编号为x和编号为y的用户在同一个圈子里。
 * 1 ≤ T ≤ 10
 * 1 ≤ n ≤ 105
 * 1 ≤ x, y ≤ 107
 *
 * 输出描述:
 * 对于每组数据，输出一个答案代表一个圈子内的最多人数
 */
public class 朋友圈 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        int n = Integer.parseInt(s);

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            UnionFind unionFind = new UnionFind(10_000_000);
            String s1 = br.readLine();
            int m = Integer.parseInt(s1);

            for (int j = 0; j < m; j++) {
                String[] relation = br.readLine().split(" ");
                int x = Integer.parseInt(relation[0]);
                int y = Integer.parseInt(relation[1]);
                unionFind.union(x, y);
            }
            sb.append(unionFind.maxCount + "\n");
        }
        System.out.print(sb);
    }

    static class UnionFind{
        int[] parent;
        int[] count;
        int maxCount = 1;

        public UnionFind(int n){
            parent = new int[n + 1];
            count = new int[n + 1];
            for (int i = 0; i < n + 1; i++) {
                parent[i] = i;
            }
            Arrays.fill(count, 1);
        }

        public void union(int i, int j) {
            int parentOfI = find(i);
            int parentOfJ = find(j);

            if(parentOfI != parentOfJ) {
                parent[parentOfI] = parentOfJ;
                count[parentOfJ] += count[parentOfI];
                maxCount = Math.max(maxCount, count[parentOfJ]);
            }
        }

        public int find(int i) {
            while(parent[i] != i) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return parent[i];
        }
    }
}
