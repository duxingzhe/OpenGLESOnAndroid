package com.luxuan.opengles.sample.filter.filter;

import android.opengl.GLES30;

import com.luxuan.opengles.library.R;
import com.luxuan.opengles.library.utils.ResReadUtils;

public class GrayFilter extends BaseFilter {

    private int aFilterLocation;

    private float[] filterValue=new float[]{0.299f, 0.587f, 0.114f};

    public GrayFilter(){
        super(ResReadUtils.readResource(R.raw.gray_filter_vertex_shader), ResReadUtils.readResource(R.raw.gray_filter_fragment_shader));
    }

    @Override
    public void setupProgram(){
        super.setupProgram();
        aFilterLocation= GLES30.glGetUniformLocation(mProgram, "a_Filter");
    }

    @Override
    public void onUpdateDrawFrame(){
        GLES30.glUniform3fv(aFilterLocation, 1, filterValue, 0);
    }
}
