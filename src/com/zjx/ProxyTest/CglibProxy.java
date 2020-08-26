package com.zjx.ProxyTest;

import java.util.Iterator;
import java.util.function.Consumer;

public class CglibProxy implements Iterable, Cloneable {

    private int[] items;

    public CglibProxy() {
        items = new int[10];
        for (int i = 0; i < items.length; i++) {
            items[i] = i;
        }
    }

    @Override
    public Iterator iterator() {
        return new MyIterator(items);
    }

    public class MyIterator implements Iterator {

        private int[] items;
        private int position = 0;

        public MyIterator(int[] items) {
            this.items = items;
        }

        @Override
        public Object next() {
            return items[position++];
        }

        @Override
        public boolean hasNext() {
            return position < items.length;
        }
    }

    public static void main(String[] args) {
        Iterator iterator = new CglibProxy().iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        String test = new String("111");
        return super.clone();
    }
}
