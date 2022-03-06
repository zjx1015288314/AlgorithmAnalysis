package com.zjx.各公司2021笔试代码题汇总.拼多多;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 有n只小熊，他们有着各不相同的战斗力。每次他们吃糖时，会按照战斗力来排，战斗力高的小熊拥有优先选择权。前面的小熊吃饱了，后面的小熊才能吃。每只小熊有一个饥饿值，每次进食的时候，小熊们会选择最大的能填饱自己当前饥饿值的那颗糖来吃，可能吃完没饱会重复上述过程，但不会选择吃撑。
 * 现在给出n只小熊的战斗力和饥饿值，并且给出m颗糖能填饱的饥饿值。
 * 求所有小熊进食完之后，每只小熊剩余的饥饿值。
 * 输入描述:
 * 第一行两个正整数n和m，分别表示小熊数量和糖的数量。（n <= 10, m <= 100）
 * 第二行m个正整数，每个表示着颗糖能填充的饥饿值。
 * 接下来的n行，每行2个正整数，分别代表每只小熊的战斗力和当前饥饿值。
 * 题目中所有输入的数值小于等于100。
 * 输出描述:
 * 输出n行，每行一个整数，代表每只小熊剩余的饥饿值。
 * 输入：
 * 2 5
 * 5 6 10 20 30
 * 4 34
 * 3 35
 * 输出：
 * 4
 * 0
 */
public class PDD2018Test小熊吃糖 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);

        int[] candys = new int[M];
        str = br.readLine().split(" ");
        for (int i = 0; i < candys.length; i++) {
            candys[i] = Integer.parseInt(str[i]);
        }

        int[][] bears = new int[N][3];
        for (int i = 0; i < N; i++) {
            str = br.readLine().split(" ");
            bears[i][0] = Integer.parseInt(str[0]);
            bears[i][1] = Integer.parseInt(str[1]);
            bears[i][2] = i;
        }
        int[][] res = process(candys, bears);
        Arrays.sort(res, (o1, o2) -> o1[2] - o2[2]);
        for (int[] bear : res) {
            System.out.println(bear[1]);
        }
    }

    public static int[][] process(int[] candys, int[][] bears) {
        Arrays.sort(candys);
        Arrays.sort(bears, (o1, o2) -> o2[0] - o1[0]);
        for (int i = 0; i < bears.length; i++) {
            int num = bears[i][1];
            for (int j = candys.length - 1; j >= 0; j--) {
                if (candys[j] != -1 && candys[j] <= num) {
                    num -= candys[j];
                    candys[j] = -1;
                }
            }
            bears[i][1] = num;

            //也可以使用二分查找
//            while(true){
//                int ret = binearySearch(candys,num);
//                if(ret != -1){
//                    num -= candys[ret];
//                    candys[ret] = -1;
//                }else{
//                    break;
//                }
//                bears[i][1] = num;
//            }
        }

        return bears;
    }

    //candys遍历也可以使用二分查找
    public static int binearySearch(int[] candys, int num, boolean[] visited){ //<= num中最大的
        if(candys == null || candys.length  == 0) return -1;
        int l = 0,r = candys.length - 1;
        while(l <= r){
            int mid = (l + r) / 2;
            if(candys[mid] > num){
                r = mid - 1;
            }else{
                l = mid + 1;
            }
        }
        while (l == candys.length || candys[l] > num || visited[l]){
            l--;
            if(l >= 0 && visited[l]){
                l--;
            }else{
                break;
            }
        }
        return l != -1 ? (visited[l] ? -1 : l) : l;
    }
}
