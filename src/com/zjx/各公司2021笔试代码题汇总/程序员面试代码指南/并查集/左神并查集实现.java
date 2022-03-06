package com.zjx.各公司2021笔试代码题汇总.程序员面试代码指南.并查集;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class 左神并查集实现 {

    public class UnionFindSet<V>{

        class Element<V>{
            V value;
            Element(V value){
                this.value = value;
            }
        }

        public HashMap<V,Element<V>> elementMap;
        public HashMap<Element<V>,Element<V>> fatherMap;
        public HashMap<Element<V>,Integer> rankMap;

        public UnionFindSet(List<V> list){
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            rankMap = new HashMap<>();
            for(V value : list){
                Element<V> element = new Element<>(value);
                elementMap.put(value,element);
                fatherMap.put(element,element);
                rankMap.put(element,1);
            }
        }

        //获取头部并且压缩路径
        private Element<V> findHead(Element<V> element){
            Stack<Element<V>> path = new Stack<>();
            while(element != fatherMap.get(element)){
                path.push(element);
                element = fatherMap.get(element);
            }
            while (!path.isEmpty()){
                fatherMap.put(path.pop(),element);
            }
            return element;
        }

        public boolean isSameSet(V a, V b){
            if(elementMap.containsKey(a) && elementMap.containsKey(b)){
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
            }
            return false;
        }

        public void union(V a,V b){
            if (elementMap.containsKey(a) && elementMap.containsKey(b)){
                Element<V> aF = findHead(elementMap.get(a));
                Element<V> bF = findHead(elementMap.get(b));
                if (aF != bF){
                    Element<V> big = rankMap.get(aF) >= rankMap.get(bF) ? aF : bF;
                    Element<V> small = big == aF ? bF : aF;
                    fatherMap.put(small,big); //这里
                    rankMap.put(big,rankMap.get(aF) + rankMap.get(bF));
                    rankMap.remove(small);
                }
            }
        }
    }
}
