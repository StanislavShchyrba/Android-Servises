package com.example.usage.sorters;

import androidx.annotation.NonNull;

public class CountingSorter implements Sorter {
    @NonNull
    @Override
    public int[] sort(@NonNull int[] array) {
        return countingSort(array);
    }

    @NonNull
    private native int[] countingSort(@NonNull int[] array);
}
