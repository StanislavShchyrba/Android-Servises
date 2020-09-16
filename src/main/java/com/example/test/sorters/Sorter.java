package com.example.test.sorters;

import com.example.test.R;

public interface Sorter {

    int[] sort(int[] array);

    enum SortingMethod { // binded with strings.xml for changes
        BUBBLESORT {
            @Override
            public int getCaptionStringId() {
                return R.string.bubble_sort;
            }
        },
        QUICKSORT {
            @Override
            public int getCaptionStringId() {
                return R.string.quicksort;
            }
        },
        COUNTINGSORT {
            @Override
            public int getCaptionStringId() {
                return R.string.counting_sort;
            }
        };

        public int getCaptionStringId() {
            return 0;
        }
    }
}
