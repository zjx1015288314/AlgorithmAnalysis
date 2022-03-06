package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南;

import java.util.Scanner;

/**
 * 来源：Acwing       https://www.acwing.com/problem/content/description/2/
 * 有 N 件物品和一个容量是 V 的背包。每件物品只能使用一次/无限次/有限次。
 * 第 i 件物品的体积是 vi，价值是 wi。
 * 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
 * 输出最大价值。
 *
 * @author zhaojiexiong
 * @create 2020/7/23
 * @since 1.0.0
 */
public class BackpackProblem {
    public static void main(String[] args) throws Exception {
        // 读入数据的代码
        Scanner reader = new Scanner(System.in);
        // 物品的数量为N
        int N = reader.nextInt();
        // 背包的容量为V
        int V = reader.nextInt();
        // 一个长度为N的数组，第i个元素表示第i个物品的体积；
        int[] v = new int[N + 1];
        // 一个长度为N的数组，第i个元素表示第i个物品的价值；
        int[] w = new int[N + 1];

        int[] s = new int[N + 1];   //多重背包时使用

        // 接下来有 N 行，每行有两个整数:v[i],w[i]，用空格隔开，分别表示第i件物品的体积和价值
        for (int i = 1; i <= N; i++) {
            v[i] = reader.nextInt();
            w[i] = reader.nextInt();
            s[i] = reader.nextInt();    //多重背包时使用
        }
        reader.close();

//        System.out.print(process(v, w, N, V));
//        System.out.print(process1(v, w, N, V));
//        System.out.print(process2(v, w, N, V));
//        System.out.print(process3(v, w, s, N, V));
        System.out.print(process4(v, w, s, N, V));
    }

    /**
     * 二维数组  01背包
     * 输入样例
     * 4 5
     * 1 2
     * 2 4
     * 3 4
     * 4 5
     * 输出样例：
     * 8
     *
     * @return
     */
    public static int process(int[] v, int[] w, int N, int V) {
        int[][] dp = new int[N + 1][V + 1];
        dp[0][0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= V; j++) {
                dp[i][j] = dp[i - 1][j];   //第i件不考虑

                if (j >= v[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - v[i]] + w[i]);
                }
            }
        }
        return dp[N][V];
    }

    /**
     * 01背包优化，一维数组，第二层倒序!!!!!
     * @return
     */
    public static int process1(int[] v, int[] w, int N, int V) {
        int[] dp = new int[V + 1];
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= v[i]; j--) { // j >= 0也可以
                if (j >= v[i]) {
                    dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
                }
            }
        }
        return dp[V];
    }


    /**
     * 完全背包   01背包简化版的修改，第二层循环正序!!!!
     * 有 N 种物品和一个容量是 V 的背包，每种物品都有无限件可用。
     * 输入样例
     * 4 5
     * 1 2
     * 2 4
     * 3 4
     * 4 5
     * 输出样例：
     * 10
     */
    public static int process2(int[] v, int[] w, int N, int V) {
        int[] dp = new int[V + 1];
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= V; j++) {
                if (j >= v[i]) {
                    dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
                }
            }
        }
        return dp[V];
    }

    /**
     * 多重背包
     *有 N 种物品和一个容量是 V 的背包。
     * 第 i 种物品最多有 si 件，每件体积是 vi，价值是 wi。
     * 输入样例
     * 4 5
     * 1 2 3
     * 2 4 1
     * 3 4 3
     * 4 5 2
     * 输出样例：
     * 10
     */
    public static int process3(int[] v, int[] w, int[] s, int N, int V) {
        int[][] dp = new int[N + 1][V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= 0; j--) {
                for (int k = 0; k <= s[i]; k++) {
                    if (j >= k * v[i]) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * v[i]] + k * w[i]);
                    }
                }
            }
        }
        return dp[N][V];
    }

    /**
     *多重背包优化   O(N∗Σlog(p[i])
     */
    public static int process4(int[] v, int[] w, int[] s, int N, int V) {
        int[] dp = new int[V + 1];
        for(int i = 1; i <= N; i++){
            int num = Math.min(s[i],V/v[i]);  //num表示还可以使用多少次，
            for(int k = 1; num > 0; k <<=  1){ //k以logn逼近num
                if(k > num) k = num;   //最后超过时直接赋剩余值
                for(int j = V; j >= k * v[i]; j--){
                    dp[j] = Math.max(dp[j],dp[j - k * v[i]] + k * w[i]);
                }
                num -= k;
            }
        }
        return dp[V];
    }

}
