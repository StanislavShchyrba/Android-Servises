#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL

Java_com_example_myapplication_MyService_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env-ы>NewStringUTF(hello.c_str());
}
