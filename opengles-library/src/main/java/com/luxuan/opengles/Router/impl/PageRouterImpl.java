package com.luxuan.opengles.Router.impl;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.luxuan.opengles.Router.router.PageRouter;
import com.luxuan.opengles.Router.table.RouteTable;

public class PageRouterImpl extends PageRouter {

    @Override
    public void init(Application application){
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(application);
    }

    @Override
    public void routeBasisPage(){
        ARouter.getInstance().build(RouteTable.PAGE_BASIS).navigation();
    }

    @Override
    public void routePage(String routePage){
        ARouter.getInstance().build(routePage).navigation();
    }
}
