#include <cstdlib>

#include "generate.h"

namespace native_sorter {
    void generate(int arr[], int size) {
        for (int i = 0; i < size; i++) {
            arr[i] = rand() % 100 + 1;
        }
    }
}
