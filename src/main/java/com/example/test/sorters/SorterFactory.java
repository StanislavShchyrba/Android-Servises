package com.example.test.sorters;

import androidx.annotation.NonNull;

public class SorterFactory {

    @NonNull
    public static Sorter create(@NonNull Sorter.SortingMethod method) {
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
