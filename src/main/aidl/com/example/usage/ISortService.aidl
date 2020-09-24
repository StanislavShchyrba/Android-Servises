package com.example.usage;

import com.example.usage.SortingMethod;

interface ISortService  {
    int[] generate(in int count);
    int[] sort(inout int[] array, in SortingMethod method);
}
