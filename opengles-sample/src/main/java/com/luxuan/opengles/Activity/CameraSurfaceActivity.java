package com.luxuan.opengles.Activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.luxuan.opengles.Router.table.RouteTable;
import com.luxuan.opengles.library.base.AbstractBaseActivity;
import com.luxuan.opengles.sample.camera.renderer.CameraSurfaceRenderer;

@Route(path = RouteTable.PAGE_CAMERA)
public class CameraSurfaceActivity extends AbstractBaseActivity {

    private static final int PERMISSION_CODE=100;

    private GLSurfaceView mGLSurfaceView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        applyPermission();
    }

    private void applyPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_CODE);
        } else {
            setupView();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_CODE && grantResults != null && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupView();
            }
        }
    }

    private void setupView(){
        mGLSurfaceView=new GLSurfaceView(this);
        mGLSurfaceView.setEGLContextClientVersion(3);
        mGLSurfaceView.setRenderer(new CameraSurfaceRenderer(mGLSurfaceView));
        setContentView(mGLSurfaceView);
    }
}
