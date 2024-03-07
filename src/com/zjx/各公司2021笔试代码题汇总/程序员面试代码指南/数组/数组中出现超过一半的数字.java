package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.数组;

/**
 * 给一个长度为 n 的数组，数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组[1,2,3,2,2,2,5,4,2]。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。
 * @link https://www.nowcoder.com/practice/e8a1b01a2df14cb2b228b30ee6a92163?tpId=13&tags=&title=&difficulty=0&judgeStatus=0&rp=0
 */
public class 数组中出现超过一半的数字 {
    public int MoreThanHalfNum_Solution(int [] array) {
        if(array == null || array.length == 0) {
            throw new RuntimeException("array is empty");
        }

        int count = 1;
        int preMostNum = array[0];
        for(int i = 1; i < array.length; i++){
            if(array[i] == preMostNum) {
                count++;
            } else {
                count--;
            }
            if(count == 0) {
                preMostNum = array[i];
                count = 1;
            }
        }
        count = 0;
        for (int j : array) {
            if (j == preMostNum) {
                count++;
            }
        }
        if(count <= array.length / 2) {
            throw new RuntimeException("find no moreThanHalfNum in array");
        }
        return preMostNum;
    }
}
