package com.zjx.各公司2021笔试代码题汇总.字节;

/**
 * @author zhaojiexiong
 * @create 2020/5/19
 * @since 1.0.0
 */
public class DFSTest {
    private boolean[][] marked = null;

    public boolean exist(char[][] board, String word) {
        //this.word = word;
        //this.board = board;
        marked = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (dfs(board, word, 0, i, j)) return true;
            }
        }
        return false;
    }

    //用一个marked[][]来记录是否被用过,凡进入dfs都是未被标记过
    private boolean isSuccess = false;

    public boolean dfs(char[][] board, String word, int start, int i, int j) {
        if (start == word.length()) return true;
        if (board[i][j] == word.charAt(start)) {
            marked[i][j] = true;
            if (!isSuccess && isInArr(i - 1, j, board) && !marked[i - 1][j]) {
                if (dfs(board, word, start + 1, i - 1, j)) isSuccess = true;
            }
            if (!isSuccess && isInArr(i + 1, j, board) && !marked[i + 1][j]) {
                if (dfs(board, word, start + 1, i + 1, j)) isSuccess = true;
            }
            if (!isSuccess && isInArr(i, j - 1, board) && !marked[i][j]) {
                if (dfs(board, word, start + 1, i, j - 1)) isSuccess = true;
            }
            if (!isSuccess && isInArr(i, j + 1, board) && !marked[i][j]) {
                if (dfs(board, word, start + 1, i, j + 1)) isSuccess = true;
            }
            marked[i][j] = false;
            if (isSuccess) return true;
        }
        return false;
    }

    public boolean isInArr(int x, int y, char[][] board) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length;
    }

    public void testIsexit() {
        char[][] c = new char[3][4];
        c[0] = new char[]{'A', 'B', 'C', 'E'};
        c[1] = new char[]{'S', 'F', 'C', 'S'};
        c[2] = new char[]{'A', 'D', 'E', 'E'};
        exist(c, "ABCCED");
    }

}
