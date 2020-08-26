package com.zjx.codingInterviewGuide.猿辅导;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 完全二叉树逆时针打印边界(左边界，叶子节点，右边界)
 * 输入：
 * 5
 * 1 2 3 4 5
 * 输出：
 * 1 2 4 3 5
 */
public class PDD2021Test完全二叉树逆序打印 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        str = br.readLine().split(" ");
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }
        int[] res = process(arr);
        for(int i : res){
            System.out.print(i + " ");
        }
    }

    public static int[] process(int[] arr){
        if(arr.length <= 3) return arr;
        int[] res = new int[arr.length];
        LinkedList<Integer> lqueue = new LinkedList<>();
        LinkedList<Integer> rqueue = new LinkedList<>();
        LinkedList<Integer> leafqueue = new LinkedList<>();
        int level = 0;
        for (int i = 0; i < arr.length; i =i * 2 + 1) {
            level++;
            lqueue.offer(arr[i]);
        }
        for (int i = 0; i < arr.length; i = i * 2 + 2) {
            rqueue.offer(arr[i]);
        }

        for (int i = arr.length / 2; i < arr.length; i++) {
            if (((i + 1) & (i + 2)) == 0 || ((i + 1) & i) == 0){
                continue;
            }
            leafqueue.offer(arr[i]);
        }
        int idx = 0;
        for(int i : lqueue){
            res[idx++] = i;
        }
        rqueue.pollFirst();
        for(int i : rqueue){
            res[idx++] = i;
        }
        for(int i : leafqueue){
            res[idx++] = i;
        }
        return Arrays.copyOf(res,idx);
    }
}
