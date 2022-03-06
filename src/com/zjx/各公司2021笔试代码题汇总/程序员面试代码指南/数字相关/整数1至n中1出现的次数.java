package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数字相关;

/**
 * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数
 * 例如，1~13中包含1的数字有1、10、11、12、13因此共出现6次
 *
 * @link https://www.nowcoder.com/practice/bd7f978302044eee894445e244c7eee6?tpId=13&tags=&title=&difficulty=0&judgeStatus=0&rp=0
 *
 * 思路：
 * https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/solution/mian-shi-ti-43-1n-zheng-shu-zhong-1-chu-xian-de-2/
 * 将 1 ~ n的个位、十位、百位、...的 11 出现次数相加，即为 11 出现的总次数。
 * 设数字n是个x位数，记n的第i位为n_i，则可将 n写为 n_{x} n_{x-1}...n_{2} n_{1}
 *      称 "n_i"为当前位，记为cur;
 *      将 "n_{i-1}n_{i-2}...n_{2}n_{1}"称为低位，记为low;
 *      将 "n_{x}n_{x-1}...n_{i+2}n_{i+1}"称为高位，记为high。
 *      将 10^i称为位因子，记为digit。
 */
public class 整数1至n中1出现的次数 {
    public int NumberOf1Between1AndN_Solution(int n) {
        int high = n / 10;
        int cur = n % 10;
        int low = 0;
        int countOfOne = 0;
        int digit = 1;
        while(high != 0 || cur != 0) {
            if(cur == 0) {
                countOfOne += high * digit;
            }else if (cur == 1) {
                countOfOne += high * digit + low + 1;
            }else {
                countOfOne += (high + 1) * digit;
            }
            low += cur * digit;
            cur = high % 10;
            high = high / 10;
            digit *= 10;
        }
        return countOfOne;
    }
}
