package com.zjx.codingInterviewGuide;

import java.util.HashMap;
import java.util.PriorityQueue;

public class TopKFrequent {
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
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((o1, o2) -> map.get(o1) - map.get(o2));
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
