package com.luxuan.opengles.sample.basis;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import com.luxuan.opengles.library.utils.ShaderUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

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

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config){
        GLES30.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);

        final int vertexShaderId= ShaderUtils.compileVertexShader(vertextShader);
        final int fragmentShaderId=ShaderUtils.compileVertexShader(fragmentShader);

        mProgram=ShaderUtils.linkProgram(vertexShaderId, fragmentShaderId);

        GLES30.glUseProgram(mProgram);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height){
        GLES30.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl){
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);

        GLES30.glVertexAttribPointer(0, POSITION_COMPONENT_COUNT, GLES30.GL_FLOAT, false, 0, vertexBuffer);

        GLES30.glEnableVertexAttribArray(0);

        GLES30.glEnableVertexAttribArray(1);
        GLES30.glVertexAttribPointer(1,4,GLES30.GL_FLOAT, false, 0, colorBuffer);

        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 3);

        GLES30.glDisableVertexAttribArray(0);
        GLES30.glDisableVertexAttribArray(1);
    }
}
