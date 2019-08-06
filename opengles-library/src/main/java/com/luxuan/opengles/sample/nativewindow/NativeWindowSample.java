package com.luxuan.opengles.sample.nativewindow;

public class NativeWindowSample {

    static{
        System.loadLibrary("native-window");
    }

    public native void drawColor(Object surface, int color);

    public native void drawBitmap(Object surface, Object bitmap);
}
