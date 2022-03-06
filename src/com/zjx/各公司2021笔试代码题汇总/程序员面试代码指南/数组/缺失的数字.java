package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组;

/**
 * @author zhaojiexiong
 * @create 2020/7/22
 * @since 1.0.0
 */
public class 缺失的数字 {
    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[]{3,4,-1,1}));
        System.out.println(missingNumber1(new int[]{9,6,4,2,3,5,7,0,1}));
    }

    /**
     * 第一题：给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数
     * 思路：将num[i]尽力放到num[i] - 1的位置 ，即 3放到下标为2的位置，
     * 4放到下标为3的位置，-1不调整，1放到0位置，最后遍历数组找到确实的数字
     * 输入: [3,4,-1,1]
     * 输出: 2
     * @param nums
     * @return
     */
    public static int firstMissingPositive(int[] nums) {
        if(nums == null || nums.length == 0) return 1;
        for(int i = 0; i < nums.length; i++){
            while(nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]){
                swap(nums,i,nums[i] - 1);
            }
        }
        int expectedNum = nums.length + 1;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != i + 1){
                return i + 1;
            }
        }
        return expectedNum;
    }

    private static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
     * 输入: [9,6,4,2,3,5,7,0,1]
     * 输出: 8
     * @param nums
     * @return
     */
    public static int missingNumber1(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int n = nums.length - 1; //理想情况是0-n
        for(int i = 0; i < nums.length; i++){
            while(nums[i] != i && nums[i] < nums.length){
                swap(nums,i,nums[i]);
            }
        }
        int expectedNum = nums.length;
        for(int i = 0; i < nums.length; i++){
            if(i != nums[i]){
                return i;
            }
        }
        return expectedNum;
    }
}
