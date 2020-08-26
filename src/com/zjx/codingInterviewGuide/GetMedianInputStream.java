package com.zjx.codingInterviewGuide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class GetMedianInputStream {
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

        public double getMedian(){
            if(maxHeap.isEmpty()) return -1;
            if(maxHeap.size() == minHeap.size()){
                return (maxHeap.peek() + minHeap.peek()) / 2.0; //这里注意2.0而不是2
            }else{
                return maxHeap.size() > minHeap.size() ? maxHeap.peek() : minHeap.peek();
            }
        }
    }
}
