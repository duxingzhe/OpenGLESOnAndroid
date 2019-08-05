package com.luxuan.opengles.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.luxuan.opengles.R;
import com.luxuan.opengles.Router.table.RouteTable;
import com.luxuan.opengles.library.base.AbstractBaseActivity;
import com.luxuan.opengles.sample.filter.filter.GrayFilter;
import com.luxuan.opengles.sample.filter.filter.OriginFilter;
import com.luxuan.opengles.sample.filter.filter.QuarterMirrorFilter;
import com.luxuan.opengles.sample.filter.view.OpenGLView;

@Route(path = RouteTable.PAGE_FILTER)
public class FilterActivity extends AbstractBaseActivity {

    private ViewGroup mRootLayer;

    private OpenGLView mGLView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        setupViews();
    }

    private void setupViews(){
        mRootLayer=(ViewGroup) findViewById(R.id.linear_root_layer);

        mGLView=new OpenGLView(this);
        mRootLayer.addView(mGLView, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemId=item.getItemId();
        if(itemId==R.id.filter_default){
            mGLView.setFilter(new OriginFilter());
        }else if(itemId==R.id.filter_gray){
            mGLView.setFilter(new GrayFilter());
        }else if(itemId==R.id.filter_quarter_mirror){
            mGLView.setFilter(new QuarterMirrorFilter());
        }
        return super.onOptionsItemSelected(item);
    }
}
