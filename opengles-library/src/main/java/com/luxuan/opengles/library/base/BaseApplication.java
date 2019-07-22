package com.luxuan.opengles.library.base;

import android.app.Application;

import com.luxuan.opengles.library.core.AppCore;

public class BaseApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        AppCore.getInstance().init(this);
    }
}
