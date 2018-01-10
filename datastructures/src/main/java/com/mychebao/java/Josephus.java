/*
 * Copyright (C), 2014-2017, 江苏乐博国际投资发展有限公司
 * FileName: Josephus.java
 * Author:   zhangdanji
 * Date:     2017年08月30日
 * Description:   
 */
package com.mychebao.java;

/**
 * @author zhangdanji
 */
public class Josephus<T> {

    private static class Node<T>{
        private T item;
        private Node<T> next;
        private Node<T> prev;

        public Node(T item,Node<T> next,Node<T> prev){
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<T> first;

    private Node<T> end;

    public Josephus(){
        first = new Node<T>(null,null,null);
        end = new Node<T>(null,null,first);
        first.next = end;
    }

    public Josephus(Integer num,T... items){
        this();
        if(items.length != num){
            return;
        }
        if(num > 0){
            for(int i = 1; i <= num; i++){
                Node<T> current = new Node<T>(items[i-1],end,end.prev);
                end.prev.next = current;
                end.prev = current;
            }
        }
    }


    public void printAll(){
        Node<T> current = first;
        while(current.next.item != null){
            System.out.println(current.next.item);
            current = current.next;
        }
    }

    public static void main(String[] args) {
        Josephus<Integer> link = new Josephus<Integer>(5,1,2,3,4,5);
        link.printAll();
    }
}
