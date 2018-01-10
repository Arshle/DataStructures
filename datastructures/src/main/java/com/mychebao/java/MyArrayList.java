/*
 * Copyright (C), 2014-2017, 江苏乐博国际投资发展有限公司
 * FileName: MyArrayList.java
 * Author:   zhangdanji
 * Date:     2017年08月29日
 * Description:   
 */
package com.mychebao.java;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author zhangdanji
 */
public class MyArrayList<T> implements Iterable<T>{

    private static final int DEFAULT_CAPACITY = 10;

    private int theSize;
    private T[] theItems;

    public MyArrayList(){
        doClear();
    }

    public void clear(){
        doClear();
    }

    private void doClear(){
        theSize = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size(){
        return theSize;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public void trimToSize(){
        ensureCapacity(size());
    }

    @SuppressWarnings("unchecked")
    public void ensureCapacity(int newCapacity){

        if(newCapacity < theSize){
            return;
        }

        T[] old = theItems;
        theItems = (T[]) new Object[newCapacity];
        System.arraycopy(old, 0, theItems, 0, size());
    }

    public boolean add(T x){
        add(size(),x);
        return true;
    }

    public void add(int idx,T x){
        if(theItems.length == size()){
            ensureCapacity(size() * 2 + 1);
        }
        for(int i = theSize; i > idx; i--){
            theItems[i] = theItems[i-1];
        }
        theItems[idx] = x;
        theSize ++;
    }

    public T remove(int idx){
        T removedItem = theItems[idx];
        for(int i = idx; i < size()-1; i++){
            theItems[i] = theItems[i+1];
        }
        theSize --;
        return removedItem;
    }

    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T>{
        private int current = 0;

        public boolean hasNext() {
            return current < size();
        }

        public T next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return theItems[current ++];
        }

        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }
}
