/*
 * Copyright (C), 2014-2017, 江苏乐博国际投资发展有限公司
 * FileName: BinarySearchTree.java
 * Author:   zhangdanji
 * Date:     2017年08月30日
 * Description:   
 */
package com.mychebao.java;

import java.nio.BufferUnderflowException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangdanji
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    private static class BinaryNode<T>{
        public BinaryNode(T element){
            this(element,null,null);
        }

        public BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right){
            this.element = element;
            this.left = left;
            this.right = right;
        }

        T element;
        BinaryNode<T> left;
        BinaryNode<T> right;
    }

    private BinaryNode<T> root;

    public BinarySearchTree(){
        root = null;
    }

    public void makeEmpty(){
        root = null;
    }

    public boolean isEmpty(){
        return root == null;
    }

    public boolean contains(T x){
        return contains(x, root);
    }

    public T findMin(){
        if(isEmpty()){
            throw new BufferUnderflowException();
        }
        return findMin(root).element;
    }

    public T findMax(){
        if(isEmpty()){
            throw new BufferUnderflowException();
        }
        return findMax(root).element;
    }



    private boolean contains(T x,BinaryNode<T> t){
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

    private BinaryNode<T> findMin(BinaryNode<T> t){
        if(t == null){
            return null;
        }else if(t.left == null){
            return t;
        }
        return findMin(t.left);
    }

    private BinaryNode<T> findMax(BinaryNode<T> t){
        if(t != null){
            while(t.right != null){
                t = t.right;
            }
        }
        return t;
    }

    public void insert(T x){
        root = insert(x,root);
    }

    public void remove(T x){
        root = remove(x, root);
    }

    public void printTree(){
        if(isEmpty()){
            System.out.println("Empty Tree");
        }else{
            printTree(root);
        }
    }

    private BinaryNode<T> insert(T x,BinaryNode<T> t){
        if(t == null){
            return new BinaryNode<T>(x,null,null);
        }

        int compareResult = x.compareTo(t.element);

        if(compareResult < 0){
            t.left = insert(x,t.left);
        }else if(compareResult > 0){
            t.right = insert(x, t.right);
        }

        return t;
    }

    private BinaryNode<T> remove(T x,BinaryNode<T> t){
        if(t == null){
            return t;
        }

        int compareResult = x.compareTo(t.element);

        if(compareResult < 0){
            t.left = remove(x, t.left);
        }else if(compareResult > 0){
            t.right = remove(x, t.right);
        }else if(t.left != null && t.right != null){
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);
        }else{
            t = (t.left != null) ? t.left : t.right;
        }
        return t;
    }

    private void printTree(BinaryNode<T> t){
        if(t != null){
            printTree(t.left);
            System.out.println(t.element);
            printTree(t.right);
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
        for(int i = 1; i <= 1000; i ++){
            tree.insert(i);
        }
        long end = System.currentTimeMillis();
        System.out.println("tree build:" + (end - start));

        start = System.currentTimeMillis();
        List<Integer> nums = new ArrayList<Integer>(1000);
        for(int i = 1; i <= 1000; i ++){
            nums.add(i);
        }
        end = System.currentTimeMillis();
        System.out.println("list build:" + (end - start));

        start = System.currentTimeMillis();
        System.out.println(tree.contains(900));
        end = System.currentTimeMillis();
        System.out.println("tree find : " + (end - start));

        start = System.currentTimeMillis();
        System.out.println(nums.contains(9009));
        end = System.currentTimeMillis();
        System.out.println("list find:" + (end - start));
    }
}
