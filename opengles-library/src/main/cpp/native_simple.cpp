//
// Created by Luxuan on 2019/7/25.
//

#include <jni.h>
#include <EGL/egl.h>
#include <GLES3/gl3.h>
#include "native_simple.h"

#ifdef ANDROID

#include <android/log.h>

#define LOG_TAG "Hello Triangle"
#define LOGE(format, ...) __android_log_print(ANDROID_LOG_ERROR, LOG_TAG, format, ##__VA_ARGS__)
#define LOGI(format, ...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, format, ##__VA_ARGS__)
#else
#define LOGE(fomrat, ...) printf(LOG_TAG format "\n", ##__VA_ARGS__)
#define LOGI(format, ...) printf(LOG_TAG format "\n", ##__VA_ARGS__)
#endif


JNINativeMethod methods[]=
{
        {"surfaceCreated", "()V",   (void *) surfaceCreated},
        {"surfaceChanged", "(II)V", (void *) surfaceChanged},
        {"onDrawFrame",    "()V",   (void *) onDrawFrame}
};

jint registerNativeMethod(JNIEnv *env)
{
    jclass cl=env->FindClass("com/luxuan/opengles/sample/basis/NativeSimpleRenderer");
    if((env->RegisterNatives(cl, methods, sizeof(methods)/sizeof(methods[0])))<0)
    {
        return -1;
    }
    return 0;
}

jint JNI_OnLoad(JavaVM *vm, void *reserved)
{
    JNIEnv *env=NULL;
    if(vm->GetEnv((void **)&env, JNI_VERSION_1_6)!=JNI_OK)
    {
        return -1;
    }

    if(registerNativeMethod(env)!=JNI_OK)
    {
        return -1;
    }

    return JNI_VERSION_1_6;
}

const GLfloat TRIANGLE_VERTICES[] = {0.0f, 0.5f, 0.0f,
                                     -0.5f, -0.5f, 0.0f,
                                     0.5f, -0.5f, 0.0f};

const char VERTEX_SHADER[] =
        "#version 300 es \n"
        "layout (location = 0) in vec4 vPosition;\n"
        "layout (location = 1) in vec4 aColor;\n"
        "out vec4 vColor;\n"
        "void main() { \n"
        "gl_Position  = vPosition;\n"
        "gl_PointSize = 10.0;\n"
        "vColor = aColor;\n"
        "}\n";

const char FRAGMENT_SHADER[] =
        "#version 300 es \n"
        "precision mediump float;\n"
        "in vec4 vColor;\n"
        "out vec4 fragColor;\n"
        "void main() { \n"
        "fragColor = vColor;\n"
        "}\n";

float color[] = {
        0.0f, 1.0f, 0.0f, 1.0f,
        1.0f, 0.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f, 1.0f
};

GLuint CompileShader(GLenum type, const char *shaderCode)
{
    GLint shader=glCreateShader(type);
    if(shader!=0)
    {
        glShaderSource(shader, 1, &shaderCode, NULL);
        glCompileShader(shader);

        GLint glResult=GL_FALSE;
        glGetShaderiv(shader, GL_COMPILE_STATUS, &glResult);
        if(glResult==GL_FALSE)
        {
            glDeleteShader(shader);
            return 0;
        }

        return shader;
    }

    return 0;
}

GLint LinkProgram(GLint vertexShader, GLint fragmentShader)
{
    GLint program=glCreateProgram();
    if(program!=0)
    {
        glAttachShader(program, vertexShader);
        glAttachShader(program, fragmentShader);
        glLinkProgram(program);

        GLint linkStatus=GL_FALSE;
        glGetProgramiv(program, GL_LINK_STATUS, &linkStatus);
        if(linkStatus==GL_FALSE)
        {
            glDeleteProgram(program);
            return 0;
        }
    }

    return 0;
}

JNIEXPORT void JNICALL surfaceCreated(JNIEnv *env, jobject obj)
{
    GLint vertexShader= CompileShader(GL_VERTEX_SHADER, VERTEX_SHADER);
    if(vertexShader==0)
    {
        LOGE("loadVertexShader Failed");
        return;
    }

    GLint fragmentShader=CompileShader(GL_FRAGMENT_SHADER, FRAGMENT_SHADER);
    if(fragmentShader==0)
    {
        LOGE("loadFragmentShader Failed");
        return;
    }

    GLint program=LinkProgram(vertexShader, fragmentShader);

    glUseProgram(program);

    glClearColor(0.5f, 0.5f, 0.5f, 0.5f);
}

JNIEXPORT void JNICALL surfaceChanged(JNIEnv *env, jobject obj, jint width, jint height)
{
    glViewport(0, 0, width, height);
}

JNIEXPORT void JNICALL onDrawFrame(JNIEnv *env, jobject obj)
{
    glClear(GL_COLOR_BUFFER_BIT);

    glVertexAttribPointer(0,3, GL_FLOAT, GL_FALSE, 0, TRIANGLE_VERTICES);

    glEnableVertexAttribArray(0);

    glEnableVertexAttribArray(1);
    glVertexAttribPointer(1,4,GL_FLOAT, false, 0, color);

    glDrawArrays(GL_TRIANGLES, 0, 3);

    glDisableVertexAttribArray(0);
    glDisableVertexAttribArray(1);
}
