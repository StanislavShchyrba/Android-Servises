package com.example.usage.sorters;

import androidx.annotation.NonNull;

public class BubbleSorter implements Sorter {

    @NonNull
    @Override
    public int[] sort(@NonNull int[] array) {
        return bubbleSort(array);
    }

    @NonNull
    private native int[] bubbleSort(@NonNull int[] array);
}
