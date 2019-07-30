package com.luxuan.opengles.sample.camera.api;

import android.graphics.SurfaceTexture;

public interface ICamera {

    boolean openCamera(int cameraId);

    void enablePreview(boolean enable);

    void setPreviewTexture(SurfaceTexture surfaceTexture);

    void closeCamera();
}
