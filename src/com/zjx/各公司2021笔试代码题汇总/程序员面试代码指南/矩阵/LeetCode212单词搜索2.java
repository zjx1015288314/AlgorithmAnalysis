package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.矩阵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个 m x n 二维字符网格 board 和一个单词（字符串）列表 words， 返回所有二维网格上的单词 。
 * 单词必须按照字母顺序，通过 相邻的单元格 内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母在一个单词中不允许被重复使用。
 * 4 4
 * oaan
 * etae
 * ihkr
 * iflv
 * oath pea eat rain
 *
 * 提示：
 * m == board.length
 * n == board[i].length
 * 1 <= m, n <= 12
 * board[i][j] 是一个小写英文字母
 * 1 <= words.length <= 3 * 10^4
 * 1 <= words[i].length <= 10
 * words[i] 由小写英文字母组成
 * words 中的所有字符串互不相同
 * 链接： https://leetcode.cn/problems/word-search-ii/description/
 */
public class LeetCode212单词搜索2 {

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
        str = br.readLine().split(" ");
        System.out.println(findWords(matrix, str));
    }

    /**
     * ！！！！时间复杂度O(k*m*n*3^(l - 1)), l为单词的最长长度 k为 words.length, 这题可以看出k很大，所以导致
     * 整体复杂度超出限制。所以办法是用前缀树把words数组的字符串存进去，这样可以在board数组遍历的时候顺便遍历前缀树
     * @see LeetCode212单词搜索2前缀树 ！！！！！！
     * @param board
     * @param words
     * @return
     */
    public static List<String> findWords(char[][] board, String[] words) {
        List<String> list = new ArrayList<>();
        if (board == null || board.length == 0 || board[0].length == 0 || words == null || words.length == 0) {
            return list;
        }
        boolean[][] visited = new boolean[row][col];
        for (int k = 0; k < words.length; k++) {
            String word = words[k];
            boolean flag = false;
            isSuccess = false; //每次开始前初始化
            for (int i = 0; i < row; i++) {
                if (flag) break;  //剪枝！！！
                for (int j = 0; j < col; j++) {
                    if (board[i][j] == word.charAt(0) && dfs(board, i, j, visited, word.toCharArray(), 1)) {
                        list.add(words[k]);
                        flag = true;
                        break;
                    }
                }
            }
        }
        return list;
    }

    public static boolean dfs(char[][] board, int i, int j, boolean[][] visited, char[] chs, int idx) {
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
                if (dfs(board, x, y, visited, chs, idx + 1)) {
                    isSuccess = true;   //！！！剪枝
                    res = true;
                }
            }
        }
        visited[i][j] = false;
        return res;
    }
}
