package com.luxuan.opengles.sample.basis;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import com.luxuan.opengles.library.R;
import com.luxuan.opengles.library.utils.ResReadUtils;
import com.luxuan.opengles.library.utils.ShaderUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MapBufferRenderer implements GLSurfaceView.Renderer {

    private static final String TAG="VertexBufferRenderer";

    private static final int VERTEX_POS_INDEX=0;

    private static final int VERTEX_POS_SIZE=3;

    private static final int VERTEX_STRIDE=VERTEX_POS_SIZE * 4;

    private int mProgram;

    /**
     * 点的坐标
     */
    private float[] vertexPoints = new float[]{
            0.0f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f
    };

    private int[] vboIds=new int[1];

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config){
        GLES30.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);

        final int vertexShaderId=ShaderUtils.compileVertexShader(ResReadUtils.readResource(R.raw.vertex_map_buffer_shader));
        final int fragmentShaderId= ShaderUtils.compileFragmentShader(ResReadUtils.readResource(R.raw.fragment_map_buffer_shader));

        mProgram=ShaderUtils.linkProgram(vertexShaderId, fragmentShaderId);

        GLES30.glGenBuffers(1, vboIds, 0);

        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, vboIds[0]);
        GLES30.glBufferData(GLES30.GL_ARRAY_BUFFER, vertexPoints.length *4, null, GLES30.GL_STATIC_DRAW);

        ByteBuffer buffer=(ByteBuffer)GLES30.glMapBufferRange(GLES30.GL_ARRAY_BUFFER, 0, vertexPoints.length * 4, GLES30.GL_MAP_WRITE_BIT|GLES30.GL_MAP_INVALIDATE_BUFFER_BIT);

        buffer.order(ByteOrder.nativeOrder()).asFloatBuffer().put(vertexPoints).position(0);

        GLES30.glVertexAttribPointer(VERTEX_POS_INDEX, VERTEX_POS_SIZE, GLES30.GL_FLOAT, false, VERTEX_STRIDE, 0);
        GLES30.glEnableVertexAttribArray(VERTEX_POS_INDEX);

        GLES30.glUnmapBuffer(GLES30.GL_ARRAY_BUFFER);

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height){
        GLES30.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl){
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);

        GLES30.glUseProgram(mProgram);

        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, 3);

        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER, 0);
    }
}
