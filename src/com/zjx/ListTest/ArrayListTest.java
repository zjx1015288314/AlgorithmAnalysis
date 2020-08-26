package com.zjx.ListTest;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ArrayListTest {
    public static void main(String[] args) {
        ArrayList<Integer> lists = new ArrayList<>();
        ArrayList<Integer> arrayList = new ArrayList<>();

        System.out.println(getArrayListCapacity(arrayList));

        //增加元素，使其扩容
        arrayList.add(0);
        System.out.println(getArrayListCapacity(arrayList));

        for (int i = 0; i < 10; ++i)
            arrayList.add(0);
        System.out.println(getArrayListCapacity(arrayList));

        for (int i = 0; i < 5; ++i)
            arrayList.add(0);
        System.out.println(getArrayListCapacity(arrayList));

        //输出结果一次为：0  10  15  22
    }

    public static int getArrayListCapacity(ArrayList<?> arrayList) {
        Class<ArrayList> arrayListClass = ArrayList.class;
        try {
            Field field = arrayListClass.getDeclaredField("elementData");
            field.setAccessible(true);
            Object[] objects = (Object[]) field.get(arrayList);
            return objects.length;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return -1;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
