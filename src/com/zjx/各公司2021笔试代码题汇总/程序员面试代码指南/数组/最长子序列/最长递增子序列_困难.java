package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.最长子序列;

/**
 * 题目描述
 * 给定数组arr，设长度为n，输出arr的最长递增子序列。（如果有多个答案，请输出其中字典序最小的）
 * 示例1
 * 输入
 * [2,1,5,3,6,4,8,9,7]
 * 输出
 * [1,3,4,8,9]
 *
 * 示例2
 * 输入
 * [1,2,8,6,4]
 * 输出
 * [1,2,4]
 * 说明
 * 其最长递增子序列有3个，（1，2，8）、（1，2，6）、（1，2，4）其中第三个字典序最小，故答案为（1，2，4）
 * 备注: n≤10^5        1≤arr<=10^9
 * 链接： https://leetcode.cn/problems/longest-increasing-subsequence/description/
 */
public class 最长递增子序列_困难 {

    /**
     * 题目难点在于输出递增子序列，并且是字典序最小的
     * 利用贪心算法思想，在长度相同的LIS中，优先选取末尾字符最小的字符串，以便之后能得到最长的LIS
     * 时间复杂度：O(nlogn)。数组nums的长度为n，我们依次用数组中的元素去更新ends数组，
     * 而更新ends数组时需要进行 O(logn)的二分搜索，所以总时间复杂度为O(nlogn)。
     * 空间复杂度：O(n)，需要额外使用长度为n的ends数组
     */
    public int[] LIS (int[] arr) {
        if(arr == null || arr.length == 0) return null;
        int[] ends = new int[arr.length]; //索引为i - 1的位置表示当前长度为i的LIS中，最小的末尾数字
        int[] dp = new int[arr.length]; //dp[i]表示当前以nums[i]结尾的最长递增子序列的长度
        int right = 0;  //表示ends数组的右边界，right处无值，表示真正的边界，一旦放了值之后，就会+1扩展
        for(int i = 0; i < arr.length; i++){
            int l = 0;
            int r = right;
            while(l < r){  //找到大于等于arr[i]的最左边的数，如果不存在的话，就在最右边(right索引处)放入
                int m = (l + r) / 2;
                if(ends[m] < arr[i]){
                    l = m + 1;
                }else{
                    r = m;  //l < r时 r = m，l <= r时 r = m - 1;  并且l < r才能使i=0时成立
                }
            }
            ends[l] = arr[i];
            dp[i] = l + 1;
            if(l == right){
                right++;
            }
        }
        int[] res = new int[right]; //right此时表示LIS的最大长度
        for(int i = arr.length-1; i >= 0; i--){
            if(dp[i] == right)
                res[--right] = arr[i];
        }
        return res;
    }
}
