/*
 * Copyright (C), 2014-2017, 江苏乐博国际投资发展有限公司
 * FileName: PrintLots.java
 * Author:   zhangdanji
 * Date:     2017年08月30日
 * Description:   
 */
package com.mychebao.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zhangdanji
 */
public class PrintLots {

    private static List<Integer> L = new ArrayList<Integer>(Arrays.asList(4,6,30,40,79,90,101,82));
    private static LinkedList<Integer> P = new LinkedList<Integer>(Arrays.asList(1,3,4,6));

    static void printLots(List<Integer> L,LinkedList<Integer> P){
        long start = System.currentTimeMillis();
        while (!P.isEmpty()){
            System.out.println(L.get(P.poll()));
        }
        long end = System.currentTimeMillis();
        System.out.println("time : " + (end - start));
    }

    public static void main(String[] args) {
        printLots(L,P);
    }
}
