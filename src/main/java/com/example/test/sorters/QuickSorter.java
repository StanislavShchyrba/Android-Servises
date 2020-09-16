package com.example.test.sorters;

public class QuickSorter implements Sorter {

    @Override
    public int[] sort(int[] array) {
        return quickSort(array);
    }

    private native int[] quickSort(int[] array);
}
