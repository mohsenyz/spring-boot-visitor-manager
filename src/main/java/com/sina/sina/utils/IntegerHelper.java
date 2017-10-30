package com.sina.sina.utils;

public class IntegerHelper {
    public static int parseInt(String value){
        try {
            return Integer.parseInt(value);
        }catch (Exception e){
            return 0;
        }
    }
}
