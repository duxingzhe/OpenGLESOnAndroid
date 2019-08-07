package com.luxuan.opengles.Activity;

import android.opengl.GLSurfaceView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.luxuan.opengles.Router.table.RouteTable;
import com.luxuan.opengles.library.base.AbstractGLSurfaceActivity;
import com.luxuan.opengles.sample.texture.TextureRenderer;

@Route(path = RouteTable.PAGE_TEXTURE)
public class TextureActivity extends AbstractGLSurfaceActivity {

    @Override
    protected GLSurfaceView.Renderer bindRenderer(){
        return new TextureRenderer();
    }
}
