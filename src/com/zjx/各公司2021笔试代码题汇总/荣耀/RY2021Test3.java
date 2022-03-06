package com.zjx.各公司2021笔试代码题汇总.荣耀;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class RY2021Test3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(";");
        String s1 = str[0];
        String s2 = str[1];
        String res = str[2];

        Map<String,Integer> map = new HashMap<>();
        String[] s22 = s2.split(",");
        for (String s : s22) {
            String[] tmp = s.split("=");
            map.put(tmp[0],Integer.parseInt(tmp[1]));
        }

        String[] s12 = s1.split(",");
        for (int i = s12.length - 1; i >= 0; i++) {

        }
    }
}
