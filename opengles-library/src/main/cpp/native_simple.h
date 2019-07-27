//
// Created by Luxuan on 2019/7/25.
//

#include <jni.h>

#ifndef OPENGLESONANDROID_NATIVE_SIMPLE_H
#define OPENGLESONANDROID_NATIVE_SIMPLE_H

#ifdef __cplusplus
extern "C"
{
#endif

JNIEXPORT void JNICALL surfaceCreated(JNIEnv *, jobject);
JNIEXPORT void JNICALL surfaceChanged(JNIEnv *, jobject, jint, jint);
JNIEXPORT void JNICALL onDrawFrame(JNIEnv *, jobject);

#ifdef __cplusplus
};
#endif
#endif //OPENGLESONANDROID_NATIVE_SIMPLE_H
