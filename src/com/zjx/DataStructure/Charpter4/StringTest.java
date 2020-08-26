package com.zjx.DataStructure.Charpter4;

import java.util.Stack;
import java.util.TreeMap;

public class StringTest {
    public static void main(String[] args) {

        //String有两种排序,一种字典序(从低到高为：0-9AaBb-Zz,
        //不同长度时先按较小的长度比较,如果都相同则长的更大,如果不一样就不看长度)
        //排序结果为：{00=1, 11=1, AAA=1, aaa=1, aaaaa=1, abC=1, abc=1, bbb=1}

        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("zjx", null);  //允许空值
//        treeMap.put(null,"1"); //可能会抛异常也可能不会(取决于你有没有自己实现Comparator,
        // 没有实现的话就要抛空指针异常,因为不能在null对象上使用compareTo方法)
        treeMap.put("aaa", "1");
        treeMap.put("bbb", "1");
        treeMap.put("abc", "1");
        treeMap.put("AAA", "1");
        treeMap.put("abC", "1");
        treeMap.put("aaaaa", "1");
        treeMap.put("00", "1");
        treeMap.put("11", "1");
        System.out.println(treeMap);


    }
}
