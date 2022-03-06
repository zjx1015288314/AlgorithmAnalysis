package com.zjx.各公司2021笔试代码题汇总.拼多多第二次笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//4 4
//1 0 1 1
//1 1 0 1
//0 0 0 0
//1 1 1 1
public class PDD2021Test {
    static int row = 0;
    static int col = 0;
    static int[][] tmp = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        row = N;
        col = M;
        int[][] matrix = new int[N][M];
        for (int i = 0; i < N; i++) {
            str = br.readLine().split(" ");
            for (int j = 0; j < M; j++) {
                matrix[i][j] = Integer.parseInt(str[j]);
            }
        }

        int numOfOne = 0;
        HashSet<String> set1 = new HashSet<>();
        HashSet<String> set2 = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (matrix[i][j] == 0) {
                    set1.add(i + "_" + j);
                } else {
                    set2.add(i + "_" + j);
                    numOfOne++;
                }
            }
        }
        for (String s : set1) {
            int i = s.charAt(0) - '0';
            int j = s.charAt(2) - '0';
            for (String ss : set2) {
                int x = ss.charAt(0) - '0';
                int y = ss.charAt(2) - '0';
                matrix[x][y] = 0;
                matrix[i][j] = 1;
                process(matrix, numOfOne);
                matrix[x][y] = 1;
                matrix[i][j] = 0;
            }
        }
        System.out.println(res);
    }

    static int res = Integer.MIN_VALUE;

    public static int process(int[][] matrix, int num) {
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 1) {
                    visited[i][j] = true;
                    Queue<String> queue = new LinkedList<>();
                    queue.add(i + "_" + j);
                    bfs(matrix, 1, visited, num, queue);
                    visited[i][j] = false;
                }
            }
        }
        return res;
    }


    public static void bfs(int[][] matrix, int sum, boolean[][] v, int num, Queue<String> queue) {
        if (sum >= num || queue.isEmpty()) return;
        Queue<String> queue1 = new LinkedList<>();
        while (!queue.isEmpty()) {
            String s = queue.poll();
            int x = s.charAt(0) - '0';
            int y = s.charAt(2) - '0';
            for (int[] arr : tmp) {
                int newX = x + arr[0];
                int newY = y + arr[1];
                if (newX >= 0 && newX < row && newY >= 0
                        && newY < col && !v[newX][newY] && matrix[newX][newY] == 1) {
                    v[newX][newY] = true;
                    queue1.add(newX + "_" + newY);
                    sum++;
                }
            }
        }
        res = Math.max(res, sum);
        bfs(matrix, sum, v, num, queue1);
    }
}
