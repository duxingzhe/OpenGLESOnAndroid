package com.luxuan.opengles.Application;


import android.app.Application;

import com.luxuan.opengles.Router.router.PageRouter;
import com.luxuan.opengles.library.core.AppCore;

public class SampleApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        AppCore.getInstance().init(this);
        PageRouter.getInstance().init(this);
    }

}
