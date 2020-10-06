package com.example.sortmanager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.NonNull;

import com.example.sorters.Sorter;
import com.example.sorters.SorterFactory;
import com.example.sortingapp.ISortService;
import com.example.sortingapp.SortingMethod;

public class SortService extends Service {
    static {
        System.loadLibrary("native-sort-lib");
    }

    @NonNull
    @Override
    public IBinder onBind(Intent intent) {
        return mSortBinder;
    }

    private final ISortService.Stub mSortBinder = new ISortService.Stub() {
        @NonNull
        @Override
        public int[] generate(int count) {
            return SortService.this.nativeGenerate(count);
        }

        @NonNull
        public int[] sort(@NonNull int[] array, @NonNull SortingMethod method) {
            return SortService.this.sort(array, method);
        }
    };

    @NonNull
    private int[] sort(@NonNull int[] array, @NonNull SortingMethod method) {
        Sorter sorter = SorterFactory.create(method);
        return sorter.sort(array);
    }

    @NonNull
    private native int[] nativeGenerate(int count);
}
