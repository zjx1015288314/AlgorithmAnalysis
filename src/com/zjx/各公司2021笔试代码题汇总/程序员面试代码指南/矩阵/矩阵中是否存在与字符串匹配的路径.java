package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.矩阵;

/**
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
 * 路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，
 * 向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子
 *
 * @link https://www.nowcoder.com/practice/2a49359695a544b8939c77358d29b7e6?tpId=13&tags=&title=&difficulty=0&judgeStatus=0&rp=0
 *       https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/
 *
 * @ref{LeetCode79单词搜索}一样
 *
 * 思路：递归 + 回溯
 */
public class 矩阵中是否存在与字符串匹配的路径 {
    int[][] coordinates = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public boolean hasPath (char[][] matrix, String word) {
        if(word == null || word.length() == 0) return false;
        if(matrix.length == 0 || matrix[0].length == 0) return false;

        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        char[] words = word.toCharArray();
        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(hasPathHelper(matrix, rows, cols, i, j, words, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean hasPathHelper(char[][] matrix, int rows, int cols, int x, int y, char[] words, int idx, boolean[][] visited) {
        if (matrix[x][y] != words[idx]) return false;  //这里可以这样写的原因是入口前保证visited[x][y]=false;
        if(idx == words.length - 1) return true;

        visited[x][y] = true;

        for(int[] coordinate: coordinates){
            int newX = x + coordinate[0];
            int newY = y + coordinate[1];
            if(newX >= 0 && newX < rows &&  newY >= 0 && newY < cols && !visited[newX][newY]) {
                if(hasPathHelper(matrix, rows, cols, newX, newY, words, idx + 1, visited)) {
                    visited[x][y] = false;
                    return true;
                }
            }
        }

        visited[x][y] = false;
        return false;
    }
}
