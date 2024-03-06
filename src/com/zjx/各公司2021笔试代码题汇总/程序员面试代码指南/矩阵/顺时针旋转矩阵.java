package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.矩阵;

/**
 *  有一个NxN整数矩阵，请编写一个算法，将矩阵顺时针旋转90度。
 *  给定一个NxN的矩阵，和矩阵的阶数N,请返回旋转后的NxN矩阵。
 *  数据范围：0<n<300，矩阵中的值满足0≤val≤1000
 *
 *  要求：空间复杂度O(N^2)，时间复杂度O(N^2)
 *  进阶：空间复杂度O(1)，时间复杂度O(N^2)
 *
 *  https://www.nowcoder.com/practice/2e95333fbdd4451395066957e24909cc?tpId=295&tags=&title=&difficulty=0&judgeStatus=0&rp=0&sourceUrl=%2Fexam%2Foj
 *
 *  思路: 1.找到 行转列 规律, 逐行转换
 *       2.先整体按左对角翻转，再按中轴线翻转
 */
public class 顺时针旋转矩阵 {

    public int[][] rotateMatrix (int[][] mat, int n) {
        // write code here
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int tmp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = tmp;
            }
        }
        for (int i = 0; i < n; i++) {
            reverse(mat[i], 0, mat[i].length - 1);
        }
        return mat;
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    private void reverse(int[] a, int left, int right) {
        while (left < right) {
            swap(a, left++, right--);
        }
    }

    /**
     * 辅助数组
     * @param mat
     * @param n
     * @return
     */
    public int[][] rotateMatrix1(int[][] mat, int n) {
        // write code here
        int[][] temp = new int[n][n];//新建temp对象，作为最终返回的对象
        for(int i = 0;i < n;i++){
            for(int j = 0;j < n;j++){
                temp[j][n-1-i] = mat[i][j];//直接交换
            }
        }
        return temp;
    }

}
