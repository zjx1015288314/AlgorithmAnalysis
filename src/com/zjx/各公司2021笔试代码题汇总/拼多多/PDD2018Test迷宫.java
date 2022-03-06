package com.zjx.各公司2021笔试代码题汇总.拼多多;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class PDD2018Test迷宫 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] rowAndCol = br.readLine().split("\\s");
        int rows = Integer.parseInt(rowAndCol[0]);
        int cols = Integer.parseInt(rowAndCol[1]);
        char[][] map = new char[rows][cols];
        for(int i=0;i<rows;i++)
            map[i] = br.readLine().toCharArray();
        Node result = null;
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                if(map[i][j]=='2'){
                    result = bfs(i, j, rows, cols, map);
                    break;
                }
            }
        }
        System.out.println(result.step);
    }

    static int[][] direction = {{-1,0},{0,1},{1,0},{0,-1}};
    static boolean[][][] visit = null;

    //bfs的思想就是队列进出，每次遍历一层，所以在哪一层找到直接返回，不用回溯，所以不用重复设置visit
    public static Node bfs(int row,int col,int rows, int cols, char[][] map){
        Deque<Node> queue = new ArrayDeque();
        visit = new boolean[rows][cols][1024]; //只有10把锁，每一把锁占据1bit
        queue.add(new Node(row,col,0,0));
        visit[row][col][0] = true;
        while(!queue.isEmpty()){
            Node node = queue.pop();
            for(int i = 0; i < direction.length; i++){
                //查看每个节点的相邻节点是否能够下脚
                int nrow = node.row + direction[i][0];
                int ncol = node.col + direction[i][1];
                if(nrow<0||nrow>=rows||
                        ncol<0||ncol>=cols||
                        map[nrow][ncol]=='0')continue;
                char c = map[nrow][ncol];
                int keys = node.keys;
                if(c == '3')return new Node(nrow,ncol,keys,node.step+1);
                //如果是门，并且没有钥匙，则跳过
                if(c >= 'A'&& c <= 'Z'&&(keys & (1 << (c - 'A'))) == 0) continue;
                //如果是钥匙
                if(c >= 'a'&& c <= 'z') keys = keys | (1 << (c - 'a'));
                //具有相同的钥匙数时，不能往回走
                //只有拿到新的钥匙，才能判定这可能是有效路径，才能往回走
                if(!visit[nrow][ncol][keys]){
                    visit[nrow][ncol][keys] = true;
                    queue.add(new Node(nrow,ncol,keys,node.step+1));
                }
            }
        }
        return null;
    }

    static class Node{
        int keys;
        int step;
        int col;
        int row;
        public Node(int row,int col,int keys,int step){
            this.col = col;
            this.row = row;
            this.keys = keys;
            this.step = step;
        }
    }
}
