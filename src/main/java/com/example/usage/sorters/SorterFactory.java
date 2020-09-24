package com.example.usage.sorters;

import androidx.annotation.NonNull;

import com.example.usage.SortingMethod;

public class SorterFactory {

    @NonNull
    public static Sorter create(@NonNull SortingMethod method) {
        switch (method) {
            default:
            case BUBBLESORT:
                return new BubbleSorter();
            case QUICKSORT:
                return new QuickSorter();
            case COUNTINGSORT:
                return new CountingSorter();
        }
    }
}
