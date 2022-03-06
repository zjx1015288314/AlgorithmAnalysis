package com.zjx.各公司2021笔试代码题汇总.网易第二次笔试;

public class Test2 {
    static int preStart = -1;
    static int preEnd = -1;
    public static boolean booking(int start,int end){
        if (preEnd == -1){
            preStart = start;
            preEnd = end;
            return true;
        }else{
            if (start > preEnd){
                preStart = start;
                preEnd = end;
                return true;
            }else{
                preEnd = Math.max(preEnd,end);
                return false;
            }
        }
    }
}
