package com.itzjx.mmall_test;



import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhaojiexiong
 * @create 2020/5/18
 * @since 1.0.0
 */
public class LeetCodeTest {
    public static int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) return 0;
        int lIdx= 0;
        int rIdx = 0;
        int maxLength = 1;
        char[] c = s.toCharArray();
        Map<Character,Integer> map = new HashMap<>();
        while(rIdx < c.length){
            if(map.get(c[rIdx]) == null){
                map.put(c[rIdx],rIdx);
            }else if(map.get(c[rIdx]) >= lIdx){
                lIdx = map.get(c[rIdx]) + 1;
                map.put(c[rIdx],rIdx);
            }else{
                map.put(c[rIdx],rIdx);
            }
            rIdx++;
            if(rIdx - lIdx > maxLength){
                maxLength = rIdx - lIdx;
            }
        }
        return maxLength;
    }

    //two threads printf 0-100 alternately
//    private static Object lock = new Object();
//    private static int i = 0;
//    private static int wait = 1;
//    private static final int TOTAL = 100;
//
//    public static void main(String[] args) {
//
//        Thread thread1 = new Thread(){
//            @Override
//            public void run() {
//                while(i <= TOTAL){
//                    synchronized (lock){
//                        if (i % 2 == 1){
//                            System.out.println("thread1 " + i++);
//                        }else{
//                            lock.notifyAll();
//                            try {
//                                lock.wait();
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }
//
//            }
//        };
//        Thread thread2 = new Thread(){
//            @Override
//            public void run() {
//                while(i <= TOTAL){
//                    synchronized (lock){
//                        if (i % 2 == 0){
//                            System.out.println("thread2 " + i++);
//                        }else{
//                            lock.notifyAll();
//                            try {
//                                lock.wait();
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }
//
//            }
//        };
//        thread1.setName("mythread1");
//        thread1.setName("mythread2");
//        thread1.start();
//        thread2.start();
//    }

    private static AtomicInteger num = new AtomicInteger();
    private static final int TOTAL = 100;
    private static CountDownLatch latch = new CountDownLatch(2);
    private static volatile boolean flag = true;

    public static void main(String[] args) {
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                while(num.get() < TOTAL){
                    if (!flag){
                        System.out.println("thread1 " + num.getAndIncrement());
                        flag = true;
                    }
                    latch.countDown();
                }
            }
        };
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                while(num.get() <= TOTAL){
                    if (flag){
                        System.out.println("thread2 " + num.getAndIncrement());
                        flag = false;
                    }
                }
                latch.countDown();
            }
        };
        thread1.start();
        thread2.start();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public int minNumberInRotateArray(int [] array) {
        if(array == null || array.length == 0){
            return 0;
        }
        int l = 0,h = array.length - 1;
        while(l < h){
            int m = l + (h - l) / 2;
            if (array[m] == array[h]){
                return minNumber(array,l,h);
            }else if(array[m] < array[h]){
                h = m;
            }else{
                l = m + 1;
            }
        }
        System.out.println(array[l]);
        return array[l];

    }

    private int minNumber(int[] nums, int l, int h) {
        for (int i = l; i < h; i++)
            if (nums[i] > nums[i + 1]){
                System.out.println("i+1 = " + nums[i+1]);
                return nums[i + 1];
            }
        System.out.println("l = " + nums[l]);
        return nums[l];
    }

    public void TestminNumberInRotateArray() {
        int[] a = {1,0,1,1,1};
        int[] a1 = {1,1,1,1,0,1};
        minNumberInRotateArray(a);
        minNumberInRotateArray(a1);
    }

    public void getNearLessNoRepeat(String[] args){
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int[] arr = new int[len];
        for(int i = 0; i < len; i++){
            arr[i] = in.nextInt();
        }

        int[][] res = new int[len][2];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < len; i++){
            while(!stack.isEmpty() && arr[stack.peek()] > arr[i]){
                int index = stack.pop();
                int leftIndex = stack.isEmpty()? -1 : stack.peek();
                res[index][0] = leftIndex;
                res[index][1] = i;
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int index = stack.pop();
            int leftIndex = stack.isEmpty()? -1 : stack.peek();
            res[index][0] = leftIndex;
            res[index][1] = -1;
        }
        for(int i = 0; i < len; i++){
            System.out.printf("%d %d\n",res[i][0],res[i][1]);
        }
    }

    public void maxRecSize(){
        Scanner in = new Scanner(System.in);
        int row = in.nextInt();
        int col = in.nextInt();

        int[][] arr = new int[row][col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                arr[i][j] = in.nextInt();
            }
        }

        if(arr == null || arr.length == 0 || arr[0].length == 0){
            return;
        }
        int maxArea = 0;
        int[] height = new int[arr[0].length];
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                height[j] = arr[i][j] == 0 ? 0 : height[j] + 1;
            }
            maxArea = Math.max(maxRecFromBottom(height),maxArea);
        }
        System.out.println(maxArea);
    }

    public int maxRecFromBottom(int[] height){
        if(height == null || height.length == 0){
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < height.length; i++){
            while(!stack.isEmpty() && height[stack.peek()] >= height[i]){
                int j = stack.pop();
                int k = stack.isEmpty()? -1 : stack.peek();
                int curArea = (i - k - 1) * height[j];
                maxArea = Math.max(maxArea,curArea);
            }
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int j = stack.pop();
            int k = stack.isEmpty()? -1 : stack.peek();
            int curArea = (height.length - k -1) * height[j];
            maxArea = Math.max(maxArea,curArea);
        }
        return maxArea;
    }

}
