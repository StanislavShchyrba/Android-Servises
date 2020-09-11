package com.example.test;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;

public class MyService extends Service {

    private final IBinder mBinder = new MyBinder();

    public enum ChooseSort{
        BUBBLESORT,
        QUICKSORT,
        COUNTINGSORT
    }

    //JNI
    private native int[] generate (int count);

    private native int[] sort(int[] array, ChooseSort chSort);

    public native String stringFromJNI();

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

    public class MyBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }
}
