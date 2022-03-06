package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author zhaojiexiong
 * @create 2020/7/11
 * @since 1.0.0
 */
public class 根据后序数组判断是不是二叉搜索树 {
    public static void main(String[] args) throws Exception{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] ss=reader.readLine().split(" ");
        int n = Integer.parseInt(ss[0]);
        int[] arr=new int[n];
        ss=reader.readLine().split(" ");
        for(int i=0;i<n;i++){
            arr[i]=Integer.parseInt(ss[i]);
        }
        System.out.println(isPostArray(arr));
    }

    public static boolean isPostArray(int[] arr){
        if(arr == null || arr.length == 0){
            return false;
        }
        return process(0,arr.length-1,arr);
    }

    public static boolean process(int l,int r,int[] arr){
        if(l == r) return true;
        int last = arr[r];
        //这里less设置不合理  按正常情况 less与more的语义是l与r两侧的值，但这里less取了奇怪的-1
        //导致for循环结束后，不能根据less != more - 1为false直接返回，故修改为less=l-1
        //int less = -1;
        int less = l - 1;
        int more = r;
        for(int i = l; i < r; i++){
            if(arr[i] < last){
                less = i;   //小于r的最右边元素
            }else{
                more = more == r ? i : more;  //大于r的最左边元素
            }
        }
        //less more是否相邻，不是的话直接返回false
        if(less != more - 1){
            return false;
        }
        //单侧有值的情况
        if(less ==  l - 1 || more == r){
            return process(l,r - 1,arr);
        }
        //类似快排，将区间分为两个子区间继续
        return process(l,less,arr) && process(more,r - 1,arr);
    }
}
