package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.字符串;

public class 字符串能否转换为科学计数法或者整数或者小数 {
    public static void main(String[] args) {
//        isNumeric("123.45e+6");
        isNumeric(".");
    }

    public static boolean isNumeric (String str) {
        // write code here
        if(str == null || str.length() == 0) return false;
        str = str.trim();

        return isInteger(str) || isMinute(str) || isScientificCount(str);
    }

    public static boolean isScientificCount(String str) {
        if(str == null || str.length() == 0) return false;

        int i = 0;
        while(i < str.length() && str.charAt(i) != 'e' && str.charAt(i) != 'E') {
            i++;
        }
        String first = str.substring(0, i);
        String second = "";
        if(i < str.length() && (str.charAt(i) != 'e' || str.charAt(i) != 'E')) {
            second = str.substring(i + 1);
        }
        boolean flagFirst = isInteger(first) || isMinute(first);
        boolean flagSecond = i == str.length() || isInteger(second);

        return flagFirst && flagSecond;
    }

    public static boolean isMinute(String str) {
        if(str == null || str.length() == 0) return false;

        int i = 0;
        boolean positive = true;
        if(str.charAt(i) ==  '-') {
            positive = false;
            i++;
        } else if(str.charAt(i) == '+') {
            i++;
        }

        if(i == str.length()) return false;  //至少一位数字

        boolean hasMinuteSeparator = false;
        int index = -1;
        for(int j = i; j < str.length(); j++) {
            if(str.charAt(j) == '.') {
                if (hasMinuteSeparator) {
                    return false;
                }else {
                    index = j;
                    hasMinuteSeparator = true;
                    continue;
                }
            }
            if(str.charAt(j) < '0' || str.charAt(j) > '9') {
                return false;
            }
        }
        if (hasMinuteSeparator) {
            //判断小数点前面有没有数字
            boolean preFlag = index > 0 && str.charAt(index - 1) >= '0' && str.charAt(index - 1) <= '9';

            //判断小数点后面有没有数字
            boolean nextFlag = index < str.length() - 1 && str.charAt(index + 1) >= '0' && str.charAt(index + 1) <= '9';

            return preFlag || nextFlag;
        }
        return false;
    }

    public static boolean isInteger(String str) {
        if(str == null || str.length() == 0) return false;

        int i = 0;
        boolean positive = true;
        if(str.charAt(i) ==  '-') {
            positive = false;
            i++;
        } else if(str.charAt(i) == '+') {
            i++;
        }

        if(i == str.length()) return false;  //至少一位数字

        int sum = 0;
        int bound = Integer.MAX_VALUE / 10;
        for(int j = i; j < str.length(); j++) {
            if(str.charAt(j) < '0' || str.charAt(j) > '9') {
                return false;
            }
        }
        return true;
    }
}
