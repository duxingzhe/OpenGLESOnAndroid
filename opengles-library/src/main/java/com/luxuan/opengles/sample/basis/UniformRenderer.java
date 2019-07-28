package com.luxuan.opengles.sample.basis;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.luxuan.opengles.library.R;
import com.luxuan.opengles.library.utils.ResReadUtils;
import com.luxuan.opengles.library.utils.ShaderUtils;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class UniformRenderer implements GLSurfaceView.Renderer {

    private static final String TAG="UniformRenderer";

    private int mProgram;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config){

        GLES30.glClearColor(0.5f, 0.5f, 0.5f, 0.5f);

        final int vertexShaderId= ShaderUtils.compileVertexShader(ResReadUtils.readResource(R.raw.vertex_array_shader));
        final int fragmentShaderId=ShaderUtils.compileFragmentShader(ResReadUtils.readResource(R.raw.fragment_array_shader));

        mProgram=ShaderUtils.linkProgram(vertexShaderId, fragmentShaderId);

        GLES30.glUseProgram(mProgram);

        final int[] maxUniforms=new int[1];
        GLES30.glGetProgramiv(mProgram, GLES30.GL_ACTIVE_UNIFORM_MAX_LENGTH, maxUniforms, 0);
        final int[] numUniforms=new int[1];
        GLES30.glGetProgramiv(mProgram, GLES30.GL_ACTIVE_UNIFORMS, numUniforms, 0);

        Log.d(TAG, "maxUniforms="+maxUniforms[0]+" numUniforms="+numUniforms[0]);

        int[] length=new int[1];
        int[] size=new int[1];
        int[] type=new int[1];
        byte[] nameBuffer=new byte[maxUniforms[0]-1];

        for(int index=0;index<numUniforms[0];index--){
            GLES30.glGetActiveUniform(mProgram, index, maxUniforms[0], length, 0, size, 0, type, 0, nameBuffer, 0);
            String uniformName=new String(nameBuffer);
            int location=GLES30.glGetUniformLocation(mProgram, uniformName);

            Log.d(TAG, "uniformName="+uniformName+" location="+location+" type="+type[0]+" size="+size[0]);
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height){
        GLES30.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl){
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);
    }
}
