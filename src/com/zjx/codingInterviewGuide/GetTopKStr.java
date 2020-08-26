package com.zjx.codingInterviewGuide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class GetTopKStr {
    public static class Node{
        public String str;
        public int times;
        public Node(String s, int t){
            str = s;
            times = t;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] ss = br.readLine().split(" ");
        int N = Integer.parseInt(ss[0]);
        int K = Integer.parseInt(ss[1]);
        ss = new String[N];
        for (int i = 0; i < N; i++) {
            ss[i] = br.readLine();
        }
        getTopKStr(ss,K);
        getTopKStr2(ss,K);
    }

    public static void getTopKStr(String[] str, int K){
        if(str == null || str.length == 0 || K < 1){
            System.out.print("");
            return;
        }
        HashMap<String,Integer> freMap = new HashMap<>();
        for(String s : str){
            if(!freMap.containsKey(s)){
                freMap.put(s,1);
            }else{
                freMap.put(s,freMap.get(s) + 1);
            }
        }
        //这里处理的原因是小顶堆输出的是最小次数的字符串，原题需要的是出现次数由大到小输出，
        //若出现次数相同时字符串字典序较小的优先输出，为了逆序输出，我们在排序的时候使用
        //o2.str.compareTo(o1.str)保证次数相等的时候，字典序较大的排在前面
        PriorityQueue<Node> minHeap = new PriorityQueue<>((o1,o2) -> {
            if(o1.times != o2.times){
                return o1.times - o2.times;
            }
            return o2.str.compareTo(o1.str);
        });
        for(Map.Entry<String,Integer> entry : freMap.entrySet()){
            String s = entry.getKey();
            int times = entry.getValue();
            Node node = new Node(s,times);
            if(minHeap.size() < K){
                minHeap.add(node);
            }else{
                Node nd = minHeap.peek();
                //!!!!这里如果没有第二项判断的话则输出失败
                //在次数相等时，如果node代表的字符串顺序小于堆顶，则也需要入堆
                if(nd.times < node.times ||
                        (node.times == nd.times && node.str.compareTo(nd.str) < 0)){
                    minHeap.poll();
                    minHeap.add(node);
                }
            }
        }
        Node[] res = new Node[minHeap.size()];
        int i = res.length - 1;
        while(!minHeap.isEmpty()){
            res[i--] = minHeap.poll();
        }
        for(Node cur : res){
            System.out.println(cur.str + " " + cur.times);
        }
    }

    //**************************方法二****************************
    /**
     * 根据传入的K自建小根堆,为了便于Node节点的比较，新建MyNode类用于比较
     */
    public static class MyNode implements Comparable<MyNode>{
        public String str;
        public int times;
        public MyNode(String s, int t){
            str = s;
            times = t;
        }
        @Override
        public int compareTo(MyNode o){
            if(this.times != o.times){
                return this.times - o.times;
            }
            return o.str.compareTo(this.str);
        }
    }

    //**************方法二**************
    //为了便于Node节点的比较，新建MyNode类实现Comparable用于比较,
    //这样在heapInsert,heapify,以及getTopKStr2中可以轻松决定大小
    public static void getTopKStr2(String[] str, int K){
        if(str == null || str.length == 0 || K < 1){
            System.out.print("");
            return;
        }
        HashMap<String,Integer> freMap = new HashMap<>();
        for(String s : str){
            if(!freMap.containsKey(s)){
                freMap.put(s,1);
            }else{
                freMap.put(s,freMap.get(s) + 1);
            }
        }
        //使用数组自建小根堆
        MyNode[] minHeap = new MyNode[K];
        int index = 0;
        for(Map.Entry<String,Integer> entry : freMap.entrySet()){
            String s = entry.getKey();
            int times = entry.getValue();
            MyNode node = new MyNode(s,times);
            if(index < K){  //or index < K
                minHeap[index] = node;
                //上滤
                heapInsert(minHeap,index++);
            }else{
                if(minHeap[0].compareTo(node) < 0){
                    minHeap[0] = node;
                    //下滤
                    heapify(minHeap,0,K);
                }
            }
        }
        //把小根堆的所有元素按词频从大到小排序
        for(int i = index - 1; i > -1; i--){
            swap(minHeap,0,i);
            heapify(minHeap,0,i);
        }

        //按照排名打印K条记录
        for(int i = 0; i < minHeap.length; i++){
            if(minHeap[i] == null){ //K比数组大
                break;
            }else{
                System.out.println(minHeap[i].str + " " + minHeap[i].times);
            }
        }
    }

    private static void heapInsert(MyNode[] heap, int index){
        while(index != 0){
            int parent = (index - 1) / 2;
            if(heap[index].compareTo(heap[parent]) < 0){
                swap(heap,index,parent);
                index = parent;
            }else{
                break;
            }
        }
    }

    private static void heapify(MyNode[] heap, int index, int size){
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int smallest = index;
        while(left < size){
            if(heap[left].compareTo(heap[index]) < 0){
                smallest = left;
            }
            if(right < size && heap[right].compareTo(heap[smallest]) < 0){
                smallest = right;
            }
            if(smallest != index){
                swap(heap,index,smallest);
                index = smallest;
                left = index * 2 + 1;
                right = index * 2 + 2;
            }else{
                break;
            }
        }
    }

    private static void swap(MyNode[] arr, int i, int j){
        MyNode tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
