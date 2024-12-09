package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.矩阵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 * 3 4
 * ABCE
 * SFCS
 * ADEE
 * ABCCED
 *
 * 提示：
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 *
 * 链接: https://leetcode.cn/problems/word-search/description/
 */
public class LeetCode79单词搜索 {
    static int[][] directs = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static int row = 0;
    static int col = 0;
    static boolean isSuccess = false;  //标记是否已成功找到

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        row = Integer.parseInt(str[0]);
        col = Integer.parseInt(str[1]);
        char[][] matrix = new char[row][col];
        String s = null;
        for (int i = 0; i < row; i++) {
            s = br.readLine();
            for (int j = 0; j < col; j++) {
                matrix[i][j] = s.charAt(j);
            }
        }
        s = br.readLine();
        System.out.println(exist(matrix, s));
    }

    public static boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null) {
            return false;
        }
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // if(process(board,i,j,visited,word.toCharArray(),0)){
                //    return true;
                // }  //通过

                //法二：进入之前先做判断
                if (board[i][j] == word.charAt(0) && process1(board, i, j, visited, word.toCharArray(), 1)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 法一
     * @return
     */
    private boolean process(char[][] board, int i, int j, boolean[][] visited, char[] chs, int idx) {
            if (board[i][j] != chs[idx]) {
                return false;
            }
            if (idx == chs.length - 1) {
                return true;
            }

            boolean success = false;
            visited[i][j] = true;
            for (int[] direct : directs) {
                int newI = i + direct[0];
                int newJ = j + direct[1];
                if (newI >= 0 && newI < board.length && newJ >= 0 && newJ < board[0].length && !visited[newI][newJ]) {
                    if (process(board, newI, newJ, visited, chs, idx + 1)) {
                        success = true;
                        // 剪枝 四个方向有一个匹配成功就退出 这里不能直接返回，回溯时需要重置visited
                        break;
                    }
                }
            }
            visited[i][j] = false;
            return success;
        }

    /**
     * 法二：这种情况不用特殊考虑{{"a"}} a 的情况  我们在进入process之前就处理完成
     * 思想与最长递增路径相同
     *
     * @return 是否找到
     */
    public static boolean process1(char[][] board, int i, int j, boolean[][] visited, char[] chs, int idx) {
        if (isSuccess) {
            return true;
        }   //！！！剪枝
        if (idx >= chs.length) {
            isSuccess = true;  //！！！剪枝
            return true;
        }
        visited[i][j] = true;
        boolean res = false;
        for (int[] dir : directs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < row && y >= 0 && y < col && !visited[x][y] && board[x][y] == chs[idx]) {
                if (process1(board, x, y, visited, chs, idx + 1)) {
                    isSuccess = true;   //！！！剪枝
                    res = true;
                }
            }
        }
        visited[i][j] = false;
        return res;
    }
}
