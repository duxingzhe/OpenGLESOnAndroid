package com.luxuan.opengles.sample.texture;

import android.opengl.GLSurfaceView;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public class TextureRenderer implements GLSurfaceView.Renderer {

    private static final String TAG="TextureRenderer";

    private final FloatBuffer vertexBuffer, mTextureVertexBuffer;

    private final ShortBuffer mVertexIndexBuffer;

    private int mProgram;

    private int textureId;

    /**
     * 顶点坐标
     * (x,y,z)
     */
    private float[] POSITION_VERTEX = new float[]{
            0f, 0f, 0f,     //顶点坐标V0
            1f, 1f, 0f,     //顶点坐标V1
            -1f, 1f, 0f,    //顶点坐标V2
            -1f, -1f, 0f,   //顶点坐标V3
            1f, -1f, 0f     //顶点坐标V4
    };

    /**
     * 纹理坐标
     * (s,t)
     */
    private static final float[] TEX_VERTEX = {
            0.5f, 0.5f, //纹理坐标V0
            1f, 0f,     //纹理坐标V1
            0f, 0f,     //纹理坐标V2
            0f, 1.0f,   //纹理坐标V3
            1f, 1.0f    //纹理坐标V4
    };

    /**
     * 索引
     */
    private static final short[] VERTEX_INDEX = {
            0, 1, 2,  //V0,V1,V2 三个顶点组成一个三角形
            0, 2, 3,  //V0,V2,V3 三个顶点组成一个三角形
            0, 3, 4,  //V0,V3,V4 三个顶点组成一个三角形
            0, 4, 1   //V0,V4,V1 三个顶点组成一个三角形
    };

    private int uMatrixLocation;

    private float[] mMatrix=new float[16];

    public TextureRenderer(){
        vertexBuffer= ByteBuffer.allocateDirect(POSITION_VERTEX.length*4).order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexBuffer.put(POSITION_VERTEX);
        vertexBuffer.position(0);

        mTextureVertexBuffer=ByteBuffer.allocateDirect(TEX_VERTEX.length*4).order(ByteOrder.nativeOrder())
                .asFloatBuffer().put(TEX_VERTEX);
        mTextureVertexBuffer.position(0);

        mVertexIndexBuffer=ByteBuffer.allocateDirect(VERTEX_INDEX.length*4).order(ByteOrder.nativeOrder())
                .asShortBuffer().put(VERTEX_INDEX);
        mVertexIndexBuffer.position(0);
    }
}
