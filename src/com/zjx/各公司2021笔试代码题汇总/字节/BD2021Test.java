package com.zjx.各公司2021笔试代码题汇总.字节;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BD2021Test {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        List<String> res = process(str);
        for(String s : res){
            System.out.print(s + " ");
        }
    }

    public static List<String> process(String str){
        String[] s = str.split(" ");
        List<String> list = new ArrayList<>();
        StringBuffer sb = new StringBuffer();
        String tmp = null;
        for (int i = 0; i < s.length; i++) {
            if (!s[i].equals("redo") && !s[i].equals("undo")){
                //tmp = s[i];
                list.add(s[i]);
            }else if (s[i].equals("undo")){
                tmp = list.remove(list.size() - 1);
            }else if (s[i].equals("redo")){
                if (tmp != null) list.add(tmp);
                tmp = null;
            }
        }
        return list;
    }
}
