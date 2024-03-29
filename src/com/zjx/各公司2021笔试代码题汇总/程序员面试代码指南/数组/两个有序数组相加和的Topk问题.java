package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;


public class 两个有序数组相加和的Topk问题 {
    public static class Node{
        public int index1;  //arr1中位置
        public int index2;  //arr2中位置
        public int value;  //arr1[index1]+arr2[index2]的值

        public Node(int i1, int i2, int sum){
            index1 = i1;
            index2 = i2;
            value = sum;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] ss = br.readLine().split(" ");
        int N = Integer.parseInt(ss[0]);
        int K = Integer.parseInt(ss[1]);
        int[] arr1 = new int[N];
        int[] arr2 = new int[N];
        ss = br.readLine().split(" ");
        for(int i = 0; i < N; i++){
            arr1[i] = Integer.parseInt(ss[i]);
        }
        ss = br.readLine().split(" ");
        for(int i = 0; i < N; i++){
            arr2[i] = Integer.parseInt(ss[i]);
        }
        // 要先对数组进行排序
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        int[] res = getTopKSum(arr1,arr2,K);
        StringBuffer sb = new StringBuffer();
        if(res == null) System.out.print(sb);
        for(int i : res){
            sb.append(i + " ");
        }
        System.out.print(sb);
    }

    public static int[] getTopKSum(int[] arr1, int[] arr2, int topK){
        if(arr1 == null || arr2 == null || arr1.length == 0 || arr2.length == 0 || topK < 1){
            return null;
        }
        topK = Math.min(topK,arr1.length * arr2.length);
        int[] res = new int[topK];
        int resIdx = 0;
        PriorityQueue<Node> maxHeap = new PriorityQueue<>((o1, o2) -> o2.value - o1.value);
        int i1 = arr1.length - 1;
        int i2 = arr2.length - 1;
        maxHeap.add(new Node(i1, i2, arr1[i1] + arr2[i2]));

        Set<String> positionSet = new HashSet<>();
        positionSet.add(i1 + "_" + i2);

        while(resIdx != topK){
            Node cur = maxHeap.poll();
            res[resIdx++] = cur.value;
            i1 = cur.index1;
            i2 = cur.index2;
            if(i1 > 0 && !positionSet.contains((i1 - 1) + "_" + i2)){
                positionSet.add((i1 - 1) + "_" + i2);
                maxHeap.add(new Node(i1 - 1, i2, arr1[i1 - 1] + arr2[i2]));
            }
            if(i2 > 0 && !positionSet.contains(i1 + "_" + (i2 - 1))){
                positionSet.add(i1 + "_" + (i2 - 1));
                maxHeap.add(new Node(i1, i2 - 1, arr1[i1] + arr2[i2 - 1]));
            }
        }
        return res;
    }
}
