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
    private CountDownLatch mLatch = new CountDownLatch(1);
    private boolean IsServiceConnected = false;
    private ISortService miSortService = null;

    public boolean bind(final Context context, final ServiceConnection Connection) throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                bindInNewThread(mLatch, context, Connection);
            }
        }).start();

        mLatch.await();

        return IsServiceConnected;
    }

    public void serviceConnected(@NonNull IBinder iBinder) {
        miSortService = ISortService.Stub.asInterface(iBinder);
    }

    @NonNull
    public int[] generate(int count) throws RemoteException {
        return miSortService.generate(count);
    }

    public int[] sort(int[] arr, @NonNull SortingMethod method) throws RemoteException {
        return miSortService.sort(arr, method);
    }

    private void bindInNewThread(CountDownLatch latch, Context context, ServiceConnection Connection) {

        Intent intent = new Intent(context, SortService.class);
        context.bindService(intent, Connection, Context.BIND_AUTO_CREATE);

        mLatch.countDown();

        IsServiceConnected = true;
    }
}
