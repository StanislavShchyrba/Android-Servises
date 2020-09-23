package com.example.usage;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.NonNull;

import com.example.usage.sorters.Sorter;
import com.example.usage.sorters.SorterFactory;

public class SortService extends Service {
    static {
        System.loadLibrary("native-sort-lib");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
            return SortService.this.nativeSort(array, method);
        }
    };

    @NonNull
    private native int[] nativeGenerate(int count);

    @NonNull
    private int[] nativeSort(@NonNull int[] array, @NonNull SortingMethod method) {
        Sorter sorter = SorterFactory.create(method);
        return sorter.sort(array);
    }
}
