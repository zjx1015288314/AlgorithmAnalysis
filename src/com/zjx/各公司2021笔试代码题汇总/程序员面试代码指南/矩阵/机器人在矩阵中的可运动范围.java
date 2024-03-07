package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.矩阵;

/**
 * 地上有一个rows行和cols列的方格。坐标从 [0,0] 到 [rows-1,cols-1]。一个机器人从坐标0,0的格子开始移动，
 * 每一次只能向左，右，上，下四个方向移动一格，但是不能 进入行坐标和列坐标的数位之和大于threshold 的格子。
 * 例如，当threshold为18时，机器人能够进入方格[35,37]，因为3+5+3+7 = 18。但是，它不能进入方格[35,38]，
 * 因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
 *
 * 和{矩阵中是否存在与字符串匹配的路径}有点像，但又不完全一样，最大的区别在于此题路径不必连续，四个方向求总和即可
 * @link https://www.nowcoder.com/practice/6e5207314b5241fb83f2329e89fdecc8?tpId=13&tags=&title=&difficulty=0&judgeStatus=0&rp=0
 */
public class 机器人在矩阵中的可运动范围 {
    int[][] coordinates = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int movingCount(int threshold, int rows, int cols) {
        if (rows <= 0 || cols <= 0 || threshold < 0) return 0;
        boolean[][] visited = new boolean[rows][cols];

        int num = movingCountHelper(rows, cols, 0, 0, visited, threshold);
        return num;
    }

    public int movingCountHelper(int rows, int cols, int x, int y, boolean[][] visited, int threshold) {
        if(sum(x) + sum(y) > threshold) return 0;

        visited[x][y] = true;

        int pathNum = 1;
        for(int[] coordinate: coordinates){
            int newX = x + coordinate[0];
            int newY = y + coordinate[1];
            if(newX >= 0 && newX < rows &&  newY >= 0 && newY < cols && !visited[newX][newY]) {
                int num = movingCountHelper(rows, cols, newX, newY, visited, threshold);
                pathNum += num;    //这里不是计算连续路径，所以不是四个方向求最大
            }
        }
//         visited[x][y] = false;            //不能写  不是回溯，走过了之后就确定了，防止重复计算
        return pathNum;
    }

    public int sum(int x) {
        int sum = 0;
        while(x != 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }
}
