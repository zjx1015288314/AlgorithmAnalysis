package com.zjx.各公司2021笔试代码题汇总.网易.面试;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个year month day表示当前日期  求n天之后的日期
 */
public class 固定日期加上n天 {
    public static void main(String[] args) {
        process(2020,9,20,15);
        process(2020,2,20,9);
    }

    public static int[] process(int year,int month,int day, int n){
        int[] res = new int[3];
        Set<Integer> set = new HashSet();
        set.add(1);
        set.add(3);
        set.add(5);
        set.add(7);
        set.add(8);
        set.add(10);
        set.add(12);
        int[] months = new int[13];
        for (int i = 1; i <= 12; i++) {
            if (set.contains(i)){
                months[i] = 31;
            }else if (i == 2){
                months[i] = (year % 4 == 0 && year % 100 != 0) ? 29 : 28;
            }else{
                months[i] = 30;
            }
        }
        res[0] = year;
        res[1] = month;
        res[2] = day + n;
        while (res[2] > months[month]){
            res[2] -= months[month];
            res[1]++;
            if (res[1] > 12){
                res[1] -= 12;
                res[0]++;
                months[2] = (year % 4 == 0 && year % 100 != 0) ? 29 : 28;
            }
        }
        for (int i : res) {
            System.out.println(i + " ");
        }
        return res;
    }
}
