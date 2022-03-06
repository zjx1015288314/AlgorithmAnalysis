package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.中位数以及第K小的数相关;

public class 获取长度不等的两个有序数组的中位数 {
    public static void main(String[] args) {
        int[] arr1 = {};
        int[] arr2 = {1};
        double res = findMedianSortedArrays(arr1, arr2);
        double res1 = findMedianSortedArrays2(arr1, arr2);
        System.out.println(res);
    }

    /**
     * 方法一：
     * @ref{com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.
     * 数组.获取长度相等的两个有序数组的上(下)中位数.getLeftMedian2}
     * 时间复杂度为O(m + n) ,但未充分利用有序数组的特性
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if ((nums1 == null || nums1.length == 0) && (nums2 == null || nums2.length == 0)) return 0;

        int first = 0;
        int second = 0;

        int leftMedia = (nums1.length + nums2.length - 1) / 2;
        int rightMedia = (nums1.length + nums2.length) / 2;
        int leftTarget = leftMedia + 1;
        int rightTarget = rightMedia + 1;

        int idx1 = 0;
        int idx2 = 0;
        while (idx1 < nums1.length && idx2 < nums2.length) {
            leftTarget--;
            rightTarget--;
            if (leftTarget == 0) {
                first = nums1[idx1] <= nums2[idx2] ? nums1[idx1] : nums2[idx2];
            }

            if (rightTarget == 0) {
                second = nums1[idx1] <= nums2[idx2] ? nums1[idx1] : nums2[idx2];
            }

            if (nums1[idx1] <= nums2[idx2]) {
                idx1++;
            }else{
                idx2++;
            }
        }

        if (leftTarget >= 1) {
            first = idx1 < nums1.length ? nums1[idx1 + leftTarget - 1] : nums2[idx2 + leftTarget - 1];
        }

        if (rightTarget >= 1) {
            second = idx1 < nums1.length ? nums1[idx1 + rightTarget - 1] : nums2[idx2 + rightTarget - 1];
        }

        return (first + second) / 2.0;
    }

    /**
     * @ref{com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.长度不相等的有序数组中的第k小的数}
     */
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if ((nums1 == null || nums1.length == 0) && (nums2 == null || nums2.length == 0)) return 0;

        int leftTarget = (nums1.length + nums2.length + 1) / 2;  //上中位数为总的数组中第leftTarget小
        int rightTarget = (nums1.length + nums2.length + 2) / 2;

        int first = getKthMinNum(nums1, nums2, leftTarget);
        int second = getKthMinNum(nums1, nums2, rightTarget);

        return (first + second) / 2.0;

    }

    public static int getKthMinNum(int[] arr1, int[] arr2, int K) {
        if (arr1 == null || arr2 == null || K < 1 || K > arr1.length + arr2.length) {
            throw new RuntimeException("Input data is invalid!");
        }
        //防止有数组为空造成空指针异常
        if (arr1.length == 0 || arr2.length == 0) {
            return  arr1.length == 0 ? arr2[K - 1] : arr1[K - 1];
        }

        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
        int l = longs.length;
        int s = shorts.length;

        if (K <= s) {
            return getLeftMedian3(shorts, 0, K - 1, longs, 0, K - 1);
        } else if (K > l) {
            if (shorts[K - l - 1] >= longs[l - 1]) {
                return shorts[K - l - 1];
            }
            if (longs[K - s - 1] >= shorts[s - 1]) {
                return longs[K - s - 1];
            }
            return getLeftMedian3(shorts, K - l, s - 1, longs, K - s, l - 1);
        } else {
            if (longs[K - s - 1] >= shorts[s - 1]) {
                return longs[K - s - 1];
            }
            return getLeftMedian3(shorts, 0, s - 1, longs, K - s, K - 1);
        }
    }

    /**
     * 找到两数组的上中位数，利用数组的有序性，联想到二分法 O(logN)
     */
    private static int getLeftMedian3(int[] arr1, int s1, int e1, int[] arr2, int s2, int e2) {
        int mid1 = 0;
        int mid2 = 0;
        int offset = 0;
        while (s1 < e1) {
            mid1 = (s1 + e1) / 2;
            mid2 = (s2 + e2) / 2;
            offset = ((e1 - s1 + 1) & 1) ^ 1;   //个数为偶数时加1，奇数不变
            if (arr1[mid1] > arr2[mid2]) {
                e1 = mid1;
                s2 = mid2 + offset;
            } else if (arr1[mid1] < arr2[mid2]) {
                s1 = mid1 + offset;
                e2 = mid2;
            } else {
                return arr1[mid1];
            }
        }
        return Math.min(arr1[s1], arr2[s2]);
    }
}
