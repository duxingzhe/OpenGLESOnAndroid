package com.luxuan.opengles.Activity;

import android.opengl.GLSurfaceView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.luxuan.opengles.Router.table.RouteTable;
import com.luxuan.opengles.library.base.AbstractGLSurfaceActivity;
import com.luxuan.opengles.sample.basis.IndicesCubeRenderer;

@Route(path = RouteTable.PAGE_BASIS)
public class SimpleActivity extends AbstractGLSurfaceActivity {

    @Override
    public GLSurfaceView.Renderer bindRenderer(){
        return new IndicesCubeRenderer();
    }
}
