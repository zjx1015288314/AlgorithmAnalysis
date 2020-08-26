package com.zjx.codingInterviewGuide.矩阵;

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
 * 解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix
 * 时间复杂度：O(mn)，其中m和n分别是矩阵的行数和列数。深度优先搜索的时间复杂度是 O(V+E)，
 * 其中 V 是节点数，E 是边数。在矩阵中，O(V)=O(mn)，O(E)约等于O(4mn) = O(mn)
 * 空间复杂度：O(mn)，其中m和n分别是矩阵的行数和列数。空间复杂度主要取决于缓存和递归调用深度，
 * 缓存的空间复杂度是 O(mn)，递归调用深度不会超过 mn。
 */
public class LeetCode329矩阵中最长递增路径 {
    static int maxLen = Integer.MIN_VALUE;
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
        longestIncreasingPath(matrix);
        System.out.println(maxLen);
    }
    public static int longestIncreasingPath(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean[][] visited = new boolean[row][col];

        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                process(matrix,visited,i,j,matrix[i][j],1);
            }
        }
        return maxLen;
    }

    public static void process(int[][] matrix, boolean[][] visited,int i, int j, int num,int len){
        maxLen = Math.max(maxLen,len);
        int row = matrix.length;
        int col = matrix[0].length;
        int[][] tmp = {{0,-1},{0,1},{-1,0},{1,0}};
        visited[i][j] = true;
        for(int k = 0; k < 4; k++){
            int x = i + tmp[k][0];
            int y = j + tmp[k][1];   //不要写错j
            if(x >= 0 && x < row && y >= 0 && y < col && !visited[x][y] && matrix[x][y] > matrix[i][j]){
                process(matrix,visited,x,y,matrix[x][y],len + 1);
            }
        }
        visited[i][j] = false;
    }
}
