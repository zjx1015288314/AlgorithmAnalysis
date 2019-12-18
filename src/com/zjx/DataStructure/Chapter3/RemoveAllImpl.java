package com.zjx.DataStructure.Chapter3;

import com.zjx.ListTest.MyArrayList;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

/**
 * 3.10 removeAll在ArrayList中的实现是通过boolean batchRemove(Collection<?> c, boolean complement,
 * final int from, final int end)实现的,且removeAll和retainAll公用batchRemove(当complement为true时实现retainAll,
 * 相反为false时实现retainAll)
 */

public class RemoveAllImpl<E>{
    //O(M×N)
    public void addAll(Iterable<? extends E> items) {
        MyArrayList myArrayList = new MyArrayList<>();
        Iterator<? extends E> iterator = items.iterator();
        E item,element;
        while (iterator.hasNext()) {
            item = iterator.next();
            Iterator<? extends E> iterList = myArrayList.iterator();
            while(iterList.hasNext()){
                element = iterList.next();
                if (element.equals(item))
                    iterList.remove();
            }
        }
    }
//    boolean batchRemove(Collection<?> c, boolean complement,
//                        final int from, final int end) {
//        Objects.requireNonNull(c);
//        final Object[] es = elementData;  //ArrayList中大部分都要用final Object[]获取原数组引用
//        int r;
//        // Optimize for initial run of survivors
//        for (r = from;; r++) {
//            //有两个方法从for循环中退出：要么到达end，要么符合中断中断：remove要求一旦遇到c中包含的item就break
//            //retain要求一旦遇到c中不包含的item就中断
//            if (r == end)
//                return false;
//            if (c.contains(es[r]) != complement)
//                break;
//        }
//        //w为中断位置
//        int w = r++;
//        //for循环找到所有与第一次for循环中if判断相反的元素 移位赋值直到结束
//        try {
//            for (Object e; r < end; r++)
//                if (c.contains(e = es[r]) == complement)
//                    es[w++] = e;
//        } catch (Throwable ex) {
//            //即使c.contains()抛出异常，仍保留与AbstractCollection的行为兼容性
//            System.arraycopy(es, r, es, w, end - r);   //遇到异常直接将剩下的元素复制,不考虑其他问题
//            w += end - r;
//            throw ex;
//        } finally {
//            modCount += end - w;
//            shiftTailOverGap(es, w, end);    //将没用的尾部去除
//        }
//        return true;
//    }

}
