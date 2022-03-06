package com.zjx.各公司2021笔试代码题汇总.华为;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HW2021Test2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(",");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[0]);

        int[][] matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                if (s.charAt(j) == 'S') {
                    matrix[i][j] = 1;
                } else if (s.charAt(j) == 'H'){
                    matrix[i][j] = 0;
                }
            }
        }
//        System.out.println(process(matrix));
        System.out.println(process1(matrix));
    }

    public static int process(int[][] m){
        if(m == null || m.length == 0){
            return 0;
        }
        int count = 0;
        int row = m.length;
        int col = m[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (m[i][j] == 1){
                    count++;
                    infect(m,i,j,row,col);
                }
            }
        }
        return count;
    }

    public static void infect(int[][] m, int i, int j, int row, int col){
        if(i < 0 || i >= row || j < 0 || j >= col || m[i][j] != 1){
            return;
        }
        m[i][j] = 0;
        infect(m,i - 1,j,row,col);
        infect(m,i + 1,j,row,col);
        infect(m,i,j - 1,row,col);
        infect(m,i,j + 1,row,col);
    }

    public static int process1(int[][] m){
        if (m == null || (m.length == 1 && m[0].length == 1 && m[0][0] == 0)){
            return 0;
        }
        if (m.length == 1 && m[0].length == 1 && m[0][0] == 1){
            return 1;
        }
        int count = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if(m[i][j] == 1){
                    dfs(m,i,j);
                    count++;
                }
            }
        }
        return count;
    }

    public static void dfs(int[][] m, int i, int j){
        Queue<int[]> list = new LinkedList<>();
        list.add(new int[]{i,j});
        while (!list.isEmpty()){
            int[] cur = list.remove();
            i = cur[0];
            j = cur[1];
            if(i >= 0 && i < m.length && j >= 0 && j < m[0].length && m[i][j] == 1){
                m[i][j] = 0;
                list.add(new int[]{i + 1,j});
                list.add(new int[]{i - 1,j});
                list.add(new int[]{i,j + 1});
                list.add(new int[]{i,j - 1});
            }
        }
    }

}
