package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.中位数以及第K小的数相关;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
 * https://leetcode.cn/problems/top-k-frequent-elements/
 */
public class 数组中前K个高频元素 {
    public int[] topKFrequent(int[] nums, int k) {
        if(nums == null || nums.length == 0) return null;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        //PriorityQueue<Integer> heap = new PriorityQueue<Integer>((o1,o2) -> map.get(o1) - map.get(o2));
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(map::get));
        for(int n : map.keySet()){
            heap.add(n);
            if (heap.size() > k){
                heap.poll();
            }
        }
        int[] res = new int[Math.min(k, heap.size())];
        for(int i = 0; i < res.length; i++){
            res[i] = heap.poll();
        }
        return res;
    }

    /**
     * 不借助现有框架 实现一个冒泡排序，时间复杂度O(kn)，空间复杂度O(n)
     */
    public int[] topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> frequent = new HashMap<>();
        for (int num: nums) {
            frequent.put(num, frequent.getOrDefault(num, 0) + 1);
        }

        int[][] arr = new int[frequent.size()][2];
        int idx = 0;
        for (Map.Entry<Integer, Integer> entry : frequent.entrySet()) {
            arr[idx][0] = entry.getKey();
            arr[idx][1] = entry.getValue();
            idx++;
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j][1] > arr[j + 1][1]) {
                    int[] tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
            res[i] = arr[arr.length - 1 - i][0];
        }
        return res;
    }
}
