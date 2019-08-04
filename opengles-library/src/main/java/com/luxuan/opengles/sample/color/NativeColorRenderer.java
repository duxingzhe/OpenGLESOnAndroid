package com.luxuan.opengles.sample.color;

import android.opengl.GLSurfaceView;

public class NativeColorRenderer extends GLSurfaceView.Renderer {

    static {
        System.loadLibrary("native-color");
    }
}
