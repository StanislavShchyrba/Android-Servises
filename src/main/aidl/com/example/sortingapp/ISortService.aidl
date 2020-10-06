package com.example.sortingapp;

import com.example.sortingapp.SortingMethod;

interface ISortService  {
    int[] generate(in int count);
    int[] sort(in int[] array, in SortingMethod method);
}
