package com.zjx.各公司2021笔试代码题汇总.拼多多;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 定义函数F:
 * 其中|X|表示X的绝对值。
 * 现在多多鸡想知道，在所有可能的数列 {An} 中，F(N)的最小值和最大值分别是多少。
 * 第一行输入1个整数T，表示测试用例的组数。
 * ( 1 <= T <= 10 )
 * 第二行开始，共T行，每行包含1个整数N，表示数列 {An} 的元素个数。
 * ( 1 <= N <= 100,000 )
 * 输入例子1:
 * 2
 * 2
 * 3
 * 输出例子1:
 * 1 1
 * 0 2
 */
public class PDD2020Test2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++){
            int N = Integer.parseInt(br.readLine());
            maxandmin(N);
        }
    }

    /**
     * 得到N的最小值
     * @param N
     * @return
     */
    public static int[] getRes(int N){
        if(N == 1) return new int[]{1,1};
        int min = 0;
        int max = 0;
        if(N % 4 == 1 || N % 4 == 2){
            min = 1;
        }
        int[] res = getRes(N - 1);
        max = N - res[0];
        return new int[]{min,max};
    }

    //其他解析
    public static void maxandmin(int N){
        if (N==1||N==2){
            System.out.println("1 1");
            return;
        }
        //之后每4个一组 0011
        int min = getmin(N);
        int max = N-getmin(N-1);
        System.out.println(min + " " + max);
    }

    public static int getmin(int N){
        int temp = (N)%4;
        if (temp==1 || temp==2){
            return 1;
        }
        else return 0;
    }
}
