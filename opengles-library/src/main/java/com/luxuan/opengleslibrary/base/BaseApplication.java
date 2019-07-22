package com.luxuan.opengleslibrary.base;

import android.app.Application;

import com.luxuan.opengleslibrary.core.AppCore;

public class BaseApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        AppCore.getInstance().init(this);
    }
}
