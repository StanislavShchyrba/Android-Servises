#ifndef NATIVE_SORT_LIB_H
#define NATIVE_SORT_LIB_H


namespace customsorters {

    extern "C"
    JNIEXPORT jintArray JNICALL
    Java_com_example_usage_SortService_nativeGenerate(JNIEnv *env, jobject thiz, jint count);

    extern "C"
    JNIEXPORT jintArray JNICALL
    Java_com_example_usage_sorters_BubbleSorter_bubbleSort(JNIEnv *env, jobject thiz,
                                                           jintArray array);

    extern "C"
    JNIEXPORT jintArray JNICALL
    Java_com_example_usage_sorters_CountingSorter_countingSort(JNIEnv *env, jobject thiz,
                                                               jintArray array);
    extern "C"
    JNIEXPORT jintArray JNICALL
    Java_com_example_usage_sorters_QuickSorter_quickSort(JNIEnv *env, jobject thiz,
                                                         jintArray array);

    int partition(jint arr[], int low, int high);// wrappers and additional funcs for sorting

    void quickSort(jint arr[], int low, int high);

    void bubbleSort(jint arr[], int size);

    void countingSort(jint arr[], int size);
}
#endif //NATIVE_SORT_LIB_H
