package com.luxuan.opengles.Activity;

import android.graphics.Color;
import android.opengl.GLSurfaceView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.luxuan.opengles.Router.table.RouteTable;
import com.luxuan.opengles.library.base.AbstractGLSurfaceActivity;
import com.luxuan.opengles.sample.color.NativeColorRenderer;

@Route(path = RouteTable.PAGE_COLOR)
public class ColorActivity extends AbstractGLSurfaceActivity {

    @Override
    public GLSurfaceView.Renderer bindRenderer(){
        return new NativeColorRenderer(Color.GRAY);
    }
}
