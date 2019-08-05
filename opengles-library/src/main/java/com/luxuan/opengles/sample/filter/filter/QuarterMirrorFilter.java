package com.luxuan.opengles.sample.filter.filter;

import com.luxuan.opengles.library.R;
import com.luxuan.opengles.library.utils.ResReadUtils;

public class QuarterMirrorFilter extends BaseFilter {

    public QuarterMirrorFilter(){
        super(ResReadUtils.readResource(R.raw.quarter_mirror_filter_vertex_shader), ResReadUtils.readResource(R.raw.quarter_mirror_filter_fragment_shader));
    }
}
