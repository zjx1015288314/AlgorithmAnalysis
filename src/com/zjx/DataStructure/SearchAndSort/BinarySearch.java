package com.zjx.DataStructure.SearchAndSort;


/**
 * 分析二分查找的一个技巧是:不要出现 else,而是把所有情况用 else if 写清楚,这样可以清楚地展现所有细节.
 * 本文都会使用 else if,旨在讲清楚,读者理解后可自行简化.计算 mid 时需要技巧防止溢出,建议写成: mid = left + (right - left) / 2
 */
public class BinarySearch {

    /**
     * 寻找一个数（基本的二分搜索,数组全部有序,不含重复元素或者在有重复元素时只需找到任意一个即可）
     * 这个场景是最简单的,可能也是大家最熟悉的,即搜索一个数,如果存在,返回其索引,否则返回 -1
     *
     * @param nums
     * @param target
     * @return
     */
    static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);   //写成这种形式必须要加运算符,因为算术运算符的优先级高于移位运算符
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else if (target < nums[mid])
                right = mid - 1;
        }
        return -1;
    }

    /**
     * 寻找一个数,数组全部有序,可能含有重复元素,需要找到最左侧的元素的索引
     *
     * @param nums
     * @param target
     * @return
     */
    static int left_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + ((right - left) >> 1);   //写成这种形式必须要加运算符,因为算术运算符的优先级高于移位运算符
            if (nums[mid] == target)
                right = mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else if (target < nums[mid])
                right = mid;
        }
        if (left == nums.length) return -1;
        return nums[left] == target ? (left) : -1;
    }

    /**
     * 寻找一个数,数组全部有序,可能含有重复元素,需要找到最右侧的元素的索引
     *
     * @param nums
     * @param target
     * @return
     */
    static int right_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target)
                left = mid + 1;
            else if (nums[mid] < target)
                left = mid + 1;
            else if (target < nums[mid])
                right = mid - 1;
        }
        if (left == 0) return -1;
        return nums[left - 1] == target ? (left - 1) : -1;
    }


    /**
     * 求一个数的平方根使用二分查找,当mid = x / mid 或者 x / mid - mid  < 0.00001 / mid时查找成功,返回mid
     *
     * @param x
     * @return
     */
    public static double mySqrt(double x) {
        if (x <= 1) return x;
        double left = 1;
        double right = x - 1;
        while (left <= right) {
            double mid = left + (right - left) / 2;
            if (mid > x / mid) {
                right = mid;
            } else if (mid < x / mid) {
                if (x / mid - mid < 0.00001 / mid) return mid;
                left = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] nums1 = new int[]{0, 1, 2, 3, 4, 5};
        int[] nums2 = new int[]{0, 1, 2, 2, 2, 3};
        System.out.println(binarySearch(nums1, 2));
        System.out.println(left_bound(nums2, 2));
        System.out.println(right_bound(nums2, 2));
        System.out.println(mySqrt(12));     //3.4641013145446777
    }

}
