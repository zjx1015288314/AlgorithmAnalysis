package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zhaojiexiong
 * @create 2020/6/28
 * @since 1.0.0
 */
public class 宽度优先搜索求最短通路值 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int row = Integer.parseInt(input[0]);
        int col = Integer.parseInt(input[1]);
        int[][] matrix = new int[row][col];
        for(int i = 0; i < row; i++){
            String s = br.readLine();
            for(int j = 0; j < col; j++){
                if(s.charAt(j) == '1'){
                    matrix[i][j] = 1;
                }
            }
        }
        System.out.print(getShortestPath(matrix));
    }

    private static int getShortestPath(int[][] m){
        if(m == null || m.length == 0 || m[0].length == 0 ||
                m[0][0] != 1 || m[m.length - 1][m[0].length - 1] != 1) return -1;
        int row = m.length;
        int col = m[0].length;
        int[][] map = new int[row][col];  //表示从(0,0)走到(i,j)位置最短的路径值
        map[0][0] = 1;
        //采用宽度优先遍历
        Queue<Integer> rQ = new LinkedList<>();  //存行坐标
        Queue<Integer> cQ = new LinkedList<>();  //存纵坐标
        rQ.add(0);
        cQ.add(0);
        int r = 0;
        int c = 0;
        while(!rQ.isEmpty()){
            r = rQ.poll();
            c = cQ.poll();
            if(r == m.length - 1 && c == m[0].length - 1){
                return map[r][c];
            }
            //除了这种方法，也可以写一个while循环，来遍历上下左右四个位置
            walkTo(map[r][c],r - 1,c,m,map,rQ,cQ);
            walkTo(map[r][c],r + 1,c,m,map,rQ,cQ);
            walkTo(map[r][c],r,c - 1,m,map,rQ,cQ);
            walkTo(map[r][c],r,c + 1,m,map,rQ,cQ);

        }
        return -1;
    }

    private static void walkTo(int pre,int toR, int toC, int[][] m,
                               int[][] map, Queue<Integer> rQ, Queue<Integer> cQ){
        if(toR < 0 || toR == m.length || toC < 0 || toC == m[0].length ||
                m[toR][toC] != 1 || map[toR][toC] != 0){
            return;
        }
        map[toR][toC] = pre + 1;
        rQ.add(toR);
        cQ.add(toC);
    }
}
