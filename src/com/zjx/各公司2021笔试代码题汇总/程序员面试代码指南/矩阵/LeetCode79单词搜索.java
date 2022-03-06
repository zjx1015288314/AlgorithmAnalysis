package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.矩阵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 3 4
 * ABCE
 * SFCS
 * ADEE
 * ABCCED
 */
public class LeetCode79单词搜索 {
    static int[][] tmp = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
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
     *
     * @return
     */
    public static boolean process(char[][] board, int i, int j, boolean[][] visited, char[] chs, int idx) {
        if (isSuccess) { //剪枝
            return true;
        }
        if (board[i][j] != chs[idx]) {
            return false;
        }
        if (idx == chs.length - 1) {
            isSuccess = true;
            return true;
        }

        visited[i][j] = true;

        for (int[] dir : tmp) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < row && y >= 0 && y < col && !visited[x][y]) {
                if (process(board, x, y, visited, chs, idx + 1)) {
                    isSuccess = true;
                    visited[i][j] = false;
                    return true;
                }
            }
        }
        visited[i][j] = false;
        return false;
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
        for (int[] dir : tmp) {
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
