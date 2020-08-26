package com.zjx.codingInterviewGuide.阿里;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * 示例:
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 这里上升意思是单调递增，而不是非递减
 * O(n^2)的解法是通过dp[]得到以num[i]结尾的最长递增子序列的长度,然后在i+1的时候从
 * dp[0]~dp[i]中找出比num[i + 1]小的子序列中长度最长的 dp[i + 1] = dp[] + 1
 * O(nlogn)解法是利用贪心+二分法:在当前长度为i的子序列中，只存储末尾数字最小的，其他的舍弃。
 * ends[i]保存i + 1长度的最小的子序列的末尾数字，并且是单调递增的，可以证明，所以遍历到num[i]
 * 的时候需要利用二分法遍历ends[]找到大于等于num[i]的最左边的位置
 */
public class LISProblem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = sc.nextInt();
        }
        int[] dp = lengthOfLIS(nums);
        int[] res = generateLIS(nums,dp);
        for (int i : res) {
            System.out.print(i + " ");  //2 1 5 3 6 4 8 9 7
        }
    }
    //最长递增(上升)子序列
    public static int[] lengthOfLIS(int[] nums) {
        if(nums == null || nums.length == 0) return null;
        int[] ends = new int[nums.length]; //索引为i - 1的位置表示 当前长度为i的LIS中，最小的末尾数字
        int[] dp = new int[nums.length];
        ends[0] = nums[0];
        dp[0] = 1;  //dp[i]表示当前以nums[i]结尾的最长递增子序列的长度
        int right = 0;  //表示ends数组的右边界
        for(int i = 1; i < nums.length; i++){
            if(ends[right] < nums[i]){  //比最大的都大
                ends[++right] = nums[i];
                dp[i] = right + 1;
            }else{
                int l = 0;
                int r = right;
                while(l <= r){
                    int m = (l + r) / 2;
                    if(nums[i] > ends[m]){//找到大于等于nums[i]的最左边的数
                        l = m + 1;
                    }else{
                        r = m - 1;
                    }
                }
                ends[l] = nums[i];
                dp[i] = l + 1;
            }
        }
//        return right + 1; //dp[]数组用来找出对应的LIS
        return dp;

    }

    //最长递增(上升)子序列优化
    public static int lengthOfLIS1(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] ends = new int[nums.length]; //索引为i - 1的位置表示 当前长度为i的LIS中，最小的末尾数字
        int[] dp = new int[nums.length];
        int right = 0;  //表示ends数组的右边界
        for(int i = 0; i < nums.length; i++){
            int l = 0;
            int r = right;
            while(l < r){  //l < r时 r = m，l <= r时 r = m - 1;  并且l < r才能使i=0时成立
                int m = (l + r) / 2;
                if(nums[i] > ends[m]){
                    l = m + 1;
                }else{
                    r = m - 1;
                }
            }
            ends[l] = nums[i];
            dp[i] = l + 1;
            if(l == right){
                right++;
            }
        }
        return right;
    }

    //从上面两个方法恢复最长递增子序列
    public static int[] generateLIS(int[] arr, int[] dp){
        int len = 0;
        int index = 0;
        for (int i = 0; i < dp.length; i++) {
            if(dp[i] > len){
                len = dp[i];
                index = i;
            }
        }
        int[] res = new int[len];
        res[--len] = arr[index];
        for (int i = index - 1; i >= 0; i--) {
            if (arr[i] < arr[index] && dp[i] == dp[index] - 1){
                res[--len] = arr[i];
                index = i;
            }
        }
        return res;
    }

    //最长非递减子序列
    public static int lengthOfLIS2(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int[] ends = new int[nums.length]; //索引为i - 1的位置表示 当前长度为i的LIS中，最小的末尾数字
        int[] dp = new int[nums.length];
        ends[0] = nums[0];
        dp[0] = 1;  //dp[i]表示当前以nums[i]结尾的最长递增子序列的长度
        int right = 0;  //表示ends数组的右边界
        for(int i = 1; i < nums.length; i++){
            if(ends[right] <= nums[i]){  //非递减与递增不同处，找到大于的nums[i]最左边的数
                ends[++right] = nums[i];
                dp[i] = right + 1;
            }else{
                int l = 0;
                int r = right;
                while(l <= r){
                    int m = (l + r) / 2;
                    if(nums[i] >= ends[m]){//找到大于nums[i]的最左边的数
                        l = m + 1;
                    }else{
                        r = m - 1;
                    }
                }
                ends[l] = nums[i];
                dp[i] = l + 1;
            }
        }
        return right + 1; //dp[]数组用来找出对应的LIS
    }
}
