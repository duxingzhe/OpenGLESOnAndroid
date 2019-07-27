package com.luxuan.opengles.sample.basis;


import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import com.luxuan.opengles.library.R;
import com.luxuan.opengles.library.utils.ResReadUtils;
import com.luxuan.opengles.library.utils.ShaderUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

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

    public IndicesCubeRenderer(){
        vertexBuffer = ByteBuffer.allocateDirect(vertexPoints.length*4).order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexBuffer.put(vertexPoints);
        vertexBuffer.position(0);

        colorBuffer=ByteBuffer.allocateDirect(colors.length*4).order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        colorBuffer.put(colors);
        colorBuffer.position(0);

        indicesBuffer=ByteBuffer.allocateDirect(indices.length*4).order(ByteOrder.nativeOrder())
                .asShortBuffer();
        indicesBuffer.put(indices);
        indicesBuffer.position(0);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config){
        GLES30.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);

        final int vertexShaderId= ShaderUtils.compileVertexShader(ResReadUtils.readResource(R.raw.vertex_colorcube_shader));
        final int fragmentShaderId= ShaderUtils.compileVertexShader(ResReadUtils.readResource(R.raw.fragment_colorcube_shader));

        mProgram=ShaderUtils.linkProgram(vertexShaderId, fragmentShaderId);

        GLES30.glUseProgram(mProgram);

        GLES30.glVertexAttribPointer(0, VERTEX_POSITION_SIZE, GLES30.GL_FLOAT, false, 0, vertexBuffer);

        GLES30.glEnableVertexAttribArray(0);

        GLES30.glVertexAttribPointer(1, VERTEX_COLOR_SIZE, GLES30.GL_FLOAT, false, 0, colorBuffer);

        GLES30.glEnableVertexAttribArray(1);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height){
        GLES30.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl){
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);
        GLES30.glDrawElements(GL10.GL_TRIANGLES, indices.length, GL10.GL_UNSIGNED_SHORT, indicesBuffer);
    }
}
