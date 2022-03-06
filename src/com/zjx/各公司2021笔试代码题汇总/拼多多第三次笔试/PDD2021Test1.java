package com.zjx.各公司2021笔试代码题汇总.拼多多第三次笔试;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class PDD2021Test1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        char[][] matrix = new char[N][M];

        int endX = -1;
        int endY = -1;
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                matrix[i][j] = s.charAt(j);
                if (s.charAt(j) == 'T'){
                    endX = i;
                    endY = j;
                }
            }
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{endX - 1,endY});
        queue.offer(new int[]{endX + 1,endY});
        queue.offer(new int[]{endX,endY - 1});
        queue.offer(new int[]{endX,endY + 1});
        boolean[][] visited = new boolean[N][M];
        visited[endX][endY] = true;
        bfs(matrix,1, queue,visited);
        if (res.isEmpty()){
            System.out.println(0);
        }else{
            System.out.println(minSum);
            for (int[] ant : res) {
                System.out.print(ant[0] + " " + ant[1] + " ");
            }
        }
    }

    private static int minSum = Integer.MAX_VALUE;
    private static PriorityQueue<int[]> res = new PriorityQueue<int[]>((o1,o2) -> o1[0] != o2[0] ? o1[0] - o2[0]:o1[1] - o2[1]);

    private static void bfs(char[][] matrix,int curSum, Queue<int[]> queue,boolean[][] visited) {
        if (queue.isEmpty()) return;
        int len = queue.size();
        for (int i = 0; i < len; i++) {
            int[] arr = queue.poll();
            int x = arr[0];
            int y = arr[1];
            if(x < 0 || x >= matrix.length || y < 0 ||
                    y >= matrix[0].length || matrix[x][y] == '1' || visited[x][y] == true) continue;
            visited[x][y] = true;
            if (matrix[x][y] == 'X'){
                if (curSum <= minSum){
                    minSum = curSum;
                    res.offer(new int[]{x,y});
                }
            }else if (matrix[x][y] == '1'){
                continue;
            }else{
                queue.offer(new int[]{x - 1,y});
                queue.offer(new int[]{x + 1,y});
                queue.offer(new int[]{x,y - 1});
                queue.offer(new int[]{x,y + 1});
            }
        }
        bfs(matrix,curSum + 1,queue,visited);
    }
}
