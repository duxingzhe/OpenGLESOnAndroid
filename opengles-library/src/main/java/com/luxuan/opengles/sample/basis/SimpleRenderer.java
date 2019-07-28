package com.luxuan.opengles.sample.basis;

import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class SimpleRenderer implements GLSurfaceView.Renderer {

    private static final int POSITION_COMPONENT_COUNT=3;

    private final FloatBuffer vertexBuffer;
    private final FloatBuffer colorBuffer;

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
     * 顶点着色器
     */
    private String vertextShader =
            "#version 300 es \n" +
                    "layout (location = 0) in vec4 vPosition;\n"
                    + "layout (location = 1) in vec4 aColor;\n"
                    + "out vec4 vColor;\n"
                    + "void main() { \n"
                    + "gl_Position  = vPosition;\n"
                    + "gl_PointSize = 10.0;\n"
                    + "vColor = aColor;\n"
                    + "}\n";

    private String fragmentShader =
            "#version 300 es \n" +
                    "precision mediump float;\n"
                    + "in vec4 vColor;\n"
                    + "out vec4 fragColor;\n"
                    + "void main() { \n"
                    + "fragColor = vColor; \n"
                    + "}\n";

    private float color[] = {
            0.0f, 1.0f, 0.0f, 1.0f,
            1.0f, 0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f, 1.0f
    };

    public SimpleRenderer(){
        vertexBuffer= ByteBuffer.allocateDirect(vertexPoints.length*4).order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexBuffer.put(vertexPoints);
        vertexBuffer.position(0);

        colorBuffer=ByteBuffer.allocateDirect(color.length*4).order(ByteOrder.nativeOrder())
                .asFloatBuffer();

        colorBuffer.put(color);
        colorBuffer.position(0);
    }
}
