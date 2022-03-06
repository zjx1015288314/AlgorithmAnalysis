package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南;

import java.util.LinkedList;

/**
 * @author zhaojiexiong
 * @create 2020/6/7
 * @since 1.0.0
 */
public class SubArraynum {

        LinkedList<Integer> qmax ;//基于LinkedList实现的双端队列。用于维护最大值
        LinkedList<Integer> qmin;//用于维护当前数组的最小值

        SubArraynum(){

            qmax = new LinkedList<Integer>();
            qmin = new LinkedList<Integer>();
        }

        public int getMax(  int []array,   int num ) {

            int l =  0 ,r = 0;
            int ans = 0;

            while(l < array.length ) {
                while( r < array.length) {
                    //paichu <qmin.peekLast() != r> qingkuang ,fangzhi xiangtongzhi chongfujinlai
                    if(qmin.isEmpty() || qmin.peekLast() != r){
                        while( !qmax.isEmpty() && array[r] >= array[qmax.peekLast()]) {
                            qmax.pollLast();
                        }
                        qmax.addLast(r);
                        while( !qmin.isEmpty() && array[r] <= array[qmin.peekLast()]) {
                            qmin.pollLast();
                        }
                        qmin.addLast(r);
                    }
                    if( array[qmax.peekFirst()] - array[qmin.peekFirst()] > num )
                        break;
                    r++;
                }
                ans += r-l;
                if(qmax.peekFirst() == l)
                    qmax.pollFirst();
                if(qmin.peekFirst() == l )
                    qmin.pollFirst();
                l++;
            }
            return ans;
        }

        public static void main(String[] args) {
            // TODO Auto-generated method stub
            int array[] = {3,2,5,1,4,7,8,6};

            SubArraynum myQueue  = new SubArraynum();
            System.out.println( myQueue.getMax(array, 2));
            Math.ceil(1.5);
        }
}
