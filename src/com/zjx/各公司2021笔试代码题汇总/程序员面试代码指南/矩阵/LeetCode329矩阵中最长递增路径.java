package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.矩阵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 给定一个整数矩阵，找出最长递增路径的长度。
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。
 * 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
 * 输入: nums =
 * [
 *   [3,4,5],
 *   [3,2,6],
 *   [2,2,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径是[3, 4, 5, 6]。注意不允许在对角线方向上移动。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix
 * 时间复杂度：O(mn)，其中m和n分别是矩阵的行数和列数。深度优先搜索的时间复杂度是 O(V+E)，
 * 其中 V 是节点数，E 是边数。在矩阵中，O(V)=O(mn)，O(E)约等于O(4mn) = O(mn)
 * 空间复杂度：O(mn)，其中m和n分别是矩阵的行数和列数。空间复杂度主要取决于缓存和递归调用深度，
 * 缓存的空间复杂度是 O(mn)，递归调用深度不会超过 mn。
 */
public class LeetCode329矩阵中最长递增路径 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(str[j]);
            }
        }
        longestIncreasingPath1(matrix);
        System.out.println(maxLen);
    }

    /**
     * process的含义需要搞清，即从matrix[x][y]开始的最长递增序列的长度，所以在用maxRemainlen获得各个方向的最长长度后
     * 还需要+1才是最终结果。visited[x][y]表示matrix[x][y]在本次递归中是否遍历过，按逻辑来说没问题，但会超出时间限制
     * 所以后面优化成int[][] visited表示从matrix[x][y]开始的最长递增序列的长度，为0表示没遍历过，否则停止遍历，这样做
     * 每个matrix[x][y]之前的遍历结果就会复用，减少复杂度
     * O(4^n)    O(n)   其中n为矩阵中元素个数
     */
    public static int longestIncreasingPath1(int[][] matrix) {
        int maxLen = 0;
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[][] visited = new boolean[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                int len = process1(matrix, i, j, row, col, visited);
                maxLen = Math.max(maxLen, len);
            }
        }
        return maxLen;
    }

    public static int process1(int[][] matrix, int x, int y, int row, int col, boolean[][] visited) {
        visited[x][y] = true;
        int maxRemainlen = 0;
        int[][] directions = {{-1,0},{0,1},{1,0},{0,-1}};
        for(int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            if(newX >= 0 && newX < row && newY >= 0 && newY < col &&
                    !visited[newX][newY] && matrix[newX][newY] > matrix[x][y]) {
                int len = process1(matrix, newX, newY, row, col, visited);
                maxRemainlen = Math.max(maxRemainlen, len);
            }
        }
        visited[x][y] = false;
        return maxRemainlen + 1;
    }

    /**
     * DFS+缓存    选用！！！！！！！！！
     */
    public int longestIncreasingPath(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] visited = new int[row][col];  //将memory改为int  0代表未遍历 >=1表示长度,这样保证每个点只遍历一次
        int maxLen = Integer.MIN_VALUE;
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                int res = process(matrix,visited,i,j, row, col);
                maxLen = Math.max(maxLen,res);
            }
        }
        return maxLen;
    }

    public int process(int[][] matrix, int[][] visited, int i, int j, int row, int col){
        if(visited[i][j] != 0){
            return visited[i][j];
        }
        visited[i][j]++;  //至少为1
        int[][] tmp = {{0,-1},{0,1},{-1,0},{1,0}};
        for(int k = 0; k < 4; k++){
            int x = i + tmp[k][0];
            int y = j + tmp[k][1];  //不要写错j
            if(x >= 0 && x < row && y >= 0 && y < col && matrix[x][y] > matrix[i][j]){
                visited[i][j] = Math.max(visited[i][j],process(matrix, visited, x, y, row, col) + 1);
            }
        }
        return visited[i][j];
    }


    /**
     * 与方法一一样有着时间限制，本质上还是属于DFS
     */
    static int maxLen = Integer.MIN_VALUE;
    public static int longestIncreasingPath3(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[][] visited = new boolean[row][col];

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                process3(matrix,visited,i,j,1);
            }
        }
        return maxLen;
    }

    public static void process3(int[][] matrix, boolean[][] visited,int i, int j, int len){
        maxLen = Math.max(maxLen,len);
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] tmp = {{0,-1},{0,1},{-1,0},{1,0}};
        visited[i][j] = true;
        for(int k = 0; k < 4; k++){
            int x = i + tmp[k][0];
            int y = j + tmp[k][1];   //不要写错j
            if(x >= 0 && x < row && y >= 0 && y < col && !visited[x][y] && matrix[x][y] > matrix[i][j]){
                process3(matrix,visited,x,y,len + 1);
            }
        }
        visited[i][j] = false;
    }
}
