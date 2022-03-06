package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.字符串;

public class CompareNum1AndNum2 {

    public static void main(String[] args) {
        boolean res = isEuqals(new Integer[]{}, new String[]{});
        System.out.println(res);
    }

    public static <T> boolean isEuqals(T[] src, T[] dst) {
        if(src == dst) {
            return true;
        }else if (src == null || dst == null) {
            return false;
        }
        if (src.getClass() != dst.getClass() ) {
            return false;
        }
        if(src.length != dst.length) {
            return false;
        }

        int len = src.length;
        for (int i = 0; i < len; i++) {
            T item1 = src[i];
            T item2 = dst[i];
            if (!item1.equals(item2)) {
                return false;
            }
        }
        return true;
    }
}
