package com.luxuan.opengles.sample.camera.renderer;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.opengl.GLES11Ext;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.view.Surface;

import com.luxuan.opengles.library.R;
import com.luxuan.opengles.library.utils.ResReadUtils;
import com.luxuan.opengles.library.utils.ShaderUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class CameraSurfaceRenderer implements GLSurfaceView.Renderer{

    private static final String TAG="CameraTextureRenderer";

    private final FloatBuffer vertexBuffer, mTexVertexBuffer;

    private final ShortBuffer mVertexIndexBuffer;

    private int mProgram;

    private int textureId;

    /**
     * 顶点坐标
     * (x,y,z)
     */
    private float[] POSITION_VERTEX = new float[]{
            0f, 0f, 0f,     //顶点坐标V0
            1f, 1f, 0f,     //顶点坐标V1
            -1f, 1f, 0f,    //顶点坐标V2
            -1f, -1f, 0f,   //顶点坐标V3
            1f, -1f, 0f     //顶点坐标V4
    };

    /**
     * 纹理坐标
     * (s,t)
     */
    private static final float[] TEX_VERTEX = {
            0.5f, 0.5f, //纹理坐标V0
            1f, 1f,     //纹理坐标V1
            0f, 1f,     //纹理坐标V2
            0f, 0.0f,   //纹理坐标V3
            1f, 0.0f    //纹理坐标V4
    };

    /**
     * 索引
     */
    private static final short[] VERTEX_INDEX = {
            0, 1, 2,  //V0,V1,V2 三个顶点组成一个三角形
            0, 2, 3,  //V0,V2,V3 三个顶点组成一个三角形
            0, 3, 4,  //V0,V3,V4 三个顶点组成一个三角形
            0, 4, 1   //V0,V4,V1 三个顶点组成一个三角形
    };

    private float[] transformatMatrix=new float[16];

    private GLSurfaceView mGLSurfaceView;

    private int mCameraId;

    private Camera mCamera;

    private SurfaceTexture mSurfaceTexture;

    private int uTextureMatrixLocation;

    private int uTextureSamplerLocation;

    public CameraSurfaceRenderer(GLSurfaceView glSurfaceView){
        mCameraId=Camera.CameraInfo.CAMERA_FACING_FRONT;
        mGLSurfaceView=glSurfaceView;
        mCamera=Camera.open(mCameraId);
        setCameraDisplayOrientation(mCameraId, mCamera);

        vertexBuffer= ByteBuffer.allocateDirect(POSITION_VERTEX.length * 4).order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexBuffer.put(POSITION_VERTEX);
        vertexBuffer.position(0);

        mTexVertexBuffer=ByteBuffer.allocateDirect(VERTEX_INDEX.length *4 ).order(ByteOrder.nativeOrder())
                .asFloatBuffer().put(TEX_VERTEX);
        mTexVertexBuffer.position(0);

        mVertexIndexBuffer=ByteBuffer.allocateDirect(VERTEX_INDEX.length *2).order(ByteOrder.nativeOrder())
                .asShortBuffer().put(VERTEX_INDEX);
        mVertexIndexBuffer.position(0);
    }

    private void setCameraDisplayOrientation(int cameraId, Camera camera){
        Activity targetActivity=(Activity)mGLSurfaceView.getContext();
        Camera.CameraInfo info=new Camera.CameraInfo();
        Camera.getCameraInfo(cameraId, info);

        int rotation=targetActivity.getWindowManager().getDefaultDisplay().getRotation();
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
            result=(info.orientation-degrees+360)%360;
        }
        camera.setDisplayOrientation(result);
    }

    public int loadTexture(){
        int[] tex=new int[1];

        GLES30.glGenTextures(1, tex, 0);
        GLES30.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, tex[0]);

        GLES30.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_NEAREST);
        GLES30.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_LINEAR);
        GLES30.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES30.GL_TEXTURE_WRAP_S, GLES30.GL_CLAMP_TO_EDGE);
        GLES30.glTexParameterf(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, GLES30.GL_TEXTURE_WRAP_T, GLES30.GL_CLAMP_TO_EDGE);

        GLES30.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, 0);

        return tex[0];
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config){

        GLES30.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);

        final int vertexShaderId= ShaderUtils.compileVertexShader(ResReadUtils.readResource(R.raw.vertex_camera_shader));
        final int fragmentShaderId=ShaderUtils.compileFragmentShader(ResReadUtils.readResource(R.raw.fragment_camera_shader));

        mProgram=ShaderUtils.linkProgram(vertexShaderId, fragmentShaderId);

        uTextureMatrixLocation=GLES30.glGetUniformLocation(mProgram, "uTextureMatrix");
        uTextureSamplerLocation=GLES30.glGetUniformLocation(mProgram, "yuvTexSampler");

        textureId=loadTexture();

        loadSurfaceTexture(textureId);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height){
        GLES30.glViewport( 0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl){
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);

        GLES30.glUseProgram(mProgram);

        mSurfaceTexture.updateTexImage();
        mSurfaceTexture.getTransformMatrix(transformatMatrix);

        GLES30.glActiveTexture(GLES30.GL_TEXTURE0);
        GLES30.glBindTexture(GLES11Ext.GL_TEXTURE_EXTERNAL_OES, textureId);
        GLES30.glUniform1i(uTextureSamplerLocation, 0);

        GLES30.glUniformMatrix4fv(uTextureMatrixLocation, 1, false, transformatMatrix, 0);
        GLES30.glEnableVertexAttribArray(0);
        GLES30.glVertexAttribPointer(0,3, GLES30.GL_FLOAT, false, 0, vertexBuffer);

        GLES30.glEnableVertexAttribArray(1);
        GLES30.glVertexAttribPointer(1,2,GLES30.GL_FLOAT, false, 0, mVertexIndexBuffer);

        GLES20.glDrawElements(GLES20.GL_TRIANGLES, VERTEX_INDEX.length, GLES20.GL_UNSIGNED_SHORT, mVertexIndexBuffer);
    }

    public boolean loadSurfaceTexture(int textureId){

        mSurfaceTexture=new SurfaceTexture(textureId);
        mSurfaceTexture.setOnFrameAvailableListener(new SurfaceTexture.OnFrameAvailableListener(){
            @Override
            public void onFrameAvailable(SurfaceTexture surfaceTexture){
                mGLSurfaceView.requestRender();
            }
        });

        try{
            mCamera.setPreviewTexture(mSurfaceTexture);
        }catch(IOException e){
            e.printStackTrace();
        }

        mCamera.startPreview();
        return true;
    }
}
