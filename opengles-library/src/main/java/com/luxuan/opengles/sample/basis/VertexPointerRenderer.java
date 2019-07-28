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

public class VertexPointerRenderer implements GLSurfaceView.Renderer {

    private final FloatBuffer vertexBuffer;

    private int mProgram;

    private static final int VERTEX_POSITION_SIZE=2;

    private static final int VERTEX_COLOR_SIZE=3;

    private static final int BYTES_PER_FLOAT=4;

    private static final int VERTEX_ATTRIBUTES_SIZE=(VERTEX_POSITION_SIZE+VERTEX_COLOR_SIZE)*4;

    /**
     * 点的坐标
     */
    private float[] vertexPoints = new float[]{
            //前两个为坐标,后三个为颜色
            0.0f, 0.0f, 1.0f, 1.0f, 1.0f,
            -0.5f, -0.5f, 1.0f, 1.0f, 1.0f,
            0.5f, -0.5f, 1.0f, 1.0f, 1.0f,
            0.5f, 0.5f, 1.0f, 1.0f, 1.0f,
            -0.5f, 0.5f, 1.0f, 1.0f, 1.0f,
            -0.5f, -0.5f, 1.0f, 1.0f, 1.0f,
            //两个点的顶点属性
            0.0f, 0.25f, 0.5f, 0.5f, 0.5f,
            0.0f, -0.25f, 0.5f, 0.5f, 0.5f,
    };

    public VertexPointerRenderer(){
        vertexBuffer= ByteBuffer.allocateDirect(vertexPoints.length * 4).order(ByteOrder.nativeOrder())
                .asFloatBuffer();

        vertexBuffer.put(vertexPoints);
        vertexBuffer.position(0);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config){
        GLES30.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);

        final int vertexShaderId= ShaderUtils.compileVertexShader(ResReadUtils.readResource(R.raw.vertex_pointer_shader));
        final int fragmentShaderId=ShaderUtils.compileFragmentShader(ResReadUtils.readResource(R.raw.fragment_pointer_shader));

        mProgram=ShaderUtils.linkProgram(vertexShaderId, fragmentShaderId);

        GLES30.glUseProgram(mProgram);

        vertexBuffer.position(0);
        GLES30.glVertexAttribPointer(0, VERTEX_POSITION_SIZE, GLES30.GL_FLOAT, false, VERTEX_ATTRIBUTES_SIZE, vertexBuffer);
        GLES30.glEnableVertexAttribArray(0);

        vertexBuffer.position(VERTEX_POSITION_SIZE);
        GLES30.glVertexAttribPointer(1, VERTEX_COLOR_SIZE, GLES30.GL_FLOAT, false, VERTEX_ATTRIBUTES_SIZE, vertexBuffer);

        GLES30.glEnableVertexAttribArray(1);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width ,int height){
        GLES30.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl){
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);

        GLES30.glDrawArrays(GLES30.GL_TRIANGLE_STRIP, 0, 0);

        GLES30.glDrawArrays(GLES30.GL_POINTS, 6, 2);
    }
}
