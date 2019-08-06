//
// Created by Luxuan on 2019/8/7.
//

#include <jni.h>
#include <stdio.h>
#include <time.h>
#include <android/bitmap.h>
#include <android/native_window.h>
#include <android/native_window_jni.h>
#include <EGL/egl.h>
#include <GLES3/gl3.h>

#include "native_window.h"

JNINativeMethod methods[] = {
        {"drawColor",  "(Ljava/lang/Object;I)V",                  (void *) drawColor},
        {"drawBitmap", "(Ljava/lang/Object;Ljava/lang/Object;)V", (void *) drawBitmap},
};

jint registerNativeMethod(JNIEnv *env)
{
    jclass cl=env->FindClass("com/luxuan/opengles/sample/nativewindow/NativeWindowSample");
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

void ThrowException(JNIEnv *env, const char *exception, const char *message)
{
    jclass clazz=env->FindClass(exception);
    if(NULL!=clazz)
    {
        env->ThrowNew(clazz, message);
    }
}