/*
 * Copyright (C), 2014-2017, 江苏乐博国际投资发展有限公司
 * FileName: AVLSearchTree.java
 * Author:   zhangdanji
 * Date:     2017年08月31日
 * Description:   
 */
package com.mychebao.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author zhangdanji
 */
public class AVLSearchTree<T extends Comparable<? super T>> {

    private static final int ALLOWED_IMBALANCE = 1;
    private AvlNode<T> root;

    public AVLSearchTree(){
        root = null;
    }

    public void makeEmpty(){
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public void insert(T x){
        root = insert(x,root);
    }

    public boolean contains(T x){
        return contains(x, root);
    }

    private static class AvlNode<T>{

        private T element;
        private AvlNode<T> left;
        private AvlNode<T> right;
        private int height;

        AvlNode(T element){
            this(element,null,null);
        }

        AvlNode(T element,AvlNode<T> left,AvlNode<T> right){
            this.element = element;
            this.left = left;
            this.right = right;
            this.height = 0;
        }
    }
    private int height(AvlNode<T> t){
        return t == null ? -1 : t.height;
    }

    private AvlNode<T> insert(T x,AvlNode<T> t){
        if(t == null){
            return new AvlNode<T>(x,null,null);
        }

        int compareResult = x.compareTo(t.element);

        if(compareResult < 0){
            t.left = insert(x, t.left);
        }else if(compareResult > 0){
            t.right = insert(x, t.right);
        }

        return balance(t);
    }

    private AvlNode<T> remove(T x,AvlNode<T> t){
        if(t == null){
            return t;
        }

        int compareResult = x.compareTo(t.element);

        if(compareResult < 0){
            t.left = remove(x, t.left);
        }else if(compareResult > 0){
            t.right = remove(x, t.right);
        }else if(t.left != null && t.right != null){
            t.element = findMax(t.right).element;
            t.right = remove(t.element, t.right);
        }else{
            t = (t.left != null) ? t.left : t.right;
        }

        return balance(t);
    }

    private AvlNode<T> findMin(AvlNode<T> t){
        if(t == null){
            return null;
        }else if(t.left == null){
            return t;
        }
        return findMin(t.left);
    }

    private AvlNode<T> findMax(AvlNode<T> t){
        if(t != null){
            while(t.right != null){
                t = t.right;
            }
        }
        return t;
    }

    private AvlNode<T> rotateWithLeftChild(AvlNode<T> k2){
        AvlNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left),height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left),k2.height) + 1;
        return k1;
    }

    private AvlNode<T> rotateWithRightChild(AvlNode<T> k2){
        AvlNode<T> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.height = Math.max(height(k2.left),height(k2.right)) + 1;
        k1.height = Math.max(height(k1.right),k2.height) + 1;
        return k1;
    }

    private AvlNode<T> doubleWithLeftChild(AvlNode<T> k3){
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private AvlNode<T> doubleWithRightChild(AvlNode<T> k3){
        k3.right = rotateWithLeftChild(k3.right);
        return rotateWithRightChild(k3);
    }

    private boolean contains(T x,AvlNode<T> t){
        if(t == null){
            return false;
        }

        int compareResult = x.compareTo(t.element);

        if(compareResult < 0){
            return contains(x, t.left);
        }else if(compareResult > 0){
            return contains(x, t.right);
        }else{
            return true;
        }
    }

    private AvlNode<T> balance(AvlNode<T> t){
        if(t == null){
            return t;
        }

        if(height(t.left) - height(t.right) > ALLOWED_IMBALANCE){
            if(height(t.left.left) >= height(t.left.right)){
                t = rotateWithLeftChild(t);
            }else{
                t = doubleWithLeftChild(t);
            }
        }else{
            if(height(t.right) - height(t.left) > ALLOWED_IMBALANCE){
                if(height(t.right.right) >= height(t.right.left)){
                    t = rotateWithRightChild(t);
                }else{
                    t = doubleWithRightChild(t);
                }
            }
        }
        t.height = Math.max(height(t.left),height(t.right)) + 1;
        return t;
    }

    public static void main(String[] args) {
        Set<String> set = new TreeSet<String>();
        long start = System.currentTimeMillis();
        AVLSearchTree<Integer> tree = new AVLSearchTree<Integer>();
        for(int i = 1; i <= 1000000; i ++){
            tree.insert(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("tree build:" + (end - start));

        start = System.currentTimeMillis();
        List<Integer> nums = new ArrayList<Integer>(1000000);
        for(int i = 1; i <= 1000000; i ++){
            nums.add(i);
        }
        end = System.currentTimeMillis();
        System.out.println("list build:" + (end - start));

        start = System.currentTimeMillis();
        System.out.println(tree.contains(999999));
        end = System.currentTimeMillis();
        System.out.println("tree find : " + (end - start));

        start = System.currentTimeMillis();
        System.out.println(nums.contains(999999));
        end = System.currentTimeMillis();
        System.out.println("list find:" + (end - start));
    }
}
