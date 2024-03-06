package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.树.后序遍历相关;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 2 9 5 16 17 15 19 18 12
 */
public class YFD二叉搜索树检验是否后序遍历 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int[] arr = new int[N];
        str = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }
        System.out.println(check(arr,0,arr.length - 1));
    }

    /**
     * 递归检验是否是后序遍历,难点是递归的终止条件以及中间确认左右子树的区间！！！
     * @param arr
     * @param low
     * @param high
     * @return
     */
    public static boolean check(int[] arr,int low, int high){
        //low = high + 1在区间为空的时候也认为true,此时根节点只有左子树或者右子树
        if (low == high || low == high + 1) return true;
        int idx = 0;
        for (;idx < high; idx++){
            if (arr[idx] > arr[high]) break;
        }
        int leftR = idx - 1;
        int rightL = idx;
        for (;idx < high; idx++){
            if (arr[idx] < arr[high]) return false;
        }
        int rightR = idx - 1;
        return check(arr,low,leftR) && check(arr,rightL,rightR);
    }
}
