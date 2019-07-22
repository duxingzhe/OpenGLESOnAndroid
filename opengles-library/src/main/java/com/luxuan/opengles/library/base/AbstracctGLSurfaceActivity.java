package com.luxuan.opengles.library.base;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

public abstract class AbstracctGLSurfaceActivity extends AbstractBaseActivity {

    private GLSurfaceView mGLSurfaceView;

    protected abstract GLSurfaceView.Renderer bindRenderer();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setupViews();
    }

    private void setupViews(){
        mGLSurfaceView=new GLSurfaceView(this);
        setContentView(mGLSurfaceView);
        mGLSurfaceView.setEGLContextClientVersion(3);
        GLSurfaceView.Renderer render=bindRenderer();
        mGLSurfaceView.setRenderer(render);
    }

}
