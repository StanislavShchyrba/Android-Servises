package com.example.sortmanager;

import android.content.Intent;
import android.os.RemoteException;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ServiceTestRule;

import com.example.sortingapp.ISortService;
import com.example.sortingapp.SortingMethod;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.assertArrayEquals;

@RunWith(AndroidJUnit4.class)
public class SortServiceTest {
    @Rule
    public final ServiceTestRule mServiceRule = new ServiceTestRule();

    @Test
    public void testWithStartedService() throws TimeoutException, RemoteException {
        mServiceRule.startService(
                new Intent(getApplicationContext(), SortService.class));
        ISortService.Stub mBinder = (ISortService.Stub) mServiceRule.bindService(new Intent(getApplicationContext(), SortService.class));

        int[] arr = {10, 1, 0, 2, 3};
        assertArrayEquals(new int[]{0, 1, 2, 3, 10}, mBinder.sort(arr, SortingMethod.BUBBLESORT));
    }
}