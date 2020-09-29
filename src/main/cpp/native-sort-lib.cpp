#include <jni.h>
#include <cstdlib>
#include <cstring>
#include <android/log.h>

#include "generate.h"
#include "bubblesort.h"
#include "countingsort.h"
#include "quicksort.h"

#define TAG "Native code log"

using namespace native_layer_sorter;

namespace jni_layer_sorter {

    extern "C"
    JNIEXPORT jintArray JNICALL
    Java_com_example_sortmanager_SortService_nativeGenerate(JNIEnv *env, jobject thiz, jint count) {
        jintArray result = env->NewIntArray(count);

        if (result == NULL) {
            __android_log_print(ANDROID_LOG_ERROR, TAG, "nativeGenerate out of memory");
            return NULL;
        }

        jint *fill = env->GetIntArrayElements(result, 0);

        generate(static_cast<int *>(fill), static_cast<int>(count));

        env->SetIntArrayRegion(result, 0, count, fill);

        return result;
    }

    extern "C"
    JNIEXPORT jintArray JNICALL

    Java_com_example_sorters_BubbleSorter_bubbleSort(JNIEnv *env, jobject thiz,
                                                                jintArray array) {
        jint *arr = env->GetIntArrayElements(array, 0);
        int len = env->GetArrayLength(array);

        bubbleSort(static_cast<int *>(arr), len);

        env->ReleaseIntArrayElements(array, arr, JNI_COMMIT);

        return array;
    }

    extern "C"
    JNIEXPORT jintArray JNICALL
    Java_com_example_sorters_QuickSorter_quickSort(JNIEnv *env, jobject thiz,
                                                              jintArray array) {
        jint *arr = env->GetIntArrayElements(array, 0);
        int len = env->GetArrayLength(array);

        quickSort(static_cast<int *>(arr), 0, len - 1);

        env->ReleaseIntArrayElements(array, arr, JNI_COMMIT);

        return array;
    }

    extern "C"
    JNIEXPORT jintArray JNICALL
    Java_com_example_sorters_CountingSorter_countingSort(JNIEnv *env, jobject thiz,
                                                                    jintArray array) {
        jint *arr = env->GetIntArrayElements(array, 0);
        int len = env->GetArrayLength(array);

        countingSort(static_cast<int *>(arr), len);

        env->ReleaseIntArrayElements(array, arr, JNI_COMMIT);

        return array;
    }
}

