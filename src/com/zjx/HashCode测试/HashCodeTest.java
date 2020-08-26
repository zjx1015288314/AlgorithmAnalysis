package com.zjx.HashCode测试;

import java.util.*;

/**
 * 文章链接：https://blog.csdn.net/bachbrahms/article/details/106983428
 * 不同对象的hashCode何时会相等？
 * 1）重写hashCode方法时
 * 一个类，如果你用某个属性去重写了hashCode方法，那么当不同对象的该属性相等时，
 * 它们的hashCode也一样。包括String类，也重写了hashCode方法（以及equals方法），
 * 当字符串内容相同时，hashCode相同。即使equals方法不相等，hashcode也可能相等
 * 因为int溢出的原因，hashcode会变成负数或者出现重复值
 * HashMap中hashcode采用了高低16位异或来防止hashcode为负导致无法放入数组(其实hash&(len - 1)也可以防止)
 * 2）未重写hashCode方法时
 * 首先，hashCode方法返回的并不是对象在jvm上的内存地址值本身，
 * 而是通过一定算法，转化而来的一个int值。
 * 这里就存在一个问题，内存上可能的地址值有多少个？
 * 经询问高手，得到的答案是，以64位系统为例，就是2的64次方个，
 * 而int的最大值是2的32次方，因此，理论上，int值不够分配给所有地址值，
 * 这就决定了会出现不同对象hashCode相等的情况.为了验证它，写了个小测试：
 * 结论：！！！！不重写hashCode方法，不同对象的hashCode也可能相同！！！！
 */
public class HashCodeTest {
    static Set<Integer> hashCodeSet = new HashSet<>();

    public static void main(String[] args) {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            Integer hashCode = new Object().hashCode();
            if(hashCode < 0) System.out.println("hashcode " + hashCode + "< 0");
            if (hashCodeSet.contains(hashCode)){
                System.out.println("出现重复hashCode值：" + hashCode + "，此时size为：" + hashCodeSet.size());
                break;
            }
            hashCodeSet.add(hashCode);
        }
    }
}
