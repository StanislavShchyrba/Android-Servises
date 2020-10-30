package com.example.testsortservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sortmanager.SortServiceManager;

public class TestSortService extends Service {
    static {
        System.loadLibrary("native-sort-lib");
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @NonNull
    private native int[] nativeTest(int testArraySize);

    SortServiceManager createSortServiceManager() {
       return new SortServiceManager(this);
    }

}
