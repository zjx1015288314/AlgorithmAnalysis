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
    public static void main(String[] args) {
        int res = strToInt("-2147483648");
        System.out.println(res);
    }
    public static int strToInt(String str) {
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
            //这里比较巧妙的是 超出边界由两部分构成，第一部分是>bound. 第二部分是等于bound且 当前位>7,
            //s如果是正数当然没问题，如果是-2147483648的话也会被赋值Integer，MIN_VALUE
            //超过MAX_ALUE/MIN_VALUE的情况只返回边界值
            if(sum > bound || (sum == bound && str.charAt(j) > '7')){
                sum = positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            } else {
                sum = sum * 10 + (str.charAt(j) - '0');
            }
        }
        // 不加的话下面 如果sum是最小值 -1 * sum则会溢出导致结果正常
        if (sum == Integer.MIN_VALUE) {
            return sum;
        }
        return positive ? sum : -1 * sum;
    }
}
