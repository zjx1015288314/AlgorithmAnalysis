package com.zjx.DataStructure;

import java.util.Comparator;

public class Employee extends Person {

    public static <T> T findMax(T[] arr, Comparator<? super T> cmp){
        int maxIndex = 0;

        for (int i = 1; i < arr.length; i++)
            if (cmp.compare(arr[i],arr[maxIndex]) > 0)
                maxIndex = i;

        return arr[maxIndex];

    }

    public static void main(String[] args){
        String arr[] = {"ZZERBA","alligator","crocodile"};
        System.out.println(findMax(arr,new CaseInsenstiveCompare()));
    }



}
class CaseInsenstiveCompare implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
        return o1.compareToIgnoreCase(o2);
    }

//    @Override
//    public int compare(Integer o1, Integer o2) {
//        return o1.compareTo(o2);
//    }

}