package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数字相关;


/**
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 *
 * 示例 1：
 * 输入：nums = [1,2,0]
 * 输出：3
 *
 * 示例 2：
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 *
 * 示例 3：
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/first-missing-positive
 */
public class 缺失的第一个正数 {
    public int firstMissingPositive(int[] nums) {
//        for(int i = 0; i < nums.length; i++) {
//            int idx = i;
//            //nums[idx] != idx + 1包含于nums[nums[idx] - 1] != nums[idx]条件，
//            // 只保留后面一个即可， idx也不需要，直接用i
//            while(nums[idx] > 0 && nums[idx] <= nums.length
//                    && nums[idx] != idx + 1 && nums[nums[idx] - 1] != nums[idx]) {
//                swap(nums, idx, nums[idx] - 1);
//            }
//        }

        //上面的改进
        for(int i = 0; i < nums.length; i++){
            while(nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]){
                swap(nums,i,nums[i] - 1);
            }
        }

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums.length + 1;    //这里不是length
    }

    private static void swap(int[] arr, int i ,int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
