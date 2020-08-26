package com.zjx.codingInterviewGuide;

/**
 * 统计一个数字在排序数组中出现的次数。
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 */
public class GetNumsOfNumInOArr {

    public static void main(String[] args) {
        System.out.println(search(new int[]{5,7,7,8,8,10},8));
    }
    public static int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return 0;
        //if(target < nums[0] || target > nums[nums.length - 1]) return 0;
        int first = binarySearh(nums,target);
        int second = binarySearh(nums,target + 1);
        //return (first == nums.length || nums[first] != target) ? 0 : second - first; //可以不考虑
        return second - first;
    }

    //找到大于或者等于k最左边的元素下标,没有就返回num.length
    public static int binarySearh(int[] nums, int k){
        int l = 0;
        int r = nums.length;
        while(l < r){
            int m = l + (r - l) / 2;
            if(nums[m] >= k){
                r = m;
            }else{
                l = m + 1;
            }
        }
        return l;
    }
}
