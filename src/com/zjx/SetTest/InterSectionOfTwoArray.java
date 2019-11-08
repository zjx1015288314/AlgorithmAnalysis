package com.zjx.SetTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class InterSectionOfTwoArray {

    //我的解法:时间复杂度O(M×N)  空间复杂度:M + 2Min(M,N)
    public static int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[]{};
        }
        LinkedList<Integer> result = new LinkedList<>();
        HashMap<Integer, Boolean> map = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            int item = nums1[i];
            if (!map.containsKey(item)) {
                map.put(item, true);
                for (int j = 0; j < nums2.length; j++) {
                    if (item == nums2[j]) {
                        result.add(item);
                        break;
                    }
                }
            }
        }
        int[] arr = new int[result.size()];
        for(int i = 0;i < arr.length;i++){
            arr[i] = result.get(i);
        }
        return arr;
    }


    //经典解法,时间复杂度：O(m+n)O(m+n)，其中 n 和 m 是数组的长度。O(n) 的时间用于转换 nums1 在集合中，O(m) 的时间用于转换 nums2 到集合中，并且平均情况下，集合的操作为 O(1)O(1)。
    //空间复杂度：O(m+n)O(m+n)，最坏的情况是数组中的所有元素都不同
    public int[] set_intersection(HashSet<Integer> set1, HashSet<Integer> set2) {
        int [] output = new int[set1.size()];
        int idx = 0;
        for (Integer s : set1)
            if (set2.contains(s)) output[idx++] = s;

        return Arrays.copyOf(output, idx);   //缩小空间
    }
    public int[] intersection1(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<Integer>();
        for (Integer n : nums1) set1.add(n);
        HashSet<Integer> set2 = new HashSet<Integer>();
        for (Integer n : nums2) set2.add(n);

        if (set1.size() < set2.size()) return set_intersection(set1, set2);
        else return set_intersection(set2, set1);
    }


    //也可以使用retainAll()  需要分析retainAll()的复杂度
    //时间复杂度：一般情况下是 O(m+n)O(m+n)，最坏情况下是 O(m \times n)O(m×n)。
    //空间复杂度：最坏的情况是 O(m+n)O(m+n)，当数组中的元素全部不一样时。

    class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            HashSet<Integer> set1 = new HashSet<Integer>();
            for (Integer n : nums1) set1.add(n);
            HashSet<Integer> set2 = new HashSet<Integer>();
            for (Integer n : nums2) set2.add(n);

            set1.retainAll(set2);

            int [] output = new int[set1.size()];
            int idx = 0;
            for (int s : set1) output[idx++] = s;
            return output;
        }
    }

}
