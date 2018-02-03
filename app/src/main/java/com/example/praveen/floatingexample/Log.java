package com.example.praveen.floatingexample;

import java.util.Objects;

/**
 * Created by praveen on 20/8/17.
 */

public class Log {

    private static final String TAG = "FloatExampleLog";
    public static void print(Object obj){
        System.out.print(obj);
    }

    public static void println(Object obj){
        android.util.Log.d(TAG,obj.toString());
        System.out.println(obj);
    }

    public static void error(Exception e){
        android.util.Log.e(TAG,e.getMessage(),e.fillInStackTrace());
    }

}
