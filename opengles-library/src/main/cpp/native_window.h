//
// Created by Luxuan on 2019/8/7.
//
#include <jni.h>

#ifndef OPENGLESONANDROID_NATIVE_WINDOW_H
#define OPENGLESONANDROID_NATIVE_WINDOW_H
#ifdef __cplusplus
extern "C"
{
#endif

JNIEXPORT void JNICALL drawColor(JNIEnv * , jobject , jobject , jint ) ;
JNIEXPORT void JNICALL drawBitmap(JNIEnv * , jobject , jobject , jobject) ;

#ifdef __cplusplus
extern "C"
}
#endif

#endif //OPENGLESONANDROID_NATIVE_WINDOW_H
