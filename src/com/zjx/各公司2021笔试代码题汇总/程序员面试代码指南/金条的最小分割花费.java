package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * @author zhaojiexiong
 * @create 2020/6/29
 * @since 1.0.0
 */
public class 金条的最小分割花费 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        int[] arr = new int[len];
        String[] input = br.readLine().split(" ");
        for(int i = 0; i < len; i++){
            arr[i] = Integer.parseInt(input[i]);
        }
        System.out.print(getMinSplitCost(arr));
    }

    private static int getMinSplitCost(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int i = 0; i < arr.length; i++){
            minHeap.add(arr[i]);
        }
        int ans = 0;
        while(minHeap.size() != 1){
            int sum = minHeap.poll() + minHeap.poll();
            ans += sum;
            minHeap.offer(sum);
        }
        return ans;
    }
}
