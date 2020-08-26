package com.zjx.DataStructure.Chapter2;

import java.util.Comparator;

/**
 * 矩形最大面积和最小面积
 */
public class RectangleTest {
    private int width;
    private int length;

    public RectangleTest(int width, int length) {
        this.width = width;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getArea() {
        return length * width;
    }

    public String toString() {
        return "(" + width + "," + length + ")";
    }

    public static RectangleTest findMax(RectangleTest[] rectangleTests, Comparator<? super RectangleTest> cmp) {
        if (rectangleTests == null && cmp == null)
            return null;
        int index = 0;
        for (int i = 1; i < rectangleTests.length; i++)
            if (cmp.compare(rectangleTests[i], rectangleTests[index]) > 0)
                index = i;
        return rectangleTests[index];
    }

    public static RectangleTest findMin(RectangleTest[] rectangleTests, Comparator<? super RectangleTest> cmp) {
        if (rectangleTests == null && cmp == null)
            return null;
        int index = 0;
        for (int i = 1; i < rectangleTests.length; i++)
            if (cmp.compare(rectangleTests[i], rectangleTests[index]) < 0)
                index = i;
        return rectangleTests[index];
    }

    //面积比较器
    public static class AreaCompare implements Comparator<RectangleTest> {

        @Override
        public int compare(RectangleTest o1, RectangleTest o2) {
            if (o1.getArea() > o2.getArea())
                return 1;
            else if (o1.getArea() == o2.getArea())
                return 0;
            else
                return -1;

        }
    }

    public static void main(String[] args) {
        RectangleTest[] arr = new RectangleTest[]{
                new RectangleTest(5, 5), new RectangleTest(6, 4), new RectangleTest(3, 8)
        };
        System.out.println("The MaxArea is " + findMax(arr, new AreaCompare()));
        System.out.println("The MinArea is " + findMin(arr, new AreaCompare()));
    }

}
