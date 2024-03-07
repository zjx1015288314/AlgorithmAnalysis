package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.矩阵;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 牛客链接：https://www.nowcoder.com/practice/b83cfe486b494a398609d18b94fb04d3?tpId=101&&tqId=33106&rp=1&ru=/ta/programmer-code-interview-guide&qru=/ta/programmer-code-interview-guide/question-ranking
 * 用一个整形矩阵matrix表示一个网格，1代表有路，0代表无路，每一个位置只要不越界，
 * 都有上下左右四个方向，求从最左上角到右下角的最短通路值
 * 例如，matrix为：
 * 1 0 1 1 1
 * 1 0 1 0 1
 * 1 1 1 0 1
 * 0 0 0 0 1
 * 通路只有一条，由12个1构成，所以返回12
 * [要求]
 * 时间复杂度为O(nm)，空间复杂度为O(nm)
 *
 * 输入描述:
 * 第一行两个整数N，M表示矩形的长宽
 * 接下来N行，每行一个长度为M的字符串表示矩形
 * 输出描述:
 * 输出一个整数表示最小步数
 * 若从(1, 1)无法到达(n, m)请输出-1
 * 示例1
 * 输入
 * 4 5
 * 10111
 * 10101
 * 11101
 * 00001
 * 输出
 * 12
 *
 * 注意：别用dp，因为dp是从左上角到右下角，这里的通路可能会从右边和下边的方向
 */
public class 宽度优先搜索矩阵最小通路 {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] s=br.readLine().split(" ");
        int n=Integer.parseInt(s[0]);
        int m=Integer.parseInt(s[1]);
        int[][] matrix=new int[n][m];
        for(int i=0;i<n;i++){
            String str=br.readLine();
            for(int j=0;j<m;j++){
                if(str.charAt(j)=='1'){
                    matrix[i][j]=1;
                }
            }
        }
        System.out.println(find(matrix));
    }


    public static int find(int[][] arr){
        if(arr==null||arr.length==0||arr[0].length==0)
            return -1;
        int row = arr.length;
        int col = arr[0].length;
        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        Queue<int[]> queue=new LinkedList<>();
        //map[i][j]表示从(0,0)到(i,j)的最短通路值，同时也兼容visited数组的角色(即保证该坐标未被遍历过)。
        // 为了保证最短，使用宽度优先遍历。如果任何方向都走不到，则map[i][j]为0；
        int[][] map = new int[row][col];
        queue.add(new int[]{0,0});
        map[0][0] = 1;
        while(!queue.isEmpty()){
            int[] a = queue.poll();
            int x = a[0];
            int y = a[1];
            if(x == arr.length - 1 && y == arr[0].length - 1){
                return map[x][y];
            }
            for(int[] b : directions){
                int newX = x + b[0];
                int newY = y + b[1];
                if(newX >= 0 && newX < row && newY >= 0 && newY < col
                        && arr[newX][newY] == 1 && map[newX][newY] == 0){
                    //到每个位置之前可以保证之前的位置是可以走到的，因为arr[newX][newY] == 1
                    map[newX][newY] = map[x][y] + 1;
                    queue.offer(new int[]{newX,newY});
                }
            }
        }
        return -1;
    }
}
