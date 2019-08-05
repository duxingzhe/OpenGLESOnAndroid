package com.luxuan.opengles.sample.filter.view;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.luxuan.opengles.sample.filter.filter.BaseFilter;
import com.luxuan.opengles.sample.filter.renderer.FilterRenderer;

public class OpenGLView extends GLSurfaceView {

    private FilterRenderer mGLRenderer;

    public OpenGLView(Context context){
        this(context, null);
    }

    public OpenGLView(Context context, AttributeSet attrs){
        super(context, attrs);
        setupSurfaceView();
    }

    private void setupSurfaceView(){
        setEGLContextClientVersion(3);

        mGLRenderer=new FilterRenderer();
        setRenderer(mGLRenderer);

        try{
            requestRender();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setFilter(final BaseFilter baseFilter){
        queueEvent(new Runnable(){
            @Override
            public void run(){
                if(mGLRenderer!=null){
                    mGLRenderer.setFilter(baseFilter);
                }
            }
        });
        try{
            requestRender();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
