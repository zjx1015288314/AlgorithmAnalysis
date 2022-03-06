package com.zjx.各公司2021笔试代码题汇总.拼多多;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 扔n个骰子，第i个骰子有可能投掷出Xi种等概率的不同的结果，数字从1到Xi。
 * 所有骰子的结果的最大值将作为最终结果。求最终结果的期望。
 * 链接：https://www.nowcoder.com/questionTerminal/86ef0d5042934ef7819035794377a507
 * 来源：牛客网
 * 以两个筛子，最大值为2,3为例
 * 可以得出，总概率分布的面积为2*3=6
 * 对1来说，最大值为1 的概率就是1*1的正方形，概率为cur=1/2*1/3=1/6
 * 对2来说，最大值为2的概率就是2*2的正方形减去1*1的正方形，也就是cur-pre=2/2*2/3-1/6 =3/6
 * 对3来说，最大值为3的概率就是面积为6的长方形减去面积为4的情况2，也就是2/2*3/3-4/6
 * 综上，求得nums的max值，对其i遍历
 * 对于每个i，计算i的概率cur    ans=(cur-pre)*i
 */
public class PDD2020Test4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int[] arr = new int[n];

        int max = Integer.MIN_VALUE;  //数组中骰子中最大值
        int total = 1;  //
        str = br.readLine().split(" ");

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(str[i]);
            max = Math.max(max,arr[i]);
            total *= arr[i];
        }
        System.out.println(String.format("%.2f",process(arr,max,total)));
    }

    //联合概率求解，这里需要懂一点概率论的知识，p(x=k)=p(x<=k)-p(x<=k-1)
    //以两个筛子为例，可以转化为求联合概率分布的面积/总面积
    //期望就是p(k)*k 求和
    public static double process(int[] arr, int max, int total){
        double res = 0.0;
        double pre = 0.0;
        for (int i = 1; i <= max; i++) {
            double cur = 1.0;
            for (int j = 0; j < arr.length; j++) {
                //不能超出边界
                cur = cur * Math.min(i,arr[j]) / arr[j]; //几何概型 1~arr[j]中 取<=i的概率
            }
            res += (cur - pre) * i;
            pre = cur;
        }
        return res;
    }
}
