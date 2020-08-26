package com.zjx.codingInterviewGuide.矩阵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 4 4
 * oaan
 * etae
 * ihkr
 * iflv
 * oath pea eat rain
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
                if (flag) break;
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
