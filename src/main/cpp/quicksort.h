#ifndef QUICKSORT_H
#define QUICKSORT_H

namespace native_layer_sorter {
    void quickSort(int arr[], int low, int high);//wrapper

    int partition(int arr[], int low, int high);
}

#endif //QUICKSORT_H
