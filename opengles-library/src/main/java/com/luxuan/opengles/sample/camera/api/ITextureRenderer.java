package com.luxuan.opengles.sample.camera.api;

import android.graphics.SurfaceTexture;

public interface ITextureRenderer {

    void onSurfaceCreated();

    void onSurfaceChanged(int widht, int height);

    void onDrawFrame(SurfaceTexture surfaceTexture);
}
