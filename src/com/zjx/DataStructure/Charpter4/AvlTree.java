package com.zjx.DataStructure.Charpter4;

public class AvlTree<T extends Comparable<? super T>> {

    private static class AvlNode<T extends Comparable<? super T>>{
        T element;    //节点数据
        AvlNode<T> left;
        AvlNode<T> right;
        int height;    //牺牲掉空间维护每个节点的高度,而不是平衡因子(这样会丧失清晰度)
        AvlNode(T theElement){
            this(theElement,null,null);
        }
        AvlNode(T theElement,AvlNode<T> lt,AvlNode<T> rt){
            element = theElement;
            left = lt;
            right = rt;
        }


    }

    /**
     * @param t
     * @return the height of node t,or -1,if null
     */
    private int height(AvlNode<T> t){
        return t == null? -1 : t.height;
    }

    /**
     *
     * @param x
     * @param t
     * @return the root of new subtree
     */
    private AvlNode<T> insert(T x,AvlNode<T> t){
        if (t == null)
            return new AvlNode<>(x,null,null);
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0)
            t.left = insert(x,t.left);
        else if (compareResult > 0)
            t.right = insert(x,t.right);
        else
            ;   //Duplicate;do nothing
        return balance(t);
    }

    private static final int ALLOWED_IMBALANCE = 1;

    /**
     * 分左右两种情况的单(双)旋,并更新节点高度
     * @param t
     * @return
     */
    private AvlNode<T> balance(AvlNode<T> t){
        if (t == null)
            return t;
        if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE)
            if (height(t.left.left) >= height(t.left.right)){
                t = rotateWithLeftChild(t);   //rotateWithLeftChild返回旋转后的根节点
            }
            else
                t = doubleWithLeftChild(t);
        else if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE){
            if (height(t.right.right) >= height(t.right.left))
                t = rotateWithRightChild(t);
            else
                t = doubleWithRightChild(t);
        }
        t.height = Math.max(height(t.left),height(t.right)) + 1;
        return t;
    }

    /**
     * Rotate binary tree node with left child,update heights
     * this is a single rotation
     * @param k2
     * @return new root
     */
    private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2){
        AvlNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left),height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left),k2.height) + 1;
        return k1;
    }

    /**
     * Double rotate binary tree node:first left child with its right child
     * then node k3 with new left child.
     * Update heights;
     * @param k3
     * @return new root
     */
    private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3){
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     * Rotate binary tree node with right child,update heights
     * this is a single rotation
     * @param k2
     * @return new root
     */
    private AvlNode<T> rotateWithRightChild(AvlNode<T> k2){
        AvlNode<T> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.height = Math.max(height(k2.left),height(k2.right)) + 1;
        k1.height = Math.max(k2.height,height(k1.right)) + 1;
        return k1;
    }

    /**
     * Double rotate binary tree node:first left child with its right child
     * then node k3 with new left child.
     * Update heights;
     * @param k3
     * @return new root
     */
    private AvlNode<T> doubleWithRightChild(AvlNode<T> k3){
        k3.right = rotateWithLeftChild(k3.right);
        return rotateWithRightChild(k3);
    }

    private AvlNode<T> remove(T x,AvlNode<T> t){
        if (t == null)
            return t;   //Item not found;do nothing
        int compareResult = x.compareTo(t.element);
        if (compareResult < 0)
            t.left = remove(x,t.left);
        else if (compareResult > 0)
            t.right = remove(x,t.right);
        else if (t.left != null && t.right != null){
            t.element = findMin(t.right).element;
            t.right = remove(t.element,t.right);
        }
        else
            t = (t.left != null)? t.left : t.right;
        return balance(t);
    }

    private AvlNode<T> findMin(AvlNode<T> t){
        if (t == null)
            return null;
        else if (t.left == null)
            return t;
        else
               return findMin(t.left);
    }

}
