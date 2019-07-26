package com.luxuan.opengles.sample.basis;

import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class EnableVertexRenderer implements GLSurfaceView.Renderer{

    private final FloatBuffer vertexBuffer, colorBuffer;
    private int mProgram;

    /**
     * 点的坐标
     */
    private float[] vertexPoints = new float[]{
            0.0f, 0.5f, 0.0f,
            -0.5f, -0.5f, 0.0f,
            0.5f, -0.5f, 0.0f
    };

    private float[] vertexColors = new float[]{
            0.5f, 0.5f, 0.8f, 1.0f
    };

    public EnableVertexRenderer(){
        vertexBuffer= ByteBuffer.allocateDirect(vertexPoints.length*4).order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexBuffer.put(vertexPoints);
        vertexBuffer.position(0);

        colorBuffer=ByteBuffer.allocateDirect(vertexColors.length*4).order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        colorBuffer.put(vertexColors);
        colorBuffer.position(0);
    }
}
