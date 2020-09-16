package com.example.test.sorters;

public class BubbleSorter implements Sorter {

    @Override
    public int[] sort(int[] array) {
        return bubbleSort(array);
    }

    private native int[] bubbleSort(int[] array);
}
