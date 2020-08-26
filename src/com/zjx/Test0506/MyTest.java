package com.zjx.Test0506;

import java.util.*;

public class MyTest {

    public static void main(String[] args) {
        int arr[];
        int n = 0;
        int m = 0;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        arr = new int[n+1];
//        for (int i = 0; i <= m; i++) {
//            arr[i] = in.nextInt();
//        }
        System.out.println(Arrays.toString(arr));

        LinkedHashMap<Integer,Integer> map = new LinkedHashMap<>();
        List<Integer> list = new LinkedList<>();
        for(int i = 0; i < m; i++) {
            int x = in.nextInt();
            if(x == 1){
                arr[in.nextInt()] = -1;
            }else if(x == 2){
                int index = in.nextInt();
                if (arr[index] == 0){
                    System.out.println(index);
                }else if (index < n && arr[index + 1] == 0){
                    System.out.println(index+1);
                }else{
                    System.out.println(-1);
                }
//
//                list.add(in.nextInt());
//                map.put(in.nextInt(),1);
            }
        }


//        for (int i = 0; i < n; i++) {
//            for (int j = i + 1; j < n; j++) {
//                int f = arr[i];
//                int s = arr[j];
//                int tmp = test(f,s);
//                if(tmp > max){
//                    max = tmp;
//                }
//            }
//        }
//        System.out.println(max);
//        int[] result = new int[n];
//        Arrays.fill(result,-1);
//        System.out.println(Arrays.toString(result));
//        for (int i = 0; i < n; i++) {
//            if(result[i] == 1){
//                continue;
//            }
//            for (int j = i+1; j < n; j++) {
//                if((arr[i] & arr[j]) == 0){
//                    result[i] = 1;
//                    result[j] = 1;
//                }
//            }
//        }
//
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(result[i]);
//        }
//        for (int i = 0; i < n; i++) {
//            List<Integer> l = new ArrayList<>();
//            for (int j = 0; j < m; j++) {
//                int k = in.nextInt();
//                l.add(k);
//                arr[i][j] = k;
//            }
//            myList.add(l);
//        }

//        int result = 0;
//        boolean[] b = new boolean[n];
//        Map<Integer,Integer> map = new HashMap<>();
//        for (int j = 0; j < m; j++) {
//            int max = 0;
//            int index = 0;
//            for (int i = 0; i < n; i++) {
//                if(max < arr[i][j]){
//                    max = arr[i][j];
//                    index = i;
//                }
//            }
//            if (!b[index]){
//                result++;
//            }
//            System.out.println(result);
//
//            for (int i = 0; i < b.length; i++) {
//                if
//            }
//            if (!map.containsKey(index)){
//                map.put(index,max);
//            }
//        }
//        System.out.println(map.size());
    }

    public static int test(int m,int n) {
        int result = m ^ n;
        System.out.println(result);
        int num = 0;
        for (int i = 0; i < 16; i++) {
            if (result % 2 == 1){
                num++;
            }
            result = result >>> 1;
        }
        return num;



    }




}
