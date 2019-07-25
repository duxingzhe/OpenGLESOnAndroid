package com.luxuan.opengles.sample.basis;

import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class ColorCubeRenderer implements GLSurfaceView.Renderer {

    private final FloatBuffer vertexBuffer, colorBuffer;

    private int mProgram;

    private static final int VERTEX_POSITION_SIZE=3;
    private static final int VERTEX_COLOR_SIZE=4;

    /**
     * 点的坐标
     */
    private float[] vertexPoints = new float[]{
            //背面矩形
            0.75f, 0.75f, 0.0f, //V5
            -0.25f, 0.75f, 0.0f, //V6
            -0.25f, -0.25f, 0.0f, //V7
            0.75f, 0.75f, 0.0f, //V5
            -0.25f, -0.25f, 0.0f, //V7
            0.75f, -0.25f, 0.0f, //V4

            //左侧矩形
            -0.25f, 0.75f, 0.0f, //V6
            -0.75f, 0.25f, 0.0f, //V1
            -0.75f, -0.75f, 0.0f, //V2
            -0.25f, 0.75f, 0.0f, //V6
            -0.75f, -0.75f, 0.0f, //V2
            -0.25f, -0.25f, 0.0f, //V7

            //底部矩形
            0.75f, -0.25f, 0.0f, //V4
            -0.25f, -0.25f, 0.0f, //V7
            -0.75f, -0.75f, 0.0f, //V2
            0.75f, -0.25f, 0.0f, //V4
            -0.75f, -0.75f, 0.0f, //V2
            0.25f, -0.75f, 0.0f, //V3

            //正面矩形
            0.25f, 0.25f, 0.0f,  //V0
            -0.75f, 0.25f, 0.0f, //V1
            -0.75f, -0.75f, 0.0f, //V2
            0.25f, 0.25f, 0.0f,  //V0
            -0.75f, -0.75f, 0.0f, //V2
            0.25f, -0.75f, 0.0f, //V3

            //右侧矩形
            0.75f, 0.75f, 0.0f, //V5
            0.25f, 0.25f, 0.0f, //V0
            0.25f, -0.75f, 0.0f, //V3
            0.75f, 0.75f, 0.0f, //V5
            0.25f, -0.75f, 0.0f, //V3
            0.75f, -0.25f, 0.0f, //V4

            //顶部矩形
            0.75f, 0.75f, 0.0f, //V5
            -0.25f, 0.75f, 0.0f, //V6
            -0.75f, 0.25f, 0.0f, //V1
            0.75f, 0.75f, 0.0f, //V5
            -0.75f, 0.25f, 0.0f, //V1
            0.25f, 0.25f, 0.0f  //V0
    };

    //立方体的顶点颜色
    private float[] colors = {
            //背面矩形颜色
            1f, 0f, 1f, 1f,
            1f, 0f, 1f, 1f,
            1f, 0f, 1f, 1f,
            1f, 0f, 1f, 1f,
            1f, 0f, 1f, 1f,
            1f, 0f, 1f, 1f,

            //左侧矩形颜色
            0f, 1f, 0f, 1f,
            0f, 1f, 0f, 1f,
            0f, 1f, 0f, 1f,
            0f, 1f, 0f, 1f,
            0f, 1f, 0f, 1f,
            0f, 1f, 0f, 1f,

            //底部矩形颜色
            1f, 0f, 0.5f, 1f,
            1f, 0f, 0.5f, 1f,
            1f, 0f, 0.5f, 1f,
            1f, 0f, 0.5f, 1f,
            1f, 0f, 0.5f, 1f,
            1f, 0f, 0.5f, 1f,

            //正面矩形颜色
            0.2f, 0.3f, 0.2f, 1f,
            0.2f, 0.3f, 0.2f, 1f,
            0.2f, 0.3f, 0.2f, 1f,
            0.2f, 0.3f, 0.2f, 1f,
            0.2f, 0.3f, 0.2f, 1f,
            0.2f, 0.3f, 0.2f, 1f,

            //右侧矩形颜色
            0.1f, 0.2f, 0.3f, 1f,
            0.1f, 0.2f, 0.3f, 1f,
            0.1f, 0.2f, 0.3f, 1f,
            0.1f, 0.2f, 0.3f, 1f,
            0.1f, 0.2f, 0.3f, 1f,
            0.1f, 0.2f, 0.3f, 1f,

            //顶部矩形颜色
            0.3f, 0.4f, 0.5f, 1f,
            0.3f, 0.4f, 0.5f, 1f,
            0.3f, 0.4f, 0.5f, 1f,
            0.3f, 0.4f, 0.5f, 1f,
            0.3f, 0.4f, 0.5f, 1f,
            0.3f, 0.4f, 0.5f, 1f
    };

    public ColorCubeRenderer(){
        vertexBuffer= ByteBuffer.allocateDirect(vertexPoints.length*4).order(ByteOrder.nativeOrder())
                .asFloatBuffer();

        vertexBuffer.put(vertexBuffer);
        vertexBuffer.position(0);

        colorBuffer=ByteBuffer.allocateDirect(colors.length*4).order(ByteOrder.nativeOrder()).asFloatBuffer();

        colorBuffer.put(colors);
        colorBuffer.position(0);
    }
}
