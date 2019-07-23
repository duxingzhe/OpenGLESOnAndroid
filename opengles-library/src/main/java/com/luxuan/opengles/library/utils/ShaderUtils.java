package com.luxuan.opengles.library.utils;

import android.opengl.GLES30;

public class ShaderUtils {

    private static final String TAG="ShaderUtils";

    public static int compileVertexShader(String shaderCode){
        return compilerShader(GLES30.GL_VERTEX_SHADER,shaderCode);
    }

    public static int compileFragmentShader(String shaderCode){
        return compilerShader(GLES30.GL_FRAGMENT_SHADER, shaderCode);
    }

    private static int compilerShader(int type, String shaderCode){
        final int shaderId=GLES30.glCreateShader(type);
        if(shaderId!=0){
            GLES30.glShaderSource(shaderId, shaderCode);
            GLES30.glCompileShader(shaderId);

            final int[] compileStatus=new int[1];
            GLES30.glGetShaderiv(shaderId, GLES30.GL_COMPILE_STATUS, compileStatus, 0);
            if(compileStatus[0]==0){
                String logInfo=GLES30.glGetShaderInfoLog(shaderId);
                System.err.println(shaderId);

                GLES30.glDeleteShader(shaderId);
                return 0;
            }
            return shaderId;
        }else{
            return 0;
        }
    }
}
