package com.example.sorters;

import androidx.annotation.NonNull;

public class QuickSorter implements Sorter {
    @NonNull
    @Override
    public int[] sort(@NonNull int[] array) {
        return quickSort(array);
    }

    @NonNull
    private native int[] quickSort(@NonNull int[] array);
}
