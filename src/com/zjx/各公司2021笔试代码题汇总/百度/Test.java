package com.zjx.各公司2021笔试代码题汇总.百度;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 总共有n阶阶梯   每次可以走1-m步，且每一步走的不能和前两步步数相同，求有多少种方案
 */
public class Test implements Cloneable{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        System.out.println(process1(N,M));
    }

    public static long process1(int n, int m){
        int res = 0;
        for (int i = 1; i <= m; i++) {
            res += dfs(n,m,0,0,i,0);
        }
        return res;
    }
    /**
     * @param n 阶梯总数
     * @param m 每次可以走的最大值
     * @param presum 这步之前一共走过的台阶总数
     * @param pre 前一步的步数
     * @param i 这一步的步数
     * @param res 第一步到前一步可以走的方案
     * @return res
     */
    public static int dfs(int n, int m, int presum, int pre, int i, int res){
        presum += i;  //当前走过的台阶
        if(presum >= n){
            return ++res;
        }
        int[] a = {1,3,4};
        System.out.println();
        for(int j=1; j<=m; j++){
            if(j != i && j!=pre){
                res = dfs(n,m,presum,i,j,res); //res传入 可不用+=
            }
        }
        return res;
    }



    //如果每一步走的步数和前两步没有关系
    public static long process(int n, int m){
        long sumStep = 0;
        if (n == 0){return 1;}
        if(n >= m){
            for (int i = 1; i <= m; i++) {
                sumStep = (sumStep + process(n - i,m)) % 1000000007;
            }
        }else{
            sumStep = process(n,n) % 1000000007;
        }
        return sumStep % 1000000007;
    }

}
