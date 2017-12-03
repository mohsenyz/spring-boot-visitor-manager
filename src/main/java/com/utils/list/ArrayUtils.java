/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils.list;

import com.sina.sina.utils.IntegerHelper;

/**
 *
 * @author mphj
 */
public class ArrayUtils {
    public static int[] toIntArray(String[] string){
        int[] intArray = new int[string.length];
        for (int i =0; i < string.length; i++){
            intArray[i] = IntegerHelper.parseInt(string[i]);
        }
        return intArray;
    }


    public static String join(int[] objs) {
        String[] strings = new String[objs.length];
        for (int i = 0; i < objs.length; i++) {
            strings[i] = "" + objs[i];
        }
        return String.join(",", strings);
    }
}
