package com.example.sorters;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;


public class CountingSorterTest {
    static {
        System.loadLibrary("native-sort-lib");
    }

    @Test
    public void countingSortTest() {
        int[] arr = {5, 4, 1, 2, 3};

        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, new BubbleSorter().sort(arr));
    }

    @Test
    public void countingSortNegativeTest(){
        int[] arr = {10, 11, 1, 2, 3};

        Assert.assertThat(new int[]{1, 2, 3, 10, 11}, IsNot.not(IsEqual.equalTo(arr)));
    }
}