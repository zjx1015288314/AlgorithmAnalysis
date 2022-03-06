package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zhaojiexiong
 * @create 2020/6/23
 * @since 1.0.0
 */
public class GetLowestString {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.valueOf(br.readLine());
        String[] strs = new String[len];
        for(int i = 0; i < len; i++){
            strs[i] = br.readLine();
        }
        System.out.println(lowestString(strs));
    }
    static class MyComparator implements Comparator<String> {
        public int compare(String a, String b){
            return (a + b).compareTo(b + a);
        }
    }

    private static String lowestString(String[] strs){
        if(strs == null || strs.length == 0){
            return "";
        }
        //根据新的排序方式排序
        Arrays.sort(strs,new MyComparator());
        String res = "";
        for(int i = 0; i < strs.length; i++){
            res += strs[i];
        }
        return res;
    }
}
