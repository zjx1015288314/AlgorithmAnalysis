package com.zjx.各公司2021笔试代码题汇总.网易第二次笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class WY2021Test3 {
    static class Cangku{
        int num;
        int times;
        Cangku parent;
        Map<Cangku,Integer> map;
        public Cangku(){
            times = 0;
            map = new HashMap<>();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int K = Integer.parseInt(str[1]);
        int M = Integer.parseInt(str[2]);

        Cangku[] houses = new Cangku[N + 1];
        for (int i = 1; i <= N; i++) {
            houses[i] = new Cangku();
        }

        for (int i = 0; i < M; i++) {
            str = br.readLine().split(" ");
            int start = Integer.parseInt(str[0]);
            int end = Integer.parseInt(str[1]);
            int times = Integer.parseInt(str[2]);
            houses[start].num = start;
            houses[end].num = end;
            houses[start].map.put(houses[end],times);
            houses[end].parent = houses[start];
        }
        System.out.println(process(houses,K));

    }

    public static int process(Cangku[] houses, int start){
        int maxDistance = 0;
        boolean[] visited = new boolean[houses.length];
        visited[start] = true;
        Queue<Cangku> queue = new LinkedList<>();
        queue.offer(houses[start]);
        while (!queue.isEmpty()){
            Cangku ck = queue.poll();
            if (ck != houses[start]){
                Cangku p = ck.parent;
                ck.times = p.times + p.map.get(ck);
                visited[ck.num] = true;
                maxDistance = Math.max(ck.times,maxDistance);
            }
            if (!ck.map.isEmpty()){
                for (Cangku cangku : ck.map.keySet()){
                    queue.offer(cangku);
                }
            }
        }
        boolean flag = false;
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i]){flag = true;}
        }
        return flag ? -1 : maxDistance;
    }
}
