package com.example.test;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.test.sorters.Sorter;
import com.example.test.sorters.SorterFactory;

public class MyService extends Service {
    static {
        System.loadLibrary("native-lib");
    }

    private final IBinder mBinder = new MyBinder();

    private native int[] nativeGenerate(int count);

    private int[] sort(int[] array, Sorter.SortingMethod method) {
        Sorter sorter = SorterFactory.create(method);
        return sorter.sort(array);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();


    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class MyBinder extends Binder {

        public int[] sort(int[] array, Sorter.SortingMethod method) {
            return MyService.this.sort(array, method);
        }

        public int[] generate(int count) {
            return MyService.this.nativeGenerate(count);
        }


        MyService getService() {
            return MyService.this;
        }

        MyBinder getBinder() {
            return MyBinder.this;
        }
    }
}
