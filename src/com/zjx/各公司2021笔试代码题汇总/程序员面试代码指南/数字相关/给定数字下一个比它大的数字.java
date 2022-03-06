package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数字相关;

/**
 * 输出给定数字下一个比它大的数字，比如输入：1234， 输出 1243
 * @author zhaojiexiong
 * @create 2020/7/22
 * @since 1.0.0
 */
public class 给定数字下一个比它大的数字 {
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length < 2) return;
        int i = nums.length - 2;
        while(i >= 0 && nums[i] >= nums[i + 1]){
            i--;
        }
        if(i >= 0){
            int j = nums.length - 1;
            while(j > i && nums[i] >= nums[j]){
                j--;
            }
            swap(nums,i,j);
        }
        reverse(nums,i + 1);
    }
    private void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private void reverse(int[] arr,int start){
        int end = arr.length - 1;
        while(start < end){
            swap(arr,start++,end--);
        }
    }
}
