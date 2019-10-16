package com.example.Utils;

public class Utils {

    public  static boolean  isStringNull(String str){
        if(str == null || str.trim().length() == 0) {
            return true;
        }
        return false;
    }
}
