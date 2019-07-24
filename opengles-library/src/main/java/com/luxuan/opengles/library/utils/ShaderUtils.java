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
                System.err.println(logInfo);

                GLES30.glDeleteShader(shaderId);
                return 0;
            }
            return shaderId;
        }else{
            return 0;
        }
    }

    public static int linkProgram(int vertexShaderId, int fragmentShaderId){
        final int programId=GLES30.glCreateProgram();
        if(programId!=0){
            GLES30.glAttachShader(programId, vertexShaderId);
            GLES30.glAttachShader(programId, fragmentShaderId);
            GLES30.glLinkProgram(programId);
            final int[] linkStatus=new int[1];

            GLES30.glGetProgramiv(programId, GLES30.GL_LINK_STATUS, linkStatus, 0);
            if(linkStatus[0]==0){
                String logInfo=GLES30.glGetProgramInfoLog(programId);
                System.err.println(logInfo);
                GLES30.glDeleteProgram(programId);
                return 0;
            }
            return programId;
        }else{
            return 0;
        }
    }

    public static boolean validProgram(int programObjectId){
        GLES30.glValidateProgram(programObjectId);
        final int[] programStatus=new int[1];
        GLES30.glGetProgramiv(programObjectId, GLES30.GL_VALIDATE_STATUS, programStatus, 0);
        return programStatus[0]!=0;
    }
}
