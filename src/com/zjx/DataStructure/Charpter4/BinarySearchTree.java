package com.zjx.DataStructure.Charpter4;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.TreeMap;

public class BinarySearchTree<T extends Comparable<? super T>> {
    private BinaryNode<T> root;

    private static class BinaryNode<T>{
        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;
        BinaryNode(T element,BinaryNode<T> left,BinaryNode<T> right){
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }

    public BinarySearchTree(){
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public boolean contains(T t){
        return contains(t,root);
    }

    public T findMin() throws Exception {
        if (isEmpty()) throw new Exception();
        return findMin(root).element;
    }

    public T findMax() throws Exception {
        if (isEmpty()) throw new Exception();
        return findMax(root).element;
    }

    public void insert(T t){
        root = insert(t,root);
    }

    public void remove(T t){
        root = remove(t,root);
    }

    public void printTree(){
        if(isEmpty()){
            System.out.println("Empty Tree");
        }
        else
            printTree(root);
    }

    /**
     * 4.18
     * @param x
     * @param t
     * @return
     */
    private boolean contains(T x,BinaryNode<T> t){
        if (t == null) return false;
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0)
            return contains(x,t.left);
        else if (compareResult > 0)
            return contains(x,t.right);
        else
            return true;   //Match

    }

    /**
     * 4.20  参考TreeMap的getFirstEntry,功能类似
     * @param t
     * @return
     */
    private BinaryNode<T> findMin(BinaryNode<T> t){
        if (t == null) return null;
        else if (t.left == null)
            return t;
        else return findMin(t.left);
        //尾递归,可转换为迭代,参考getFirstEntry
//        TreeMap.Entry<K,V> p = root;
//        if (p != null)
//            while (p.left != null)
//                p = p.left;
//        return p;
    }

    /**
     * 4.20 参考TreeMap的getFirstEntry,功能类似
     * @param t
     * @return
     */
    private BinaryNode<T> findMax(BinaryNode<T> t){
        if (t != null)
            while(t.right != null)
                t = t.right;
        return t;

    }

    /**
     * 4.22 类似于TreeMap的put(K k,V v),put的语义是查找key值与k相同的Entry,返回旧值(不存在时返回null),然后再插入新节点或者
     * 更新旧值为新值,最后可能会有插入新节点后的修正;而该insert只是插入并返回新值(相同的话什么也不做)
     * @param x the item will be inserted to the Tree
     * @param t t is the root/BinaryNode of Tree
     * @return
     */
    private BinaryNode<T> insert(T x,BinaryNode<T> t){
        if (t == null) return new BinaryNode<>(x,null,null);
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0)
            t.left = insert(x,t.left);
        else if(compareResult > 0)
            t.right = insert(x,t.right);
        else
            ;       //do nothing
        return t;

    }

    /**
     * 方法写的很巧妙,需要记下
     * 删除情况分为：
     *  1)空节点时,返回null
     *  2)非空节点但没有左右孩子,直接删除.并修改父节点的对应属性
     *  3)有一个孩子:由孩子接替该节点所在的位置
     *  4)有两个孩子:找到后继节点(中序遍历下后继节点),将后继节点的数据赋值到该节点,
     *  此时后继节点就应该删除且后继节点的删除符合2),3)情况
     * @param x
     * @param t
     * @return the new root of the subtree
     */
    private BinaryNode<T> remove(T x,BinaryNode<T> t){
        if(t == null)
            return t;
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0)
            t.left = remove(x,t.left);
        else if (compareResult > 0)
            t.right = remove(x,t.right);
        else if (t.left != null && t.right != null){
            t.element = findMin(t.right).element;   //记下
            t.right = remove(x,t.right);
        }
        else{
            t = (t.left == null)? t.left:t.right;
        }
        return t;
    }

    /**
     * O(N)
     * @param t
     */
    private void printTree(BinaryNode<T> t){
        if (t != null){
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    private int height(BinaryNode<T> t){
        if (t == null)
            return -1;
        else
            return 1 + Math.max(height(t.left),height(t.right));
    }
}
