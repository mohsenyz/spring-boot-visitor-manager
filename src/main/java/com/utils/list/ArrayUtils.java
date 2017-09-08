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
        for (int i =0; i < string.length; i++){
            intArray[i] = Integer.parseInt(string[i]);
        }
        return intArray;
    }
}
