package com.luxuan.opengles.sample.camera.renderer;

import com.luxuan.opengles.sample.camera.api.ITextureRenderer;

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
}
