package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 链接: https://leetcode.cn/problems/n-queens/description/
 * @see N皇后II  N皇后II比这个简单，只需要返回解法的数量，所以用records数组记录皇后已占位置,records[i] = j表示第i行的皇后在第j列
 * 此题需要输出所有解法，所以用二维数组chess记录皇后位置，chess[i][j] = 'Q'表示第i行第j列有皇后， 总体思路是dfs+回溯
 */
public class N皇后 {

    private static List<List<String>> res;

    public static void main(String[] args) {
        System.out.println(solveNQueens(4));
    }

    public static List<List<String>> solveNQueens(int n) {
        char[][] chess = new char[n][n];
        // 全部填充为.
        for(char[] c : chess) {
            Arrays.fill(c, '.');
        }
        res = new ArrayList<>();
        process(0, n, chess);
        return res;
    }

    private static void process(int row, int n, char[][] chess) {
        if (row == n) {
            List<String> tmp = new ArrayList<>();
            for (char[] ch : chess) {
                tmp.add(new String(ch));
            }
            res.add(tmp);
        }

        for (int col = 0; col < n; col++) {
            if (isValid(row, col, n, chess)) {
                chess[row][col] = 'Q';
                process(row + 1, n, chess);
                chess[row][col] = '.';
            }
        }
    }

    private static boolean isValid(int row, int col, int n, char[][] chess) {
        // 检查这一列
        for(int i = 0; i < row; i++)
            if(chess[i][col] == 'Q') {
                return false;
            }
        // 检查这一行
        for(int j = 0; j < col; j++)
            if(chess[row][j] == 'Q') {
                return false;
            }
        // 检查45度的方向
        for(int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if(chess[i][j] == 'Q') {
                return false;
            }
        // 检查135度的方向
        for (int i = row - 1, j = col + 1; i >= 0 && j <= n - 1; i--, j++)
            if (chess[i][j] == 'Q') {
                return false;
            }
        return true;
    }
}
