#include <algorithm>
#include"bubblesort.h"

namespace native_sorter {
    void bubbleSort(int arr[], int size) {
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
}