package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.矩阵;


import java.util.LinkedList;

/**
 * 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-islands
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 输入:
 * [
 * ['1','1','1','1','0'],
 * ['1','1','0','1','0'],
 * ['1','1','0','0','0'],
 * ['0','0','0','0','0']
 * ]
 * 输出: 1
 */
public class LeetCode200岛屿数量 {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 ||grid[0].length == 0) return 0;

        int count = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                    //dfs(grid,i,j);
                    bfs(grid,i,j,new LinkedList<Integer>());
                    count++;
                }
            }
        }
        return count;
    }

    public void dfs(char[][] grid, int i, int j){
        int row = grid.length;
        int col = grid[0].length;
        if(i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == '0'){
            return;
        }
        grid[i][j] = '0';
        dfs(grid,i + 1,j);
        dfs(grid,i - 1,j);
        dfs(grid,i,j + 1);
        dfs(grid,i,j - 1);
    }



    public void bfs(char[][] grid, int i, int j,LinkedList<Integer> queue){
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        // 小技巧：把坐标转换为一个数字
        queue.addLast(i * cols + j);
        // 注意：这里要标记上已经访问过
        //marked[i][j] = true;
        grid[i][j] = '0';
        while (!queue.isEmpty()) {
            int cur = queue.pollFirst();
            int curX = cur / cols;
            int curY = cur % cols;

            for (int k = 0; k < 4; k++) {
                int newX = curX + directions[k][0];
                int newY = curY + directions[k][1];
                if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && grid[newX][newY] == '1') {
                    queue.addLast(newX * cols + newY);
                    grid[newX][newY] = '0';  // 【特别注意】在放入队列以后，要马上标记成已经访问过
                }
            }
        }
    }
}
