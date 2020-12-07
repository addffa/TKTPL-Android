//
// Created by ADLI DAFFA on 12/7/2020.
//

#include <jni.h>
#include <string>

extern "C" JNIEXPORT jboolean
Java_id_ac_ui_cs_mobileprogramming_activityplanner_MainActivity_isPrime (
        JNIEnv* env,
        jobject /* this */,
        jint n
        ) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }

        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }

        for (int i = 5; i * i <= n; i = i + 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }

        return true;
}
