package com.itzjx.mmall_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author zhaojiexiong
 * @create 2020/6/28
 * @since 1.0.0
 */
public class GetMaxProgramMoney {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int W = Integer.parseInt(input[1]);
        int K = Integer.parseInt(input[2]);
        int[] costs = new int[N];
        input = br.readLine().split(" ");
        for(int i = 0; i < N; i++){
            costs[i] = Integer.parseInt(input[i]);
        }
        int[] profits = new int[N];
        input = br.readLine().split(" ");
        for(int i = 0; i < N; i++){
            profits[i] = Integer.parseInt(input[i]);
        }
        System.out.print(maxMoney(W,K,costs,profits));
    }

    private static long maxMoney(long W, int K, int[] costs, int[] profits){
        if(W < 1 || K < 0 || costs == null || profits == null || costs.length != profits.length){
            return W;  ////这里返回值需要注意
        }

        PriorityQueue<Program> costMinHeap = new PriorityQueue<>(new CostMinComp());
        PriorityQueue<Program> profitMaxHeap = new PriorityQueue<>(new ProfitMaxComp());

        for(int i = 0; i < costs.length; i++){
            costMinHeap.offer(new Program(costs[i],profits[i]));
        }

        for(int i = K; i > 0; i--){
            while(!costMinHeap.isEmpty() && costMinHeap.peek().cost <= W){
                profitMaxHeap.offer(costMinHeap.poll());
            }
            if(!profitMaxHeap.isEmpty()){
                W += profitMaxHeap.poll().profit;
            }else{
                return W;
            }
        }
        return W;
    }

    public static class Program{
        public int cost;
        public int profit;
        public Program(int cost, int profit){
            this.cost = cost;
            this.profit = profit;
        }
    }

    //定义小根堆如何比较大小
    public static class CostMinComp implements Comparator<Program> {
        //@Override
        public int compare(Program o1, Program o2){
            return o1.cost - o2.cost;
        }
    }
    //定义大根堆如何比较大小
    public static class ProfitMaxComp implements Comparator<Program>{
        //@Override
        public int compare(Program o1, Program o2){
            return o2.profit - o1.profit;
        }
    }
}
