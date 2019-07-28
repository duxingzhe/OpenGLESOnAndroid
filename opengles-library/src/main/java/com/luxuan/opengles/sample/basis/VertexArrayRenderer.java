package com.luxuan.opengles.sample.basis;

import android.opengl.GLSurfaceView;

import java.nio.FloatBuffer;

public class VertexArrayRenderer implements GLSurfaceView.Renderer {

    private static final String TAG="VertexBufferRenderer";

    private static final int VERTEX_POS_INDEX=0;
    private final FloatBuffer vertexBuffer;
    private static final int VERTEX_POS_SIZE=3;
    private static final int VERTEX_STRIDE=VERTEX_POS_SIZE*4;

    private int mProgram;

    /**
     * 点的坐标
     */
    private float[] vertexPoints = new float[]{
            0.0f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f
    };

    /**
     * 缓冲数组
     */
    private int[] vaoIds=new int[1];
    private int[] vboIds=new int[1];

}
