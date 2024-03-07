package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.矩阵;

/**
 * 编写一个高效的算法来搜索mxn矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii
 */
public class 行列升序二维矩阵搜索目标值 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        int row = matrix.length;
        int col = matrix[0].length;

        // (i, j) 从右上角开始搜索 这个位置有点像有序数组的中点
        int i = 0;
        int j = col - 1;
        while(i < row && j >= 0) { //因i递增，所以不用判断i >= 0， j递减不用判断j < col
            if(matrix[i][j] == target) {
                return true;
            } else if(matrix[i][j] < target) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }
}
