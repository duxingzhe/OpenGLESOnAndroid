package com.luxuan.opengles.sample.basis;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import com.luxuan.opengles.library.utils.ShaderUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class RectangleRenderer implements GLSurfaceView.Renderer {

    private final FloatBuffer vertexBuffer;

    private int mProgram;

    private static final int POSITION_COMPONENT_COUNT=2;
    private static final int COLOR_COMPONENT_COUNT=3;

    private static final int BYTES_PER_FLOAT=4;

    private static final int STRIDE=
            (POSITION_COMPONENT_COUNT+COLOR_COMPONENT_COUNT)*BYTES_PER_FLOAT;

    /**
     * 点的坐标
     */
    private float[] vertexPoints = new float[]{
            0.0f, 0.0f, 1.0f, 1.0f, 1.0f,
            -0.5f, -0.8f, 1.0f, 1.0f, 1.0f,
            0.5f, -0.8f, 1.0f, 1.0f, 1.0f,
            0.5f, 0.8f, 1.0f, 1.0f, 1.0f,
            -0.5f, 0.8f, 1.0f, 1.0f, 1.0f,
            -0.5f, -0.8f, 1.0f, 1.0f, 1.0f,

            0.0f, 0.25f, 0.5f, 0.5f, 0.5f,
            0.0f, -0.25f, 0.5f, 0.5f, 0.5f,
    };

    private final float[] mMatrix = new float[16];

    /**
     * 顶点着色器
     */
    private String vertextShader =
            "#version 300 es \n" +
                    "layout (location = 0) in vec4 vPosition;\n"
                    + "layout (location = 1) in vec4 aColor;\n"
                    + "uniform mat4 u_Matrix;\n"
                    + "out vec4 vColor;\n"
                    + "void main() { \n"
                    + "gl_Position  = u_Matrix * vPosition;\n"
                    + "gl_PointSize = 10.0;\n"
                    + "vColor = aColor;\n"
                    + "}\n";

    private String fragmentShader =
            "#version 300 es \n" +
                    "precision mediump float;\n"
                    + "in vec4 vColor;\n"
                    + "out vec4 fragColor;\n"
                    + "void main() { \n"
                    + "fragColor = vColor;\n"
                    + "}\n";

    private int uMatrixLocation;
    private int aPositionLocation;
    private int aColorLocation;

    public RectangleRenderer(){
        vertexBuffer= ByteBuffer.allocateDirect(vertexPoints.length*4).order(ByteOrder.nativeOrder())
                .asFloatBuffer();

        vertexBuffer.put(vertexPoints);
        vertexBuffer.position(0);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config){
        GLES30.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);

        final int vertexShaderId= ShaderUtils.compileVertexShader(vertextShader);
        final int fragmentShaderId=ShaderUtils.compileFragmentShader(fragmentShader);

        mProgram=ShaderUtils.linkProgram(vertexShaderId, fragmentShaderId);

        GLES30.glUseProgram(mProgram);

        uMatrixLocation=GLES30.glGetUniformLocation(mProgram, "u_Matrix");

        aPositionLocation=GLES30.glGetAttribLocation(mProgram, "vPosition");
        aColorLocation=GLES30.glGetAttribLocation(mProgram, "vPosition");

        vertexBuffer.position(0);

        GLES30.glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GLES30.GL_FLOAT, false, STRIDE, vertexBuffer);
        GLES30.glEnableVertexAttribArray(aPositionLocation);

        vertexBuffer.position(POSITION_COMPONENT_COUNT);
        GLES30.glVertexAttribPointer(aColorLocation, COLOR_COMPONENT_COUNT, GLES30.GL_FLOAT, false, STRIDE, vertexBuffer);
        GLES30.glEnableVertexAttribArray(aColorLocation);

    }
}
