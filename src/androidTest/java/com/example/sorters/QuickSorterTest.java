package com.example.sorters;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class QuickSorterTest {
    static {
        System.loadLibrary("native-sort-lib");
    }

    @Test
    public void quickSortTest() {
        int[] arr = {10, 11, 1, 2, 3};

        assertArrayEquals(new int[]{1, 2, 3, 10, 11}, new BubbleSorter().sort(arr));
    }

}