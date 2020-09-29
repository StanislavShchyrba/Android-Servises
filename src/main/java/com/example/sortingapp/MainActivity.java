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

    private Button mGenerateButton = null, mSortButton = null;

    private RecyclerView mNumberList = null;
    private NumbersAdapter mNumbersAdapter = null;

    private SortingMethod mSortingMethod = SortingMethod.BUBBLESORT;//as Default
    private SortServiceManager mSortManager = new SortServiceManager();

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mSortManager.serviceConnected(iBinder);
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Service connected", Toast.LENGTH_SHORT);
            toast.show();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mSortManager = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SortServiceManager sortServiceManager = new SortServiceManager();

        try {
            sortServiceManager.bind(this, mConnection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        if (mNumbersAdapter == null || mNumberList == null) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No numbers to sort, press generate firstly", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        if (mNumbersAdapter.getDataSet().length == 0) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No numbers to sort, press generate firstly", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        try {
            mNumbersAdapter.setDataSet(mSortManager.sort(mNumbersAdapter.getDataSet(), mSortingMethod));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void onGenerateButtonClicked() {
        mNumbersAdapter = new NumbersAdapter();
        mNumberList.setAdapter(mNumbersAdapter);

        try {
            mNumbersAdapter.setDataSet(mSortManager.generate(NUMBER_OF_ELEMENTS));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void initButtons() {
        mGenerateButton = (Button) findViewById(R.id.main_GenerateButton);
        mSortButton = (Button) findViewById(R.id.main_SortButton);

        mGenerateButton.setOnClickListener(this);
        mSortButton.setOnClickListener(this);
    }

    private void initRecyclerView() {
        mNumberList = findViewById(R.id.main_RecyclerView);
        mNumberList.setHasFixedSize(true);
        LinearLayoutManager NumberListManager = new LinearLayoutManager(this);
        mNumberList.setLayoutManager(NumberListManager);
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