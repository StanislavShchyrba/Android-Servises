package com.example.test.sorters;

public class CountingSorter implements Sorter {

    @Override
    public int[] sort(int[] array) {
        return countingSort(array);
    }

    private native int[] countingSort(int[] array);
}
