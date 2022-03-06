package com.itzjx.mmall_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * @author zhaojiexiong
 * @create 2020/6/29
 * @since 1.0.0
 */
public class GetBuildingOutline {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        int[][] matrix = new int[len][3];

        for(int i = 0; i < len; i++){
            String[] input = br.readLine().split(" ");
            for(int j = 0; j < input.length; j++){
                matrix[i][j] = Integer.parseInt(input[j]);
            }
        }
        System.out.print(getOutline(matrix));
    }

    public static class Node{
        public int x;  //x轴上的值
        public boolean isAdd;     //true为添加，false为删除
        public int h;  //高度
        public Node(int x, boolean isAdd, int h){
            this.x = x;
            this.isAdd = isAdd;
            this.h = h;
        }
    }

    private static String getOutline(int[][] m){
        if(m == null || m.length == 0 || m[0].length == 0) return "";
        Node[] nodes = new Node[m.length * 2];
        //每一个大楼轮廓数组产生两个描述高度变化的对象
        for(int i = 0; i < m.length; i++){
            nodes[i * 2] = new Node(m[i][0],true,m[i][2]);
            nodes[i * 2 + 1] = new Node(m[i][1],false,m[i][2]);
        }

        Arrays.sort(nodes, (o1, o2) -> {
            if(o1.x != o2.x){
                return o1.x - o2.x;
            }
            if(o1.isAdd != o2.isAdd){
                return o1.isAdd ? -1 : 1;
            }
            return 0;
        });

        //有序表
        TreeMap<Integer,Integer> mapHeightTimes = new TreeMap<>();
        TreeMap<Integer,Integer> mapXvalueHeight = new TreeMap<>();

        for(int i = 0; i < nodes.length; i++){
            if(nodes[i].isAdd){
                if(!mapHeightTimes.containsKey(nodes[i].h)){
                    //mapHeightTimes中第一次出现
                    mapHeightTimes.put(nodes[i].h,1);
                }else{
                    //mapHeightTimes中出现过
                    mapHeightTimes.put(nodes[i].h,mapHeightTimes.get(nodes[i].h) + 1);
                }
            }else{
                //！！！这里可以这样写而不用考虑为0的情况，因为之前在写Treeap的比较器时，使用了"添加在前，删除在后"
                //的原则，所以这里一定有值，同时为了避免特殊情况如[12,12,4]，如果删除在前，那么4这个记录最终无法删除
                //！！！mapHeightTimes中该高度出现次数仅为1
                if(mapHeightTimes.get(nodes[i].h) == 1){
                    mapHeightTimes.remove(nodes[i].h);
                }else{      //mapHeightTimes中该高度出现次数大于1
                    mapHeightTimes.put(nodes[i].h,mapHeightTimes.get(nodes[i].h) - 1);
                }
            }

            if (mapHeightTimes.isEmpty()){
                mapXvalueHeight.put(nodes[i].x,0);
            }else{
                mapXvalueHeight.put(nodes[i].x,mapHeightTimes.lastKey());
            }
        }

        //List<List<Integer>> res = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        int start = 0;
        int preHeight = 0;

        for(Map.Entry<Integer,Integer> entry : mapXvalueHeight.entrySet()){
            //当前位置
            int curX = entry.getKey();
            //当前最大高度
            int curMaxHeight = entry.getValue();
            //不一样时更新
            if(curMaxHeight != preHeight){
                //不为0时才加入结果集
                if (preHeight != 0){
                    //res.add(Arrays.asList(start,curX,preHeight));
                    sb.append(start + " " + curX + " " + preHeight + "\n");
                }
                start = curX;
                preHeight = curMaxHeight;
            }
        }
        return sb.toString();
    }
}
