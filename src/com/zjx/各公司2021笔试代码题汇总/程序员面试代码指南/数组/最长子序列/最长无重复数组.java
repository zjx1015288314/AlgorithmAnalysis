package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.最长子序列;

import java.util.*;

/**
 *  给定一个长度为n的数组arr，返回arr的 最长无重复元素子数组(注意要连续) 的长度，无重复指的是所有数字都不相同。
 *  子数组是连续的，比如[1,3,5,7,9]的子数组有[1,3]，[3,5,7]等等，但是[1,3,7]不是子数组
 *
 *  数据范围：0≤arr.length≤10^5，0<arr[i]≤10^5
 *  [2,3,4,5] 4
 *  [2,2,3,4,3] 3
 *  [9] 1
 *  [1,2,3,1,2,3,2,2] 3
 *  [1,2,3,1,2,3,2,2,1,2,3,4,5,6,7,8,9,10] 10
 *  原题链接找不到了,但和这道字符串中求最长无重复子串类似，所以贴个链接: https://leetcode.cn/problems/wtcaE1/description/
 */
public class 最长无重复数组 {

    public int maxLength (int[] arr) {
        // write code here
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Map<Integer, Integer> itemToIndex = new HashMap<>();

        int start = 0;
        int cur = 0;
        int maxLen = 0;
        while (cur < arr.length) {
            // 如果当前元素在start和end之间出现过，那么start就要移动到这个元素的下一个位置
            // 注意这里的判断条件是itemToIndex.get(arr[end]) >= start，目的是让start不能后退，例如 [3,3,2,1,3,3,3,1]
            // 这段也可以写成, 更加简介明了
            //if (itemToIndex.containsKey(arr[cur])) {
            //    start = Math.max(start, itemToIndex.get(arr[end]) + 1);
            //}
            // !!!!!!第二次写觉得if判断的第二个条件太关键了，如果没有第二个条件就需要把start 到 itemToIndex.get(arr[cur])之间的元素都删除
            // 就像这样:
            //            if (exist.containsKey(c)) {
            //                int end = exist.get(c);
            //                for (int j = start; j <= end; j++) {
            //                    exist.remove(cs[j]);
            //                }
            //                 start = end + 1;
            //            } else {
            //                num = i - start + 1;
            //                maxNum = Math.max(maxNum, num);
            //            }
            if (itemToIndex.containsKey(arr[cur]) && itemToIndex.get(arr[cur]) >= start) { //!!!!!!
                start = itemToIndex.get(arr[cur]) + 1;
            }
            itemToIndex.put(arr[cur], cur);
            maxLen = Math.max(maxLen, cur - start + 1);
            cur++;
        }
        return maxLen;
    }

    /**
     * 我们还可以用一个队列，把元素不停的加入到队列中，如果有相同的元素，就把队首的元素移除，这样我们就可以保证队列中永远都没有重复的元素
     * @param arr
     * @return
     */
    private int maxLength1(int[] arr) {
        //用链表实现队列，队列是先进先出的
        Queue<Integer> queue = new LinkedList<>();
        int res = 0;
        for (int c : arr) {
            while (queue.contains(c)) {
                //如果有重复的，队头出队
                queue.poll();
            }
            //添加到队尾
            queue.add(c);
            res = Math.max(res, queue.size());
        }
        return res;
    }

    /**
     * Set集合也可以，且只有一层循环
     * @param arr
     * @return
     */
    private int maxLength2(int[] arr) {
        int left = 0, right = 0, max = 0;
        Set<Integer> set = new HashSet<>();
        while (right < arr.length) {
            if (set.contains(arr[right])) {
                set.remove(arr[left++]);
            } else {
                set.add(arr[right++]);
                max = Math.max(max, set.size());
            }
        }
        return max;
    }

}
