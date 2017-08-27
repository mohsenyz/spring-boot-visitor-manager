/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils.list;

/**
 *
 * @author mphj
 */
public class ArrayUtils {
    public static int[] toIntArray(String[] string){
        int[] intArray = new int[string.length];
        for (String str : string){
            intArray[intArray.length] = Integer.parseInt(str);
        }
        return intArray;
    }
}
