package com.luxuan.opengles.sample.basis;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import com.luxuan.opengles.library.R;
import com.luxuan.opengles.library.utils.ResReadUtils;
import com.luxuan.opengles.library.utils.ShaderUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class LineCubeRenderer implements GLSurfaceView.Renderer {

    private final FloatBuffer vertexBuffer;

    private int mProgram;

    private static final int POSITION_COMPONENT_COUNT=3;

    /**
     * 点的坐标
     */
    private float[] vertexPoints = new float[]{
            0.25f, 0.25f, 0.0f,  //V0
            -0.75f, 0.25f, 0.0f, //V1
            -0.75f, -0.75f, 0.0f, //V2
            0.25f, -0.75f, 0.0f, //V3

            0.75f, -0.25f, 0.0f, //V4
            0.75f, 0.75f, 0.0f, //V5
            -0.25f, 0.75f, 0.0f, //V6
            -0.25f, -0.25f, 0.0f, //V7

            -0.25f, 0.75f, 0.0f, //V6
            -0.75f, 0.25f, 0.0f, //V1

            0.75f, 0.75f, 0.0f, //V5
            0.25f, 0.25f, 0.0f, //V0

            -0.25f, -0.25f, 0.0f, //V7
            -0.75f, -0.75f, 0.0f, //V2

            0.75f, -0.25f, 0.0f, //V4
            0.25f, -0.75f, 0.0f //V3
    };

    public LineCubeRenderer(){

        vertexBuffer= ByteBuffer.allocateDirect(vertexPoints.length *4).order(ByteOrder.nativeOrder())
                .asFloatBuffer();

        vertexBuffer.put(vertexPoints);
        vertexBuffer.position(0);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config){

        GLES30.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);

        final int vertexShaderId= ShaderUtils.compileVertexShader(ResReadUtils.readResource(R.raw.vertex_linecube_shader));
        final int fragmentShaderId=ShaderUtils.compileFragmentShader(ResReadUtils.readResource(R.raw.fragment_linecube_shader));

        mProgram=ShaderUtils.linkProgram(vertexShaderId, fragmentShaderId);

        GLES30.glUseProgram(mProgram);
        GLES30.glVertexAttribPointer(0, POSITION_COMPONENT_COUNT, GLES30.GL_FLOAT, false, 0, vertexBuffer);
        GLES30.glEnableVertexAttribArray(0);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height){
        GLES30.glViewport(0,0,width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl){
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);

        GLES30.glLineWidth(5);
        GLES30.glDrawArrays(GLES30.GL_LINE_LOOP, 0, 4);
        GLES30.glDrawArrays(GLES30.GL_LINE_LOOP, 4, 4);
        GLES30.glDrawArrays(GLES30.GL_LINES, 8, 8);
    }
}
