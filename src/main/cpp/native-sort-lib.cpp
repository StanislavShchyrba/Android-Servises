#include <jni.h>
#include <cstdlib>
#include <cstring>
#include <android/log.h>
#include "native-sort-lib.h"

#define TAG "Native code log"

namespace customsorters {

    extern "C"
    JNIEXPORT jintArray JNICALL
    Java_com_example_usage_SortService_nativeGenerate(JNIEnv *env, jobject thiz, jint count) {

        jintArray result = env->NewIntArray(count);

        if (result == NULL) {
            __android_log_print(ANDROID_LOG_ERROR,TAG,"nativeGenerate out of memory");
            return NULL;
        }

        jint *fill = env->GetIntArrayElements(result, 0);
        for (int i = 0; i < count; i++) {
            fill[i] = rand() % 101;
        }

        env->SetIntArrayRegion(result, 0, count, fill);

        return result;
    }

    extern "C"
    JNIEXPORT jintArray JNICALL
    Java_com_example_usage_sorters_BubbleSorter_bubbleSort(JNIEnv *env, jobject thiz,
                                                           jintArray array) {
        jint *arr = env->GetIntArrayElements(array, 0);
        int len = env->GetArrayLength(array);

        bubbleSort(arr, len);

        env->ReleaseIntArrayElements(array, arr, JNI_COMMIT);

        return array;
    }

    extern "C"
    JNIEXPORT jintArray JNICALL
    Java_com_example_usage_sorters_QuickSorter_quickSort(JNIEnv *env, jobject thiz,
                                                         jintArray array) {

        jint *arr = env->GetIntArrayElements(array, 0);
        int len = env->GetArrayLength(array);

        quickSort(arr, 0, len - 1);

        env->ReleaseIntArrayElements(array, arr, JNI_COMMIT);

        return array;
    }

    extern "C"
    JNIEXPORT jintArray JNICALL
    Java_com_example_usage_sorters_CountingSorter_countingSort(JNIEnv *env, jobject thiz,
                                                               jintArray array) {

        jint *arr = env->GetIntArrayElements(array, 0);
        int len = env->GetArrayLength(array);

        countingSort(arr, len);

        env->ReleaseIntArrayElements(array, arr, JNI_COMMIT);

        return array;
    }

    int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            if (arr[j] < pivot) {
                ++i;
                std::swap(arr[i], arr[j]);
            }
        }
        std::swap(arr[i + 1], arr[high]);
        return (i + 1);
    }


    void quickSort(jint arr[], int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    void bubbleSort(jint arr[], int size) {
        int i, j;
        for (i = 0; i < size - 1; i++) {
            // Last i elements are already in place
            for (j = 0; j < size - i - 1; j++) {

                if (arr[j] > arr[j + 1]) {
                    std::swap(arr[j], arr[j + 1]);
                }
            }
        }
    }

    void countingSort(jint arr[], int size) {
        jint output[size];

        int count[size + 1], i;
        memset(count, 0, sizeof(count));

        for (i = 0; arr[i]; ++i) {
            ++count[arr[i]];
        }

        for (i = 1; i <= size + 1; ++i) {
            count[i] += count[i - 1];
        }

        for (i = 0; arr[i]; ++i) {
            output[count[arr[i]] - 1] = arr[i];
            --count[arr[i]];
        }

        for (i = 0; arr[i]; ++i) {
            arr[i] = output[i];
        }
    }
}

