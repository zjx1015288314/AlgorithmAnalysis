package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.矩阵;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * 编写一个程序，通过填充空格来解决数独问题。
 *
 * 数独的解法需 遵循如下规则：
 *
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 *
 * 思路；递归+回溯
 * 最容易想到的方法是用一个数组记录每个数字是否出现。由于我们可以填写的数字范围
 * 为 [1, 9]，而数组的下标从 0开始，因此在存储时，我们使用一个长度为 9的布尔类
 * 型的数组，其中i个元素的值为True，当且仅当数字 i+1出现过。例如我们用
 * line[2][3]=True 表示数字 4在第2行已经出现过，那么当我们在遍历到第2行的空白格时,
 * 就不能填入数字 4
 */
public class 解数独游戏 {
    private boolean[][] line = new boolean[9][9];
    private boolean[][] column = new boolean[9][9];
    private boolean[][][] block = new boolean[3][3][9];
    private boolean valid = false;   //中断递归的标志，表示找到解
    private List<int[]> spaces = new ArrayList<int[]>();

    public void solveSudoku(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') {
                    spaces.add(new int[]{i, j});
                } else {
                    int digit = board[i][j] - '0' - 1;
                    line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                }
            }
        }

        //如果开头影响结果则要for循环遍历spaces，否则随便选择一个开始
        dfs(board, 0);
    }

    public void dfs(char[][] board, int pos) {
        if (pos == spaces.size()) {
            valid = true;
            return;
        }

        int[] space = spaces.get(pos);
        int i = space[0], j = space[1];
        for (int digit = 0; digit < 9 && !valid; ++digit) {
            if (!line[i][digit] && !column[j][digit] && !block[i / 3][j / 3][digit]) {
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = true;
                board[i][j] = (char) (digit + '0' + 1);
                dfs(board, pos + 1);
                line[i][digit] = column[j][digit] = block[i / 3][j / 3][digit] = false;
            }
        }
    }
}
