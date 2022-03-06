package com.itzjx.mmall_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author zhaojiexiong
 * @create 2020/6/17
 * @since 1.0.0
 */
public class LongestIncSubsequence {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        String[] ss = br.readLine().trim().split(" ");
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = Integer.parseInt(ss[i]);
        }
        int[] dp = getdp(arr);
        int[] res = generateLIS(arr, dp);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            sb.append(res[i] + " ");
        }
        System.out.println(sb.toString().trim());
    }

    //找到dp[]中最大的元素，并从该元素往左遍历找到满足条件的元素
    public static int[] generateLIS(int[] arr, int[] dp) {
        if (arr == null || arr.length == 0) return null;
        int len = 0;
        int index = 0;
        for (int i = 0; i < dp.length; i++) {
            //因为要输出字典序最小的，如果dp数组中存在相等的两个值，我们就比较并选择较小的
            if (dp[i] > len ) {
                //找到第一个max
                len = dp[i];
                index = i;
            }
        }
        int[] res = new int[len];
        res[--len] = arr[index];
        for (int i = index - 1; i >= 0; i--) {
            if (dp[i] == dp[index] && arr[i] < arr[index]) {
                res[len] = arr[i];
                index = i;
            }
            if (arr[i] < arr[index] && dp[i] == dp[index] - 1) {
                res[--len] = arr[i];
                index = i;
            }
        }

        return res;
    }

    //使用二分查找来优化dp数组的生成过程，时间复杂度O(NlogN)
    public static int[] getdp(int[] arr) {
        int[] dp = new int[arr.length];
        int[] ends = new int[arr.length];
        ends[0] = arr[0];
        dp[0] = 1;
        int right = 0;
        int l = 0;
        int r = 0;
        int m = 0;
        for (int i = 1; i < arr.length; i++) {
            l = 0;
            r = right;
            //二分查找返回数组中大于或者等于arr[i]最左边的元素，也可由Arrays.binarySearch()替代，
            //但返回值为index(找到)或者-(index+1)(未找到)
            while (l <= r) {
                m = (l + r) / 2;
                if (arr[i] > ends[m]) {
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
            //l可能正好是right+1,此时需要更新right
            right = Math.max(right, l);
            ends[l] = arr[i];   //所有长度为l+1的递增序列中，最小的结尾数为arr[i]
            dp[i] = l + 1;
        }
        return dp;
    }

    //动态规划，dp[i]表示在以arr[i]这个数结尾的情况下，arr[0..i]中的最大递增子序列长度，复杂度为O(N^2);
    public static int[] getdp1(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        int[] dp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return dp;
    }

}
