package com.zjx.codingInterviewGuide.网易;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


/**
 * 给出一个长度为m的序列T，求一个长度为n且字典序最小的排列S，要求不改变原序列中元素的相对位置。
 * 第一行输入两个正整数n和m
 * 第二行输入m个数，表示序列
 * 5 3
 * 2 1 5
 * 输出
 * 2 1 3 4 5
 */
public class WangyiTest2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int[] arr = new int[m];
        str = br.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }
        List<Integer> res = process(arr,n);

        for (int item : res) {
            System.out.print(item + " ");
        }
        System.out.println();
        int[] res1 = process1(arr,n);
        for (int i : res1) {
            System.out.print(i + " ");
        }
    }

    //自己的解法，只过了40%
    public static List<Integer> process(int[] arr, int n){
        LinkedList<Integer> list1 = new LinkedList<>(); // arr中元素
        LinkedList<Integer> list2 = new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
            list1.add(arr[i]);
        }
        for (int i = 1; i <= n; i++) {
            if(list1.size() + list2.size() == n) break;
            if (!list1.contains(i)){
                list2.add(i);
            }
        }
        List<Integer> res = new ArrayList<>();
        while(!list1.isEmpty() && !list2.isEmpty()){
            int a = list1.peekFirst();
            int b = list2.peekFirst();
            if(a < b){
                res.add(list1.pollFirst());
            }else{
                res.add(list2.pollFirst());
            }
        }
        if(!list1.isEmpty()){
            res.addAll(list1);
        }else{
            res.addAll(list2);
        }
        return res;
    }

    //别人的解法
    public static int[] process1(int[] arr, int n){
        int[] res = new int[n];
        int m = arr.length;
        int[] tmp = new int[n-m];
        HashSet<Integer> set = new HashSet<>();
        for(int i : arr){
            set.add(i);
        }
        int index = 1;  //从1开始
        for(int i = 0;i < tmp.length; i++){
            while(set.contains(index))
                index++;
            tmp[i] = index++;
        }
        int index1 = 0;
        int index2 = 0;
        for(int i = 0; i < res.length; i++){
            if(index1 < m && index2 < n-m){
                if(arr[index1] <= tmp[index2]){
                    res[i] = arr[index1++];
                }else{
                    res[i] = tmp[index2++];
                }
            }else if(index1<m){
                res[i] = arr[index1++];
            }else{
                res[i] = tmp[index2++];
            }
        }
        return res;
    }
}
