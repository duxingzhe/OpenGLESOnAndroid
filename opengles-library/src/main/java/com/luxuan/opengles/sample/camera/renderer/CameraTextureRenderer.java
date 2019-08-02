package com.luxuan.opengles.sample.camera.renderer;

import android.opengl.GLES30;

import com.luxuan.opengles.library.R;
import com.luxuan.opengles.library.utils.ResReadUtils;
import com.luxuan.opengles.library.utils.ShaderUtils;
import com.luxuan.opengles.sample.camera.api.ITextureRenderer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class CameraTextureRenderer implements ITextureRenderer {

    private FloatBuffer mVertexBuffer;

    private int mOESTexture=-1;

    private int mShaderProgram=-1;

    private int aPositionLocation=-1;
    private int aTextureCoordLocation=-1;
    private int uTextureMatrixLocation=-1;
    private int uTextureSamplerLocation=-1;

    public static final String POSITION_ATTRIBUTE="aPosition";
    public static final String TEXTURE_COORD_ATTRIBUTE="aTextureCoord";
    public static final String TEXTURE_MATRIX_UNIFORM="uTextureMatrix";
    public static final String TEXTURE_SAMPLER_UNIFORM="uTextureSampler";

    private static final int POSITION_SIZE=2;
    private static final int TEXTURE_SIZE=2;
    private static final int STRIDE=(POSITION_SIZE+TEXTURE_SIZE)*4;

    /**
     * 前两个为顶点坐标
     * 后两个为纹理坐标
     */
    private static final float[] VERTEX_DATA = {
            1.0f, 1.0f, 1.0f, 1.0f,
            -1.0f, 1.0f, 0.0f, 1.0f,
            -1.0f, -1f, 0.0f, 0.0f,
            1.0f, 1.0f, 1.0f, 1.0f,
            -1.0f, -1.0f, 0f, 0.0f,
            1.0f, -1.0f, 1.0f, 0.0f
    };

    private float[] transformMatrix=new float[16];

    public CameraTextureRenderer(int OESTextureId){
        mOESTexture=OESTextureId;
        mVertexBuffer=loadVertexBuffer(VERTEX_DATA);
    }

    public FloatBuffer loadVertexBuffer(float[] vertexData){
        FloatBuffer buffer= ByteBuffer.allocateDirect(vertexData.length * 4).order(ByteOrder.nativeOrder())
                .asFloatBuffer();

        buffer.put(vertexData, 0, vertexData.length).position(0);
        return buffer;
    }

    @Override
    public void onSurfaceCreated(){
        final int vertexShader= ShaderUtils.compileVertexShader(ResReadUtils.readResource(R.raw.vertex_texture_shader));
        final int fragmentShader= ShaderUtils.compileFragmentShader(ResReadUtils.readResource(R.raw.fragment_texture_shader));

        mShaderProgram=ShaderUtils.linkProgram(vertexShader, fragmentShader);

        aPositionLocation= GLES30.glGetAttribLocation(mShaderProgram, CameraTextureRenderer.POSITION_ATTRIBUTE);
        aTextureCoordLocation= GLES30.glGetAttribLocation(mShaderProgram, CameraTextureRenderer.TEXTURE_COORD_ATTRIBUTE);
        uTextureMatrixLocation= GLES30.glGetUniformLocation(mShaderProgram, CameraTextureRenderer.TEXTURE_MATRIX_UNIFORM);
        uTextureSamplerLocation=GLES30.glGetUniformLocation(mShaderProgram, CameraTextureRenderer.TEXTURE_SAMPLER_UNIFORM);
    }
}
