package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.并查集;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 腾讯2021笔试
 * 2003年3月SARS病毒疯狂的席卷了全球。为了降低被感染的可能性，最好的办法就是将感染者隔离。
 * 在NSYSU大学,有许多学生社团.同一个学生社团中的人会频繁的接触，而一个学生可能会加入很多个团体。
 * 为了防止SARS的传播，NSYSU搜集了所有学生社团的名单。
 * 只要社团中有一个人被感染，那么社团中的每一个人都将被感染。可是，每当出现一个感染者，
 * 要确定每一个人是否被感染确实很困难的。你能否写一个程序找出所有的受感染的。
 *
 * 输入：
 * 输入包含多组数组。每组测试数据首先包含两个整数n和m，n（0 < n <= 30000）表示学生的数目，
 * m（0 <= m <= 500）表示社团的数目。每个学生都有一个唯一的编号，编号取值为0到n-1，
 * 编号为0的学生是所有学生的中最初的唯一感染者。
 * 接下来有m个社团的名单，每个社团的名单在输入中为一行。每一行先输入一个数k表示社团总人数。接着是社团中k个成员的编号
 * 当输入使 n = 0 且 m = 0 时，则输入结束。
 *
 * 例：
 *  100 4   //第一组
 *  2 1 2
 *  5 10 13 11 12 14
 *  2 0 1
 *  2 99 2
 *  200 2   //第二组
 *  1 5
 *  5 1 2 3 4 5
 *  1 0
 *  0 0     //结束
 */
public class 感染人数之数组并查集 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); //人数
        int m = sc.nextInt();  //团队数量
        if(n == 0 && m == 0){
            return;
        }

        UnionFind unionFind = new UnionFind(n);
        for (int i = 0; i < m; i++) {
            int k = sc.nextInt();
            int before = -1;
            for (int j = 0; j < k; j++) {
                int v = sc.nextInt();
                if(before != -1){
                    int x = unionFind.find(before);
                    int y = unionFind.find(v);
                    //x == y说明之前合并过 这里就不用做改变
                    //x != y说明还没有合并
                    //这步相当于并查集中的union
                    if(x != y){
                        if(x == 0){
                            unionFind.union(y, x);
                        }else {  //y == 0 || (x != 0 && y != 0)
                            unionFind.union(x, y);
                        }
                    }
                }
                before = v;
            }
        }
        System.out.println(unionFind.count[0]);
    }

    static class UnionFind {
        int[] parent;
        int[] count;    //i所在的集合中的人数

        public UnionFind(int n) {
            parent = new int[n];
            count = new int[n];
            for(int i = 0; i < n; i++) {
                parent[i] = i;
            }
            Arrays.fill(count, 1);
        }

        public void union(int i, int j) {
            int parentOfI = find(i);
            int parentOfJ = find(j);
            if (parentOfI != parentOfJ) {
                parent[parentOfI] = parentOfJ;
                count[parentOfJ] += count[parentOfI];
            }
        }

        public int find(int before) {
            while(parent[before] != before){
                parent[before] = parent[parent[before]];
                before = parent[before];
            }
            return parent[before];
        }
    }
}
