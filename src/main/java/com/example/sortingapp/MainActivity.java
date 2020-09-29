package com.example.sortingapp;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sortmanager.SortServiceManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private static final int NUMBER_OF_ELEMENTS = 100;

    private Button mGenerateButton, mSortButton;

    private RecyclerView mNumberListRecycleView;
    private NumbersAdapter mNumbersAdapter;

    private SortingMethod mSortingMethod = SortingMethod.BUBBLESORT; // as Default
    private SortServiceManager mSortManager = new SortServiceManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SortServiceManager sortServiceManager = new SortServiceManager();

        // todo: move out into separate thread
        boolean isStartedSuccesfully = sortServiceManager.bind(this, mConnection);

        // todo:
        Toast.create() // show toast with isStartedSuccesfully

        initButtons();
        initRecyclerView();
        initSpinner();
    }

    @Override
    public void onClick(View view) {
        if (view == mGenerateButton) {
            onGenerateButtonClicked();

        } else if (view == mSortButton) {
            onSortButtonClicked();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        mSortingMethod = SortingMethod.values()[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void onSortButtonClicked() {
        if (mNumbersAdapter == null || mNumbersAdapter.getDataSet().length == 0) {
            Toast.makeText(getApplicationContext(),
                    R.string.press_generate_first, Toast.LENGTH_SHORT).show();
            return;
        }

        int[] sortedNumbersArray = null;

        try {
            sortedNumbersArray = mSortManager.sort(mNumbersAdapter.getDataSet(), mSortingMethod)
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (sortedNumbersArray == null) {
            // something bad has happened(
            // todo: show toast
            return;
        }

        mNumbersAdapter.setDataSet(sortedNumbersArray);
    }

    private void onGenerateButtonClicked() {
        int[] numbersArray = null;

        try {
            numbersArray = mSortManager.generate(NUMBER_OF_ELEMENTS);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        if (numbersArray == null) {
            // show error toast
            return;
        }

        mNumbersAdapter = new NumbersAdapter();
        mNumbersAdapter.setDataSet(numbersArray);
        mNumberListRecycleView.setAdapter(mNumbersAdapter);
    }

    private void initButtons() {
        mGenerateButton = (Button) findViewById(R.id.main_GenerateButton);
        mSortButton = (Button) findViewById(R.id.main_SortButton);

        mGenerateButton.setOnClickListener(this);
        mSortButton.setOnClickListener(this);
    }

    private void initRecyclerView() {
        mNumberListRecycleView = findViewById(R.id.main_RecyclerView);
        mNumberListRecycleView.setHasFixedSize(true);
        LinearLayoutManager NumberListManager = new LinearLayoutManager(this);
        mNumberListRecycleView.setLayoutManager(NumberListManager);
    }

    private void initSpinner() {
        Spinner mSpinner = findViewById(R.id.main_Spinner);

        List<String> captionList = new ArrayList<>();
        for (SortingMethod sort : SortingMethod.values()) {
            captionList.add(getString(sort.getCaptionStringId()));
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, captionList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(spinnerAdapter);
        mSpinner.setOnItemSelectedListener(this);
    }
}