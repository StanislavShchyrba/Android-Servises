#include <cstring>
#include "countingsort.h"

namespace native_layer_sorter {
    void countingSort(int arr[], int size) {
        int output[size];

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
