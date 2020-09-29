package com.example.sortmanager;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.NonNull;

import com.example.sortingapp.ISortService;
import com.example.sortingapp.SortingMethod;

import java.util.concurrent.CountDownLatch;

public class SortServiceManager {
    private static final long AWAIT_TIMEOUT_MILLS = 1000;

    private CountDownLatch mLatch = new CountDownLatch(1);
    private ISortService mSortService = null;

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

        // todo fix this:
        mLatch.await(AWAIT_TIMEOUT);

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

    public int[] sort(int[] arr, @NonNull SortingMethod method) throws RemoteException {
        return mSortService.sort(arr, method);
    }
}
