package com.luxuan.opengles.sample.camera.camera;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.util.Log;
import android.view.TextureView;

import com.luxuan.opengles.library.utils.TextureUtils;
import com.luxuan.opengles.sample.camera.api.ICamera;

public class CameraV1Pick implements TextureView.SurfaceTextureListener {

    private static int final String TAG="CameraV1Pick";

    private TextureView mTextureView;

    private int mCameraId;

    private ICamera mCamera;

    private TextureEGLHelper mTextureEglHelper;

    public void bindTextureView(TextureView textureView){
        this.mTextureView=textureView;
        mTextureEglHelper=new TextureEGLHelper();
        mTextureView.setSurfaceTextureListener(this);
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height){
        final int textureId= TextureUtils.loadOESTexture();

        mTextureEglHelper.initEgl(mTextureView, textureId);

        SurfaceTexture surfaceTexture=mTextureEglHelper.loadOESTexture();

        mCameraId= Camera.CameraInfo.CAMERA_FACING_FRONT;
        mCamera=new CameraV1((Activity)mTextureView.getContext());

        if(mCamera.openCamera(mCameraId)){
            mCamera.setPreviewTexture(surfaceTexture);
            mCamera.enablePreview(true);
        }else{
            Log.e(TAG, "openCamera failed");
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height){
        mTextureEglHelper.onSurfaceChanged(width, height);
    }
}
