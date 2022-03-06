package com.zjx.各公司2021笔试代码题汇总.华为.面试;
import java.util.HashMap;
import java.util.Map;
public class HW202109151 {
    public static void main(String[] args) {
        System.out.println(process("abcijkmos","wwuuzzccbakijomssvvvv"));
    }
    public static int process(String s1, String s2){
        if(s1 == null || s1.isEmpty()) return -1;
        int len1 = s1.length();
        int len2 = s2.length();
        for (int i = 0; i < len2; i++) {
            if (check(s2,s1,i,len1)) return i;
        }
        return -1;
    }
    public static boolean check(String s2,String s1,int index,int len1){
        int len2 = s2.length();
        if (index + len1 > len2) return false;
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            if(!map.containsKey(s1.charAt(i))){
                map.put(s1.charAt(i),1);
            }else{
                map.put(s1.charAt(i),map.get(s1.charAt(i)) + 1);
            }
        }
        for (int i = index; i < index + len1; i++) {
            if (!map.containsKey(s2.charAt(i))){
                return false;
            }else{
                if (map.get(s2.charAt(i)) == 1){
                    map.remove(s2.charAt(i));
                }else{
                    map.put(s2.charAt(i),map.get(s2.charAt(i)) - 1);
                }
            }
        }
        return true;
    }
}
