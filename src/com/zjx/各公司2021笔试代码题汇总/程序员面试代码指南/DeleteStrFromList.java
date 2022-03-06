package com.itzjx.mmall_test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 输入List<String>，删除当中形如”1_”的，返回原来的List
 * @author zhaojiexiong
 * @create 2020/7/21
 * @since 1.0.0
 */
public class DeleteStrFromList {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("1_1111111");
        list.add(("2_11111"));
        list.add("_bdada");
        list.add("1234567");
        List<String> ret = new LinkedList<>(list);  //返回原来的List
//        List<String> res = delete(list,"1_");  //返回删除后的List
        List<String> res = delete1(list);  //返回删除后的List
        for(String str : res){
            System.out.println(str);
        }
    }

    public static List<String> delete(List<String> list,String str){
        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            String tmp = it.next();
            if(tmp.startsWith(str)){
                it.remove();
            }
        }
        return list;
    }
    //自己写判断
    public static List<String> delete1(List<String> list){
        Iterator<String> it = list.iterator();
        while (it.hasNext()){
            String tmp = it.next();
            boolean flag = false;
            if(tmp.length() >= 2 && tmp.charAt(0) >= '0'
                    && tmp.charAt(0) <= '9' && tmp.charAt(1) == '_'){
                flag = true;
            }
            if(flag){
                it.remove();
            }
        }
        return list;
    }
}
