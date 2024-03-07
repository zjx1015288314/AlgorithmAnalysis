package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.字符串;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class 出现次数最多的k个字符串 {
    public static void main(String[] args) {
        int[] res = topKFrequent(new int[]{1},1);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }
    public static int[] topKFrequent(int[] nums, int k) {
        if(nums == null || nums.length == 0) return null;
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i : nums){
            if(!map.containsKey(i)){
                map.put(i,1);
            }else{
                map.put(i,map.get(i) + 1);
            }
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.comparingInt(map::get));
        for(int n : map.keySet()){
            heap.add(n);
            if (heap.size() > k){
                heap.poll();
            }
        }
        int[] res = new int[k];
        for(int i = 0; i < res.length; i++){
            res[i++] = heap.poll();
        }
        return res;
    }
}
