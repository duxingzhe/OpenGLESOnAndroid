package com.luxuan.opengles;

import android.os.Bundle;
import android.view.View;

import com.luxuan.opengles.Router.router.PageRouter;
import com.luxuan.opengles.Router.table.RouteTable;
import com.luxuan.opengles.library.base.AbstractBaseActivity;

public class MainActivity extends AbstractBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBasisClick(View view){
        PageRouter.getInstance().routeBasisPage();
    }

    public void onColorClick(View view) {
        PageRouter.getInstance().routePage(RouteTable.PAGE_COLOR);
    }

    public void onNativeClick(View view) {
        PageRouter.getInstance().routePage(RouteTable.PAGE_NATIVE);
    }

    public void onTextureClick(View view) {
        PageRouter.getInstance().routePage(RouteTable.PAGE_TEXTURE);
    }

    public void onCameraClick(View view){
        PageRouter.getInstance().routePage(RouteTable.PAGE_CAMERA);
    }

    public void onFilterClick(View view){
        PageRouter.getInstance().routePage(RouteTable.PAGE_FILTER);
    }
}
