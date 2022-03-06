package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组;

/**
 * @ref {二分查找有序数组中某个数的出现次数} 与这个类似
 *
 * 思路：
 *  1.找到大于或者等于target的最左边的位置first，找到小于或者等于target的最右边的位置second
 *  2.或者根据{二分查找有序数组中某个数的出现次数}中的解法，找到大于或者等于target的最左边的位置first，
 *  找到大于或者等于target + 1的最左边的位置first，
 */
public class 数组中某元素第一次出现和最后一次出现的位置 {

    public static void main(String[] args) {
        int[] res = searchRange(new int[]{5,7,7,8,8,10},7);
//        int[] res = searchRange1(new int[]{5,7,7,8,8,10},8);
        for(int i : res) {
            System.out.println(i);
        }
    }

    public static int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0) return new int[]{-1,-1};

        int left = 0;
        int right = nums.length - 1;
        //找到大于或者等于target的最左边的位置
        while(left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        //全都小于的话返回-1
        if(left == nums.length || nums[left] != target) {
            return new int[]{-1, -1};
        }
        //重新定位区间
        int first = left;
        int second = left;
        right = nums.length - 1;

        //找到小于或者等于target的最右边的位置
        while(left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        second = right;
        return new int[]{first, second};
    }

    public static int[] searchRange1(int[] nums, int target) {
        if(nums == null || nums.length == 0) return new int[]{-1,-1};

        int first = searchRangeHelper(nums, target);
        int second = searchRangeHelper(nums, target + 1);
        if (first == nums.length || nums[first] != target) {
            return new int[]{-1, -1};
        } else {
            return new int[]{first, second - 1};
        }
    }

    private static int searchRangeHelper(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;

        int left = 0;
        int right = nums.length - 1;
        //找到大于或者等于target的最左边的位置
        while(left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] >= target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
