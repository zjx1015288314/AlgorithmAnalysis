package com.zjx.DataStructure;

import java.util.Date;
import java.util.Random;
import java.util.stream.IntStream;

public class Chapter2 {

        public static void test1(int N,int[] a){
            int i = 0;
            long startTime = System.currentTimeMillis();
            while(i < N){
                int X = randInt(0,i);
                int j;
                for (j = 0; j < i; j++) {
                    if (X == a[j]){
                        break;
                    }
                }
                if (j == i){
                    a[i] = X;
                    i++;
                }

            }
            System.out.println("the runtime of test1() is " + (System.currentTimeMillis() - startTime) + "ms");
        }

        public static void test2(int N,int[] a){
            boolean[] used = new boolean[N];
            int i = 0;
            long startTime = System.currentTimeMillis();
            while(i < N){
                int X = randInt(0,i);
                if (used[X] == false) {
                    a[i] = X;
                    used[X] = true;
                    i++;
                }
            }
            System.out.println("the runtime of test2() is " + (System.currentTimeMillis() - startTime) + "ms");
        }

        public static void test3(int N,int[] a){
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < N; i++) {
                a[i] = i + 1;
            }
            for (int i = 1; i < N; i++) {
                int j = randInt(0,i);
                if (i != j){
                    a[i] = a[i]^a[j];
                    a[j] = a[i]^a[j];
                    a[i] = a[i]^a[j];
                }

            }
            System.out.println("the runtime of test3() is " + (System.currentTimeMillis() - startTime) + "ms");
        }

        public static void main(String[] args){
            test1(250,new int[250]);            //31ms
            test1(500,new int[500]);            //31ms
            test1(1000,new int[1000]);          //140ms
            test1(2000,new int[2000]);          //884ms

//            test2(25000,new int[25000]);        //21762ms
//            test2(100000,new int[100000]);      //82748ms
//            test2(200000,new int[200000]);      //327013ms
//            test2(400000,new int[400000]);
//
//            test3(100000,new int[100000]);      //16ms
//            test3(200000,new int[200000]);      //16ms
//            test3(400000,new int[400000]);      //47ms
//            test3(800000,new int[800000]);      //142ms
        }

        static int randInt(int min, int max){
            int result;
            if (min > max){
                try {
                    throw new Exception("IllegalState");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Random random = new Random();
            result = random.nextInt(max - min + 1) + min;
            return result;
        }

}
