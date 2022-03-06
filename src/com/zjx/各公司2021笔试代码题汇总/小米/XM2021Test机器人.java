package com.zjx.各公司2021笔试代码题汇总.小米;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//6 6 20 10
//S#++O#
//OXX#X#
//++++++
//###XX#
//++#O#+
//OXO++X
//SSDDDDDAWWSSSAWSSSADDD
//10
public class XM2021Test机器人 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);
        int P = Integer.parseInt(str[2]);
        int Q = Integer.parseInt(str[3]);

        char[][] arr = new char[N][M];
        String s = null;
        int startX = -1;
        int startY = -1;
        for (int i = 0; i < N; i++) {
            s = br.readLine();
            for (int j = 0; j < M; j++) {
                if (s.charAt(j) == 'S'){
                    startX = i;
                    startY = j;
                }
                arr[i][j] = s.charAt(j);
            }
        }
        s = br.readLine();
        System.out.println(process(arr,N,M,P,Q,startX,startY,s));
    }

    private static int process(char[][] arr, int n, int m, int p, int q, int x, int y,String s) {
        char[] chs = s.toCharArray();
        int score = 0;
        for (int i = 0; i < chs.length; i++) {
            if (chs[i] == 'W'){
                if (x - 1 < 0 || arr[x - 1][y] == '#'){continue;}
                x -= 1;
                score = check(arr,x,y,score,p,q);
            }else if (chs[i] == 'A'){
                if (y - 1 < 0 || arr[x][y - 1] == '#'){continue;}
                y -= 1;
                score = check(arr,x,y,score,p,q);
            }else if (chs[i] == 'S'){
                if (x + 1 >= n || arr[x + 1][y] == '#'){continue;}
                x += 1;
                score = check(arr,x,y,score,p,q);
            }else if (chs[i] == 'D'){
                if (y + 1 >= m || arr[x][y + 1] == '#'){continue;}
                y += 1;
                score = check(arr,x,y,score,p,q);
            }
        }
        return score;
    }

    private static int check(char[][] arr, int x, int y ,int score,int p, int q) {
        if (arr[x][y] == 'O'){
            score += p;
        } else if (arr[x][y] == 'X'){
            score -= q;
            arr[x][y] = '+';
        }
        return score;
    }
}
