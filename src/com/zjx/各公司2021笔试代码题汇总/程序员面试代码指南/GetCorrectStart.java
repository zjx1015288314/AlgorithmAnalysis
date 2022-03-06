package com.itzjx.mmall_test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author zhaojiexiong
 * @create 2020/6/29
 * @since 1.0.0
 */
public class GetCorrectStart {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] input = br.readLine().split(" ");
        String[] input1 = br.readLine().split(" ");
        int[] oil = new int[N];
        int[] dis = new int[N];
        for(int i = 0; i < N; i++){
            oil[i] = Integer.parseInt(input[i]);
            dis[i] = Integer.parseInt(input1[i]);
        }
        boolean[] res = isCorrectStart(oil,dis);
        for(boolean flag : res){
            System.out.print((flag ? 1 : 0) + " ");
        }
    }

    private static boolean[] isCorrectStart(int[] oil, int[] dis){
        if(oil == null || dis == null || dis.length < 2 || dis.length != oil.length) return null;
        int init = changeDisArrayGetInit(oil,dis);
        return init == -1 ? new boolean[dis.length] : enlargeArea(dis,init);
    }

    //将dis数组当作纯能值数组
    private static int changeDisArrayGetInit(int[] oil, int[] dis){
        int init = -1;
        for(int i = 0; i < dis.length; i++){
            dis[i] = oil[i] - dis[i];
            if(dis[i] >= 0){
                init = i;
            }
        }
        return init;
    }

    private static boolean[] enlargeArea(int[] dis,int init){
        boolean[] res = new boolean[dis.length];
        int start = init;
        int end = nextIndex(init, dis.length);
        int need = 0;
        int rest = 0;
        do{
            if(start != init && start == lastIndex(end, dis.length)){
                break;
            }
            //当前start不在连通域，可以扩展连通区域
            if(dis[start] < need){
                need -= dis[start];
            }else{
                rest += dis[start] - need;
                need = 0;  //清零，看下一个start能否满足
                while(rest >= 0 && end != start){
                    rest += dis[end];
                    end = nextIndex(end,dis.length);
                }
                //如果连通区已经覆盖整个环，当前的start是良好出发点
                if(rest >= 0){ //上面的循环终止不是因为rest,即已全部覆盖
                    res[start] = true;
                    connectGood(dis,lastIndex(start, dis.length), init, res);
                    break;
                }
            }
            start = lastIndex(start,dis.length);
        }while(start != init);
        return res;
    }
    //已知start的next方向有一个良好出发点，start如果可以达到这个出发点，那么start一定可以绕一圈
    private static void connectGood(int[] dis, int start, int init, boolean[] res){
        int need = 0;
        while(start != init){
            if(dis[start] < need){
                need -= dis[start];
            }else{
                res[start] = true;
                need = 0;
            }
            start = lastIndex(start, dis.length);
        }
    }

    private static int lastIndex(int index, int size){
        return index == 0 ? (size - 1) : index - 1;  //逆方向
    }

    private static int nextIndex(int index, int size){
        return index == size - 1 ? 0 : index + 1;  //逆方向
    }
}
