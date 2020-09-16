package com.example.test;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.sorters.Sorter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private static final int NUMBER_OF_ELEMENTS = 100;

    private Button mGenerateButton, mSortButton;

    private RecyclerView mNumberList;

    private NumbersAdapter mNumbersAdapter;

    private MyService.MyBinder mBinder;

    private Sorter.SortingMethod mSortingMethod = Sorter.SortingMethod.BUBBLESORT;//as Default

    private boolean mIsBounded = false;

    public static final int SIZE_OF_RECYCLER_ELEMENTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Service creation and binding
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

        //RecyclerView creation
        mNumberList = findViewById(R.id.main_RecyclerView);
        mNumberList.setHasFixedSize(true);
        LinearLayoutManager NumberListManager = new LinearLayoutManager(this);
        mNumberList.setLayoutManager(NumberListManager);


        //Buttons creation
        mGenerateButton = (Button) findViewById(R.id.main_GenerateButton);
        mSortButton = (Button) findViewById(R.id.main_SortButton);

        mGenerateButton.setOnClickListener(this);
        mSortButton.setOnClickListener(this);

        //Spinner creation
        Spinner mSpinner = findViewById(R.id.main_Spinner);

        List<String> captionList = new ArrayList<>();
        for (Sorter.SortingMethod sort : Sorter.SortingMethod.values()) {
            captionList.add(getString(sort.getCaptionStringId()));
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, captionList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(spinnerAdapter);
        mSpinner.setOnItemSelectedListener(this);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyService.MyBinder binder = (MyService.MyBinder) iBinder;
            mBinder = binder;
            mIsBounded = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mIsBounded = false;
        }
    };

    @Override
    public void onClick(View view) {
        if (view == mGenerateButton) {
            // 1 . get array from the service () using binder
            // 2. set it for the adapter
            mNumbersAdapter = new NumbersAdapter(SIZE_OF_RECYCLER_ELEMENTS);
            mNumberList.setAdapter(mNumbersAdapter);

            mNumbersAdapter.setDataSet(mBinder.generate(NUMBER_OF_ELEMENTS));


        } else if (view == mSortButton) {
            // 1. check if array is generated
            // 2. check if mSortingMethod != null
            if (mNumbersAdapter.getDataSet().length != 0) {
                if (mSortingMethod != null) {
                    mNumbersAdapter.setDataSet(mBinder.sort(mNumbersAdapter.getDataSet(), mSortingMethod));
                }
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mSortingMethod = Sorter.SortingMethod.values()[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // do nothing
    }
}