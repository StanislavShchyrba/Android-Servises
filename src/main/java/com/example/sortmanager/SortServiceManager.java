package com.example.sortmanager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.sortingapp.ISortService;
import com.example.sortingapp.SortingMethod;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class SortServiceManager {
    private static final long AWAIT_TIMEOUT_MILLS = 1000;

    private CountDownLatch mLatch = new CountDownLatch(1);
    private volatile ISortService mSortService = null;

    private Executor mCallbackExecutor = Executors.newSingleThreadExecutor();

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            SortServiceManager.this.onServiceConnected(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    public boolean bind(final Context context) {
        Intent intent = new Intent(context, SortService.class);
        context.bindService(intent, Context.BIND_AUTO_CREATE, mCallbackExecutor, mConnection);

        try {
            mLatch.await(AWAIT_TIMEOUT_MILLS, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return mSortService != null;
    }

    public void onServiceConnected(@NonNull IBinder iBinder) {
        mSortService = ISortService.Stub.asInterface(iBinder);
        mLatch.countDown();
    }

    @NonNull
    public int[] generate(int count) throws RemoteException {
        return mSortService.generate(count);
    }

    public int[] sort(int[] arr, @NonNull SortingMethod method) {
        try {
            return mSortService.sort(arr, method);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
