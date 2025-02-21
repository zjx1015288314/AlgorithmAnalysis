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
 * 输出:1
 *
 * 思路: 之前想用经典的矩阵回溯，但是这题虽然是矩阵，但可能不用回溯，一旦某个节点陆地遍历过后，就把它置为 非1。
 * 岛屿数量的累加在外层的方法中，即一旦grid[i][j] = '1'，即代表一块陆地，只是不知道陆地有多大，需要
 * dfs/bfs把相连的陆地找到置为非1（0 或者 2都行，具体处理看情景）
 */
public class LeetCode200岛屿数量 {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 ||grid[0].length == 0) return 0;

        int count = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                    //dfs(grid,i,j);
                    bfs(grid,i,j, new LinkedList<>());
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 复杂度分析
     * 时间复杂度：O(MN)，其中M和N分别为行数和列数。
     * 空间复杂度：O(MN)，在最坏情况下，整个网格均为陆地，深度优先搜索的深度达到MN。
     * @param grid
     * @param i
     * @param j
     */
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


    /**
     * 复杂度分析
     * 时间复杂度：O(MN)，其中M和N分别为行数和列数。
     * 空间复杂度：O(min(M,N))，在最坏情况下，整个网格均为陆地，队列的大小可以达到min(M,N)。
     *
     * @param grid
     * @param i
     * @param j
     * @param queue
     */
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


    /**
     * 集合的合并是在每次遍历矩阵的时候对4个方向进行合并，这样就可以在遍历的时候就把相邻的陆地合并到一个集合中。
     * 但是grid[i][j] = '0';这一步只有在遍历到当前位置时才操作，不能提前置为0
     *
     * 时间复杂度：O(MN×α(MN))，其中M和N分别为行数和列数。注意当使用路径压缩（见find函数）和按秩合并（见数组rank）实现并查集时，
     * 单次操作的时间复杂度为α(MN)，其中α(x)为反阿克曼函数，当自变量x的值在人类可观测的范围内（宇宙中粒子的数量）时，
     * 函数α(x)的值不会超过5，因此也可以看成是常数时间复杂度。
     *
     * 空间复杂度：O(MN)，这是并查集需要使用的空间。
     * 链接：https://leetcode.cn/problems/number-of-islands/solutions/13103/dao-yu-shu-liang-by-leetcode/
     * @param grid
     * @return
     */
    public int numIslands1(char[][] grid) {
        if(grid == null || grid.length == 0 ||grid[0].length == 0) return 0;

        UnionFind union = new UnionFind(grid);
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    int r = i * n + j;
                    if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                        union.union(r, (i - 1) * n + j);
                    }
                    if (i + 1 < m && grid[i + 1][j] == '1') {
                        union.union(r, (i + 1) * n + j);
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                        union.union(r, i * n + j - 1);
                    }
                    if (j + 1 < n && grid[i][j + 1] == '1') {
                        union.union(r, i * n + j + 1);
                    }
                }
            }
        }
        return union.count;
    }

    class UnionFind {
        int[] parent;
        // int[] rank;  //此题型用不到
        int count;

        public UnionFind(char[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            // rank = new int[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        count++;
                    }
                    parent[i * n + j] = i * n + j;
                }
            }
        }

        public void union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px != py) {
                parent[py] = parent[px];
                // rank[px]++;
                count--;
            }
        }

        public int find(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            //递归写法
            // if (parent[x] != x) {
            //     parent[x] = find(px);
            // }
            return parent[x];
        }
    }
}
