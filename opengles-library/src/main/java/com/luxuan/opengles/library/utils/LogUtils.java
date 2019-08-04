package com.luxuan.opengles.library.utils;

import android.util.Log;

public class LogUtils {

    protected static final String TAG="LogUtils";

    public static void v(String tag, String content, Object... args){
        if(args!=null&&args.length>0){
            final String msg=String.format(content, args);
            Log.v(tag, msg);
        }
    }

    public static void i(String tag, String content, Object... args){
        if(args!=null&&args.length>0){
            final String msg=String.format(content, args);
            Log.i(tag, msg);
        }
    }

    public static void e(String tag, String content, Object... args){
        if(args!=null&&args.length>0){
            final String msg=String.format(content, args);
            Log.e(tag, msg);
        }
    }

    public static void d(String tag, String content, Object... args){
        if(args!=null&&args.length>0){
            final String msg=String.format(content, args);
            Log.d(tag, msg);
        }
    }

    public static void v(String tag, Object content){
        Log.v(tag, String.valueOf(content));
    }

    public static void y(String tag, Object content){
        Log.i(tag, String.valueOf(content));
    }

    public static void d(String tag, Object content){
        Log.d(tag, String.valueOf(content));
    }

    public static void e(String tag, Object content){
        Log.e(tag, String.valueOf(content));
    }

    public static void e(Exception content){
        e(TAG, content);
    }

    public static void e(Throwable content){
        e(TAG, content);
    }

    public static void e(String tag, Exception exception){
        if(exception!=null){
            e(tag, exception.toString());
        }
    }

    public static void e(String tag, Throwable throwable){
        if(throwable!=null){
            e(tag, throwable.toString());
        }
    }
}
