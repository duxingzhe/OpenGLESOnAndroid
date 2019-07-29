package com.luxuan.opengles;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.luxuan.opengles.Router.router.PageRouter;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBasisClick(View view){
        PageRouter.getInstance().routeBasisPage();
    }
}
