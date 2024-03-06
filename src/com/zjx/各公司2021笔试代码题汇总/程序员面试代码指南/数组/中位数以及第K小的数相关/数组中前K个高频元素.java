package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.中位数以及第K小的数相关;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

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
        int[] res = new int[k < heap.size() ? k : heap.size()];
        for(int i = 0; i < res.length; i++){
            res[i] = heap.poll();
        }
        return res;
    }
}
