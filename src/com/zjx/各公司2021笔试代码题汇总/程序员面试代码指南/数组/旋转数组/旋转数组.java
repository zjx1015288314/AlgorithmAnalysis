package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组.旋转数组;

/**
 * 一个数组A中存有 n 个整数，在不允许使用另外数组的前提下，将每个整数循环向右移 M（ M >=0）个位置，
 *  即将A中的数据由（A0 A1 ……AN-1 ）变换为（AN-M …… AN-1 A0 A1 ……AN-M-1 ）（最后 M 个数循环移至最前面的 M 个位置）。
 *  如果需要考虑程序移动数据的次数尽量少，要如何设计移动的方法？
 *
 *  数据范围：0<n≤100，0≤m≤1000
 *  进阶：空间复杂度O(1)，时间复杂O(n)
 *
 * https://www.nowcoder.com/practice/e19927a8fd5d477794dac67096862042?tpId=295&tags=&title=&difficulty=0&judgeStatus=0&rp=0&sourceUrl=%2Fexam%2Foj
 *  思路：先整体翻转，再分别翻转前M个和后N-M个
 *  @see com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.字符串.单词翻转
 */
public class 旋转数组 {

    public int[] solve (int n, int m, int[] a) {
        // write code here
        // 可以类比字符串翻转   先将整体翻转 之后根绝分隔符依次翻转子元素
        m = m % n;
        reverse(a, 0, a.length - 1);

        reverse(a, 0, m - 1);
        reverse(a, m, a.length - 1);
        return a;
    }

    private void reverse(int[] a, int left, int right) {
        while (left < right) {
            swap(a, left++, right--);
        }
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

}
