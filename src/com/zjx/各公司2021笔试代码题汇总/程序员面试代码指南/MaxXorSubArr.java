package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhaojiexiong
 * @create 2020/6/25
 * @since 1.0.0
 */
public class MaxXorSubArr {
    public static class Node{
        Node[] nexts = new Node[2];
    }

    public static class NumTrie{
        public Node head = new Node();

        //将newNum加入trie树
        public void add(int newNum){
            Node cur = head;
            for(int move = 31; move > -1; move--){
                int path = (newNum >> move) & 1;
                if(cur.nexts[path] == null){
                    cur.nexts[path] = new Node();
                }
                cur = cur.nexts[path];
            }
        }

        //求eorj的最大异或和
        public int maxXor(int eorj){
            Node cur = head;
            int res = 0;
            for(int move = 31; move > -1; move--){
                int path = (eorj >> move) & 1;
                int best = move == 31 ? path : (path ^ 1);
                //有就用，没有就选另一个,意思是肯定有一条路可以选，这样做的前提是下面在使用前提前插入了0
                best = cur.nexts[best] != null ? best : best ^ 1;
                res |= (path ^ best) << move;
                cur = cur.nexts[best];
            }
            return res;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        int[] arr = new int[len];
        String[] ss = br.readLine().split(" ");
        for(int i = 0; i < len; i++){
            arr[i] = Integer.parseInt(ss[i]);
        }
        System.out.print(getMaxXorSum(arr));
    }

    private static int getMaxXorSum(int[] arr){
        if(arr == null || arr.length == 0) return 0;
        int max = Integer.MIN_VALUE;
        int eor = 0;
        NumTrie numTrie = new NumTrie();
        numTrie.add(0);  //提前准备
        for(int i = 0; i < arr.length; i++){
            eor ^= arr[i];
            max = Math.max(max,numTrie.maxXor(eor));
            numTrie.add(eor);
        }
        return max;
    }
}
