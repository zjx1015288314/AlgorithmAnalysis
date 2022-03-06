package com.zjx.各公司2021笔试代码题汇总.网易第二次笔试;


import java.util.*;

public class Test {
    public static void main(String[] args) {
        int[][] arr = {{0,3},{2,3},{1,4}};
        int[][] arr2 = {{1,0},{0,1}};
        int[] res = process1(5,arr);
        if (res.length == 0){
            System.out.println("");
        }else{
            for (int item : res) {
                System.out.print(item + " ");
            }
        }

    }
    public static class Process{
        int num;
        PriorityQueue<Integer> pre;
        //PriorityQueue<Integer> post;
        public Process(int num){
            this.num = num;
            pre = new PriorityQueue<>((o1, o2) -> o1 - o2);
            //post = new PriorityQueue<>((o1, o2) -> o1 - o2);
        }
    }
    public static int[] process1(int n,int[][] arr){
        Process[] processes = new Process[n];
        for (int i = 0; i < n; i++) {
            processes[i] = new Process(i);
        }
        for (int[] tmp : arr) {
            int post = tmp[0];
            int pre = tmp[1];
            processes[post].pre.offer(pre);
            //processes[pre].post.offer(post);
        }

        TreeMap<Integer, Queue<Integer>> map = new TreeMap<>((o1, o2) -> o1 - o2);
        for (int i = 0; i < n; i++) {
            for (int preNum : processes[i].pre){
                if (!map.containsKey(preNum)){
                    Queue<Integer> queue = new PriorityQueue<>((o1, o2) -> o1 - o2);
                    map.put(preNum,queue);
                }
                map.get(preNum).offer(i);
            }

        }
        boolean[] visited = new boolean[n];
        int[] res = new int[n];
        int idx = 0;
        while (!map.isEmpty()){
            Integer key = map.firstKey();

            if (visited[key]) return new int[]{};
            visited[key] = true;
            res[idx++] = key;
            Queue<Integer> queue = map.get(key);
            while (queue != null && !queue.isEmpty()){
                Integer num = queue.poll();
                if (!map.containsKey(num)){
                    map.put(num,new PriorityQueue<>());
                }
            }
            map.remove(key);
        }
        return res;
    }
}
