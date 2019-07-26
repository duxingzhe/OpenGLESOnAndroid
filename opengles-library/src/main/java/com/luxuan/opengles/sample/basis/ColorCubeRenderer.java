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

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config){
        GLES30.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);

        final int vertexShaderId= ShaderUtils.compileVertexShader(ResReadUtils.readResource(R.raw.vertex_colorcube_shader));
        final int fragmentShaderId=ShaderUtils.compileFragmentShader(ResReadUtils.readResource(R.raw.fragment_colorcube_shader));

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
        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 36);
    }
}
