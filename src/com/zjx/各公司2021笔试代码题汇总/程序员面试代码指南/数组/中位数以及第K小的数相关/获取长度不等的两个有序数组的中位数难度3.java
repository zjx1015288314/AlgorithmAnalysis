package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.中位数以及第K小的数相关;

/**
 *
 * https://leetcode.cn/problems/median-of-two-sorted-arrays/
 * https://www.nowcoder.com/practice/b3b59248e61f499482eaba636305474b?tpId=196&tqId=40563&ru=/exam/oj
 *
 * 该题依赖于下面两个题型
 *  @see 获取长度相等的两个有序数组的上中位数难度1
 *  @see 长度不相等的有序数组中的第k小的数难度2
 *
 */
public class 获取长度不等的两个有序数组的中位数难度3 {
    public static void main(String[] args) {
        int[] arr1 = {-35,-29,-15,-1,27,36,42,43};
        int[] arr2 = {-39,-24,-24,-23,-14,-10,9,12,13,22,40};
        // res: -1.0
        double res1 = findMedianSortedArrays2(arr1, arr2);
        System.out.println("res: " + res1);
    }

    /**
     * @see 长度不相等的有序数组中的第k小的数难度2
     */
    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        if ((nums1 == null || nums1.length == 0) && (nums2 == null || nums2.length == 0)) return 0;

        int leftTarget = (nums1.length + nums2.length + 1) / 2;  //上中位数为总的数组中第leftTarget小
        int rightTarget = (nums1.length + nums2.length + 2) / 2;

        int first = getKth(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, leftTarget);
        int second = getKth(nums1, 0, nums1.length - 1, nums2, 0, nums2.length - 1, rightTarget);

        return (first + second) / 2.0;
    }

    public static int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        // 数组为空时, start = 0, end = -1, len = 0
        if (len1 == 0) return nums2[start2 + k - 1];
        // ！！！没有这段代码会引发50行的数组越界异常
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }


    //因为写法复杂被抛弃
    @Deprecated
    public static int getKthMinNum(int[] arr1, int[] arr2, int K) {
        if (arr1 == null || arr2 == null || K < 1 || K > arr1.length + arr2.length) {
            throw new RuntimeException("Input data is invalid!");
        }
        //防止有数组为空造成空指针异常
        if (arr1.length == 0 || arr2.length == 0) {
            return arr1.length == 0 ? arr2[K - 1] : arr1[K - 1];
        }

        int[] longs = arr1.length >= arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length < arr2.length ? arr1 : arr2;
        int l = longs.length;
        int s = shorts.length;
        // 三种情况
        // k <= s 可以直接求上中位数
        // k > l 把长短数组拼起来(短 + 长 , 长 + 短) 看第k位数能不能通过剪枝直接确定，不行的话求上中位数
        // s < k <= l 短 + 长 ，再看第k位数是不是可以剪枝, 不行的话求上中位数
        if (K <= s) {
            return getLeftMedian3(shorts, 0, K - 1, longs, 0, K - 1);
        } else if (K > l) {
            if (shorts[K - l - 1] >= longs[l - 1]) {
                return shorts[K - l - 1];
            }
            if (longs[K - s - 1] >= shorts[s - 1]) {
                return longs[K - s - 1];
            }
            //  规律： 数组的两个位置的值比较后，如果要缩小空间，一定是较大值所在数组往左(或小)缩，较大值所在数组往右(或大)缩
            return getLeftMedian3(shorts, K - l, s - 1, longs, K - s, l - 1);
        } else {
            if (longs[K - s - 1] >= shorts[s - 1])  {
                return longs[K - s - 1];
            }
            // longs[K - s - 1] < shorts[s - 1] 说明第K小的数在长数组的(K-s, k - 1)位和短数组的前s位之间
            return getLeftMedian3(shorts, 0, s - 1, longs, K - s, K - 1);
        }
    }

    /**
     * 找到两数组的上中位数，利用数组的有序性，联想到二分法 O(logN)
     */
    private static int getLeftMedian3(int[] arr1, int s1, int e1, int[] arr2, int s2, int e2) {
        while (s1 < e1) {
            int mid1 = (s1 + e1) / 2;
            int mid2 = (s2 + e2) / 2;
            int offset = ((e1 - s1 + 1) & 1) ^ 1;   //个数为偶数时加1，奇数不变
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

    /**
     * 方法一：
     * 时间复杂度为O(m + n) ,但未充分利用有序数组的特性
     * @see 获取长度相等的两个有序数组的上中位数难度1#getLeftMedian2(int[], int[])
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
}
