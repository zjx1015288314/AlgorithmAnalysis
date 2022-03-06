package com.itzjx.mmall_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhaojiexiong
 * @create 2020/6/28
 * @since 1.0.0
 */
public class LeftUnique {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int N = Integer.parseInt(input[0]);
        int[] arr = new int[N];
        input = br.readLine().split(" ");
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(input[i]);
        }
//        leftUnique(arr);
        sort(arr);
    }

    private static void leftUnique(int[] arr){
        if(arr == null || arr.length == 0) return;
        int left = 0;
        for(int i = 1; i < arr.length; i++){
            if(arr[i] == arr[left] + 1){
                int tmp = arr[++left];
                arr[left] = arr[i];
                arr[i] = tmp;
            }
        }
        for(int i : arr){
            System.out.print(i + " ");
        }
    }

    private static void sort(int[] arr){
        if(arr == null || arr.length == 0) return;
        int left = -1;
        int right = arr.length;
        int i = 0;
        while(i < right){
            if(arr[i] == 0){
                int tmp = arr[++left];
                arr[left] = arr[i];
                arr[i++] = tmp;
            }else if(arr[i] == 2){
                int tmp = arr[--right];
                arr[right] = arr[i];
                arr[i] = tmp;
            }else{
                i++;
            }
        }
        for (int item : arr){
            System.out.print(item + " ");
        }

    }
}
