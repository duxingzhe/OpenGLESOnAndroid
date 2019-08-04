package com.luxuan.opengles.sample.filter.filter;

import android.opengl.GLES30;

import com.luxuan.opengles.library.R;
import com.luxuan.opengles.library.core.AppCore;
import com.luxuan.opengles.library.utils.LogUtils;
import com.luxuan.opengles.library.utils.ResReadUtils;
import com.luxuan.opengles.library.utils.ShaderUtils;
import com.luxuan.opengles.library.utils.TextureUtils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

public abstract class BaseFilter implements RendererFilter {

    private static final String TAG="RendererFilter";

    private FloatBuffer vertexBuffer, mTextVertexBuffer;

    private ShortBuffer mVertexIndexBuffer;

    protected int mProgram;

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

    private float[] Matrix=new float[16];

    private String mVertexShader;

    private String mFragmentShader;

    public BaseFilter(){
        this(ResReadUtils.readResource(R.raw.no_filter_vertex_shader), ResReadUtils.readResource(R.raw.no_filter_fragment_shader));
    }

    public BaseFilter(final String vertexShader, final String fragmentShader){
        mVertexShader=vertexShader;
        mFragmentShader=fragmentShader;

        setupBuffer();
    }

    private void setupBuffer(){

        vertexBuffer= ByteBuffer.allocateDirect(POSITION_VERTEX.length *4 ).order(ByteOrder.nativeOrder())
                .asFloatBuffer();
        vertexBuffer.put(POSITION_VERTEX);
        vertexBuffer.position(0);

        mTextVertexBuffer=ByteBuffer.allocateDirect(TEX_VERTEX.length * 4).order(ByteOrder.nativeOrder())
                .asFloatBuffer().put(TEX_VERTEX);
        mTextVertexBuffer.position(0);

        mVertexIndexBuffer=ByteBuffer.allocateDirect(VERTEX_INDEX.length *4 ).order(ByteOrder.nativeOrder())
                .asShortBuffer().put(VERTEX_INDEX);
        mVertexIndexBuffer.position(0);
    }

    public void setupProgram(){

        final int vertexShaderId= ShaderUtils.compileVertexShader(mVertexShader);
        final int fragmentShaderId=ShaderUtils.compileFragmentShader(mFragmentShader);

        mProgram=ShaderUtils.linkProgram(vertexShaderId, fragmentShaderId);
        uMatrixLocation= GLES30.glGetUniformLocation(mProgram, "u_Matrix");

        textureId= TextureUtils.loadTexture(AppCore.getInstance().getContext(), R.drawable.main);

        LogUtils.d(TAG, "program=%d matrixLocation=%d textureId=%d", mProgram, uMatrixLocation, textureId);
    }

}
