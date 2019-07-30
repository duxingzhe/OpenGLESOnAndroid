package com.luxuan.opengles.sample.camera.camera;

import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.IntDef;
import android.view.TextureView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class TextureEGLHelper extends HandlerThread implements SurfaceTexture.OnFrameAvailableListener {

    private static final String TAG="TextureEGLHelper";

    @IntDef({EGLMessage.MSG_INIT, EGLMessage.MSG_RENDER, EGLMessage.MSG_DESTROY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface EGLMessage{
        int MSG_INIT=10;
        int MSG_RENDER=200;
        int MSG_DESTROY=300;
    }

    private HandlerThread mHandlerThread;

    private Handler mHandler;

    private TextureView mTextureView;

    private int mOESTextureId;

    private EGLDisplay mGELDisplay= EGL14.EGL_NO_DISPLAY;

    private EGLContext mEGLContext=EGL14.EGL_NO_CONTEXT;

    private EGLConfig[] configs=new EGLConfig[1];

    private EGLSurface mEglSurface;

    private SurfaceTexture mOESSurfaceTexture;

    private CameraTextureRenderer mTextureRenderer;

    private final class TextureHandler extends Handler{

        public TextureHandler(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(Message msg){
            switch(msg.what){
                case EGLMessage.MSG_INIT:
                    initEGLContext(3);
                    break;
                case EGLMessage.MSG_RENDER:
                    drawFrame();
                    return;
                case EGLMessage.MSG_DESTROY:
                    return;
                default:
                    return;
            }
        }
    }

    public TextureEGLHelper(){
        super("TextureEGLHelper");
    }
}
