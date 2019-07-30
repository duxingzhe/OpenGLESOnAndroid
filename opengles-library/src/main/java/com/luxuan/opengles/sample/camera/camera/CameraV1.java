package com.luxuan.opengles.sample.camera.camera;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.view.Surface;

import com.luxuan.opengles.sample.camera.api.ICamera;

import java.io.IOException;

public class CameraV1 implements ICamera {

    private Activity mActivity;

    private int mCameraId;

    private Camera mCamera;

    public CameraV1(Activity activity){
        mActivity=activity;
    }

    @Override
    public boolean openCamera(int cameraId){
        try{
            mCameraId=cameraId;
            mCamera=Camera.open(mCameraId);
            setCameraDisplayOrientation(mActivity, mCameraId, mCamera);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void enablePreview(boolean enable){
        if(mCamera!=null){
            if(enable){
                mCamera.startPreview();
            }else{
                mCamera.stopPreview();
            }
        }
    }

    @Override
    public void setPreviewTexture(SurfaceTexture surfaceTexture){
        if(mCamera!=null){
            try{
                mCamera.setPreviewTexture(surfaceTexture);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void closeCamera(){
        if(mCamera!=null){
            mCamera.release();
            mCamera=null;
        }

        mActivity=null;
    }

    private void setCameraDisplayOrientation(Activity activity, int cameraId, Camera camera){
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);

        int rotation=activity.getWindowManager().getDefaultDisplay().getRotation();
        int degrees=0;
        switch(rotation){
            case Surface.ROTATION_0:
                degrees=0;
                break;
            case Surface.ROTATION_90:
                degrees=90;
                break;
            case Surface.ROTATION_180:
                degrees=180;
                break;
            case Surface.ROTATION_270:
                degrees=270;
                break;
        }

        int result;
        if(info.facing==Camera.CameraInfo.CAMERA_FACING_FRONT){
            result=(info.orientation+degrees)%360;
            result=(360-result)%360;
        }else{
            result=(info.orientation-degrees+360)%369;
        }

        camera.setDisplayOrientation(result);
    }
}
