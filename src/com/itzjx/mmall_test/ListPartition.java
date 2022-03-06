package com.itzjx.mmall_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhaojiexiong
 * @create 2020/7/9
 * @since 1.0.0
 */
public class ListPartition {
    public static class Node{
        public int val;
        public Node next;
        public Node(int val){
            this.val = val;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] ss = br.readLine().split(" ");
        int N = Integer.parseInt(ss[0]);
        int pivot = Integer.parseInt(ss[1]);
        Node dummyHead = new Node(-1);  //哑节点
        Node cur = dummyHead;
        ss = br.readLine().split(" ");
        for(int i = 0; i < N; i++){
            cur = cur.next = new Node(Integer.parseInt(ss[i]));
        }
        cur = listPartition(dummyHead.next,pivot,N);
        StringBuffer sb = new StringBuffer();
        while(cur != null){
            sb.append(cur.val + " ");
            cur = cur.next;
        }
        System.out.print(sb);
    }

    private static Node listPartition(Node head, int pivot, int N){
        if(head == null || head.next == null){
            return head;
        }
        Node[] nodeList = new Node[N];
        //将数组分成三个区域  small代表左边区域的末尾，index代表中间区域的末尾，big代表右边区域的开始
        int small = -1;
        int big = nodeList.length;
        int index = 0;
        while(index != big){
            if(nodeList[index].val < pivot){
                swap(nodeList,index++,++small);
            }else if(nodeList[index].val > pivot){
                swap(nodeList,index,--big);
            }else{
                index++;
            }
        }
        int i = 0;
        for(; i < nodeList.length - 1; i++){
            nodeList[i].next = nodeList[i + 1];
        }
        nodeList[i].next = null;
        return nodeList[0];
    }

    private static void swap(Node[] arr,int i, int j){
        Node tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
