package com.luxuan.opengles;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.luxuan.opengles.Router.router.PageRouter;
import com.luxuan.opengles.Router.table.RouteTable;

public class MainActivity extends Activity {

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

    public void onCameraClick(View view){
        PageRouter.getInstance().routePage(RouteTable.PAGE_CAMERA);
    }
}
