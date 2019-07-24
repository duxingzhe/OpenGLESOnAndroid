package com.luxuan.opengles.Router.router;

import android.app.Application;

import com.luxuan.opengles.Router.impl.PageRouterImpl;

public abstract class PageRouter {

    private static PageRouter sInstance;

    public static PageRouter getInstance(){
        if(sInstance==null){
            sInstance=new PageRouterImpl();
        }
        return sInstance;
    }

    public abstract void init(Application application);

    public abstract void routeBasisPage();

    public abstract void routePage(String routPage);

}
