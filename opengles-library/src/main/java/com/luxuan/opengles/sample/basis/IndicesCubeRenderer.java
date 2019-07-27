package com.luxuan.opengles.sample.basis;


import android.opengl.GLSurfaceView;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class IndicesCubeRenderer implements GLSurfaceView.Renderer {

    private final FloatBuffer vertexBuffer, colorBuffer;
    private final ShortBuffer indicesBuffer;

    private int mProgram;

    private static final int VERTEX_POSITION_SIZE=3;
    private static final int VERTEX_COLOR_SIZE=4;

    /**
     * 点的坐标
     */
    private float[] vertexPoints = new float[]{
            //正面矩形
            0.25f, 0.25f, 0.0f,  //V0
            -0.75f, 0.25f, 0.0f, //V1
            -0.75f, -0.75f, 0.0f, //V2
            0.25f, -0.75f, 0.0f, //V3

            //背面矩形
            0.75f, -0.25f, 0.0f, //V4
            0.75f, 0.75f, 0.0f, //V5
            -0.25f, 0.75f, 0.0f, //V6
            -0.25f, -0.25f, 0.0f //V7
    };

    /**
     * 定义索引
     */
    private short[] indices = {
            //背面
            5, 6, 7, 5, 7, 4,
            //左侧
            6, 1, 2, 6, 2, 7,
            //底部
            4, 7, 2, 4, 2, 3,
            //顶面
            5, 6, 7, 5, 7, 4,
            //右侧
            5, 0, 3, 5, 3, 4,
            //正面
            0, 1, 2, 0, 2, 3
    };

    //立方体的顶点颜色
    private float[] colors = {
            0.3f, 0.4f, 0.5f, 1f,   //V0
            0.3f, 0.4f, 0.5f, 1f,   //V1
            0.3f, 0.4f, 0.5f, 1f,   //V2
            0.3f, 0.4f, 0.5f, 1f,   //V3
            0.6f, 0.5f, 0.4f, 1f,   //V4
            0.6f, 0.5f, 0.4f, 1f,   //V5
            0.6f, 0.5f, 0.4f, 1f,   //V6
            0.6f, 0.5f, 0.4f, 1f    //V7
    };
}
