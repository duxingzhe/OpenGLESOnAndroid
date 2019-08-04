//
// Created by Luxuan on 2019/8/4.
//

#ifndef OPENGLESONANDROID_NATIVE_COLOR_H
#define OPENGLESONANDROID_NATIVE_COLOR_H

#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif

JNIEXPORT void JNICALL surfaceCreated(JNIEnv *, jobject, jint);

JNIEXPORT void JNICALL surfaceChanged(JNIEnv *, jobject, jint, jint);

JNIEXPORT void JNICALL onDrawFrame(JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif

#endif //OPENGLESONANDROID_NATIVE_COLOR_H
