package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数字相关;

/**
 * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。
 * 数值为0或者字符串不是一个合法的数值则返回0
 *
 * 输入描述：
 * 输入一个字符串,包括数字字母符号,可以为空
 * 返回值描述：
 * 如果是合法的数值表达则返回该数字，如果该数值超过Integer.MAX_VALUE/MIN_VALUE,则返回边界,否则返回0
 *
 * 输入: "+2147483647"    返回值：2147483647
 * 输入: "1a33"           返回值：0
 * 输入: "2147483648"     返回值：2147483647
 * @link https://www.nowcoder.com/practice/1277c681251b4372bdef344468e4f26e?tpId=13&tags=&title=&difficulty=0&judgeStatus=0&rp=0
 */
public class StrToNum {
    public int StrToInt(String str) {
        if(str == null || str.length() == 0) return 0;

        int i = 0;
        boolean positive = true;
        if(str.charAt(i) ==  '-') {
            positive = false;
            i++;
        } else if(str.charAt(i) == '+') {
            i++;
        }
        
        int sum = 0;
        int bound = Integer.MAX_VALUE / 10;
        for(int j = i; j < str.length(); j++) {
            if(str.charAt(j) < '0' || str.charAt(j) > '9') {
                sum = 0;
                break;
            }
            //防止非法字符出现在最后
            if (sum == Integer.MIN_VALUE || sum == Integer.MAX_VALUE) {
                continue;
            }
            //超过MAX_ALUE/MIN_VALUE的情况只返回边界值
            if(sum > bound || (sum == bound && str.charAt(j) > '7')){
                sum = positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            sum = sum * 10 + (str.charAt(j) - '0');
        }
        return positive ? sum : -1 * sum;
    }
}
