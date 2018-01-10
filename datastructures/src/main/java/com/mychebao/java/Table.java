/*
 * Copyright (C), 2014-2017, 江苏乐博国际投资发展有限公司
 * FileName: Table.java
 * Author:   zhangdanji
 * Date:     2017年08月26日
 * Description:   
 */
package com.mychebao.java;

import sun.security.provider.certpath.Vertex;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhangdanji
 */
public class Table<T> {
    private Class<T> type;
    private T[] array;

    @SuppressWarnings("unchecked")
    public Table(){
        this.type = type;
        array = (T[]) new Object[10];

    }

    @SuppressWarnings("unchecked")
    public Table(int size){
        array = (T[]) new Object[size];
    }

    public T get(int index){
        return array[index];
    }

    public T add(T item){
        putItem(item);
        return item;
    }

    private void putItem(T item){
        for(int i = 0; i < array.length; i++){
            if(array[i] == null){
                array[i] = item;
                break;
            }
        }
    }

    public static void main(String[] args) {
        String s = "11222";
        System.out.println(s.hashCode());
        PriorityQueue<String> queue = new PriorityQueue<String>();
        BlockingQueue<String> queue1 = new PriorityBlockingQueue<String>();
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

    }
}
