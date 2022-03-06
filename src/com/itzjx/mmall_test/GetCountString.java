package com.itzjx.mmall_test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author zhaojiexiong
 * @create 2020/6/20
 * @since 1.0.0
 */
public class GetCountString {
    public static void main(String[] args){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try{
            String ss = br.readLine();
//            System.out.print(getCountString(ss));
            System.out.println(getCharAt(ss,50));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static String getCountString(String str){
        if(str == null || str.equals("")) return "";
        char[] chs = str.toCharArray();
        StringBuffer sb = new StringBuffer();
        sb.append(chs[0]);
        int num = 1;
        for(int i = 1; i < chs.length; i++){
            if(chs[i] != chs[i - 1]){
                sb.append("_" + num + "_" + chs[i]);
                num = 1;
            }else{
                num++;
            }
        }
        sb.append("_" + num);
        return sb.toString();
    }

    private static char getCharAt(String str,int index){
        String[] ss = str.split("_");
        for (int i = 1; i < ss.length; i += 2){
            index -= Integer.parseInt(ss[i]);
            if (index < 0){
                return ss[i - 1].charAt(0);
            }
        }
        return ' ';
    }
}
