package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.为难人系列;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后
 * 位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平
 * 均值。我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
 *
 * @link https://www.nowcoder.com/practice/9be0172896bd43948f8a32fb954e1be1?tpId=13&tags=&title=&difficulty=0&judgeStatus=0&rp=0
 */
public class 大小堆随时获得输入流中的中位数 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] ss = null;
        MedianHoler mh = new MedianHoler();
        for(int i = 0; i < N; i++){
            ss = br.readLine().split(" ");
            if(ss[0].equals("1")){
                mh.addNum(Integer.valueOf(ss[1]));
            }else{
                double res = mh.getMedian();
                System.out.print(res == -1 ? -1 : String.format("%.1f",res));
            }
        }
    }


    public static class MedianHoler{
        private PriorityQueue<Integer> minHeap = null;
        private PriorityQueue<Integer> maxHeap = null;
        public MedianHoler(){
            minHeap = new PriorityQueue<>((o1,o2) -> o1 - o2);
            maxHeap = new PriorityQueue<>((o1,o2) -> o2 - o1);
        }

        public void addNum(Integer num){
            if(maxHeap.isEmpty() || maxHeap.peek() >= num){
                maxHeap.add(num);
            }else{
                minHeap.add(num);
            }
            int minSize = minHeap.size();
            int maxSize = maxHeap.size();
            if(minSize - maxSize > 1){
                maxHeap.add(minHeap.poll());
            }else if(maxSize - minSize > 1){
                minHeap.add(maxHeap.poll());
            }
        }

        public Double getMedian(){
            if(maxHeap.isEmpty()) return 0.0;
            if(maxHeap.size() == minHeap.size()){
                return (maxHeap.peek() + minHeap.peek()) / 2.0; //这里注意2.0而不是2
            }else{
                int value =  maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
                return Double.valueOf(value);
            }
        }
    }
}
