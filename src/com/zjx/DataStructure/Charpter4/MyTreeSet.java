package com.zjx.DataStructure.Charpter4;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * p112 4.11
 * 编写TreeSet类的实现程序，其中相关的迭代器使用二叉查找树。
 * 在每个节点上添加一个指向其父节点的链
 * @param <T>
 */
public class MyTreeSet<T extends Comparable<? super T>> {

    private BinaryNode<T> root; //根节点
    int modCount = 0;  //记录调整树结构的次数

    public MyTreeSet(){
        root = null;
    }
    /**
     * 定义二叉查找树的节点
     */
    private class BinaryNode<T>{
        T data; //节点的值
        BinaryNode<T> left; //节点的左节点
        BinaryNode<T> right; //节点右节点
        BinaryNode<T> parent; //节点的父节点

        public BinaryNode(T data){
            this(data, null, null, null);
        }

        public BinaryNode(T data, BinaryNode<T> lt, BinaryNode<T> rt, BinaryNode<T> pt) {
            this.data = data;
            this.left = lt;
            this.right = rt;
            this.parent = pt;
        }
    }

    /**
     * 定义TreeSet的迭代器
     * @return
     */
    public Iterator iterator(){
        return new MyTreeSetIterator();
    }
    private class MyTreeSetIterator implements Iterator{
        private BinaryNode<T> current = findMin(root);
        private BinaryNode<T> previous;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;
        private boolean atEnd;

        @Override
        public boolean hasNext() {
            return !atEnd;
        }

        @Override
        public T next() {
            if (expectedModCount != modCount){
                try {
                    throw new ConcurrentModificationException();
                }catch (ConcurrentModificationException e){
                    e.printStackTrace();
                }
            }
            if (!hasNext()){
                try{
                    throw new NoSuchElementException();
                }catch (NoSuchElementException e){
                    e.printStackTrace();
                }
            }
            T nextItem = current.data;
            previous = current;
            if (current.right != null){
                current = findMin(current.right);
            }else{
                BinaryNode<T> child = current;
                current = current.parent;
                while(current != null && current.right == child){
                    child = current;
                    current = current.parent;
                }
                if (current == null){
                    atEnd = true;
                }
            }
            okToRemove = true;
            return nextItem;
        }

        @Override
        public void remove() {
            if (expectedModCount != modCount){
                try {
                    throw new ConcurrentModificationException();
                }catch (ConcurrentModificationException e){
                    e.printStackTrace();
                }
            }
            if (!okToRemove){
                throw new IllegalStateException();
            }
            try {
                MyTreeSet.this.remove(previous.data);
            } catch (UnderflowException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            okToRemove = false;
        }
    }

    public void makeEmpty(){
        modCount++ ;
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public boolean contains(T x){
        return contains(x, root);
    }

    public boolean contains(T x, BinaryNode<T> t){
        if(t == null){
            return false;
        }
        int compareResult = x.compareTo(t.data);
        if(compareResult < 0){
            return contains(x, t.left);
        }else if(compareResult > 0){
            return contains(x, t.right);
        }else{
            return true;
        }
    }

    public T findMin(){
        if(isEmpty()){
            try {
                throw new UnderflowException();
            } catch (UnderflowException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return findMin(root).data;
    }

    public T findMax(){
        if(isEmpty()){
            try {
                throw new UnderflowException();
            } catch (UnderflowException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return findMax(root).data;
    }

    public void insert(T x){
        root = insert(x, root, null);
    }

    public void remove(T x) throws UnderflowException{
        root = remove(x, root);
    }

    public void printTree(){
        if(isEmpty()){
            System.out.println("Empty tree");
        }else{
            printTree(root);
        }
    }

    public void printTree(BinaryNode<T> t){
        if(t != null){
            printTree(t.left);
            System.out.println(t.data);
            printTree(t.right);
        }
    }

    /**
     * 大致与TreeMap相似,不过TreeMap还要做修正(分四种情况：BB-1，BB2-R，BB2-B，BB-3)
     * @param x
     * @param t the root of tree
     * @return the root of tree
     */
    public BinaryNode<T> remove(T x, BinaryNode<T> t){
        if(t == null){
            try {
                throw new UnderflowException();
            } catch (UnderflowException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        int compareResult = x.compareTo(t.data);
        if(compareResult < 0){
            t = remove(x, t.left);
        }else if(compareResult > 0){
            t = remove(x, t.right);
        }else if(t.left != null && t.right != null){
            //要删除的节点是含有左右子树的节点
            t.data = findMin(t.right).data;//将右子树的最小值作为根节点
            t.right = remove(t.data, t.right);
        }else{
            modCount++ ;
            BinaryNode<T> oneChild;
            oneChild = (t.left == null)?t.left:t.right;
            oneChild.parent = t.parent;
            t = oneChild;
        }
        return t;
    }

    /**
     *
     * @param x
     * @param t the root of tree,while TreeSet has not this field and parameter(it has only the field NavigableMap)
     * @param parent the parent of the t
     * @return t, which is the root of new tree,while TreeMap.put(K k,V v) return the previous value(Note that TreeSet.add(E e)
     * call the TreeMap.put(e,PRSENT))
     */
    public BinaryNode<T> insert(T x, BinaryNode<T> t, BinaryNode<T> parent){
        if(t == null){
            modCount++ ;
            //空树
            return new BinaryNode(x, null, null, parent);
        }
        int compareResult = x.compareTo(t.data);
        if(compareResult < 0){
            //要插入的数小于节点值，插入到左子树
            t.left = insert(x, t.left, t);
        }else if(compareResult > 0){
            //要插入的数小于节点值，插入到左子树
            t.right = insert(x, t.right, t);
        }else{
            //duplicate
        }
        return t;
    }

    public BinaryNode<T> findMin(BinaryNode<T> t){
        // TODO Auto-generated method stub
        if(t == null){
            try {
                throw new UnderflowException();
            } catch (UnderflowException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else if(t.left == null){
            return t;
        }
        return findMin(t.left);
    }

    public BinaryNode<T> findMax(BinaryNode<T> t){
        // TODO Auto-generated method stub
        if(t == null){
            try {
                throw new UnderflowException();
            } catch (UnderflowException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else if(t.right == null){
            return t;
        }
        return findMax(t.right);
    }

    public static void main(String[] args) {
        MyTreeSet<Integer> myTreeSet = new MyTreeSet<Integer>();
        myTreeSet.insert(24);
        myTreeSet.insert(23);
        myTreeSet.insert(16);
        myTreeSet.insert(20);
        myTreeSet.insert(28);
        myTreeSet.insert(29);
        System.out.println("最小值： "+ myTreeSet.findMin());
        System.out.println("最大值： "+ myTreeSet.findMax());
        Iterator iter = myTreeSet.iterator();
        while(iter.hasNext()){
            System.out.print(iter.next() + "、");
        }

    }
}
class UnderflowException extends Exception{}
class CurrentModificationException extends Exception{}
class NoSuchElementException extends Exception{}
