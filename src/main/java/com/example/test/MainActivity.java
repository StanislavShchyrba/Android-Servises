package com.example.test;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mGenerateButton, mSortButton;

    private RecyclerView mNumberList;

    private NumbersAdapter mNumbersAdapter;

    private Spinner mSpinner;

    private MyService mService;

    private boolean mIsBounded=false;

    public static final int SIZE_OF_RECYCLER_ELEMENTS = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Service creation and binding
        Intent intent = new Intent(this,MyService.class);
        bindService(intent,mConnection,Context.BIND_AUTO_CREATE);

        //RecyclerView creation
        mNumberList=findViewById(R.id.main_RecyclerView);
        LinearLayoutManager NumberListManager = new LinearLayoutManager(this);
        mNumberList.setLayoutManager(NumberListManager);
        mNumberList.setHasFixedSize(true);

        mNumbersAdapter=new NumbersAdapter(SIZE_OF_RECYCLER_ELEMENTS);
        mNumberList.setAdapter(mNumbersAdapter);

        //Buttons creation
        mGenerateButton = (Button) findViewById(R.id.main_GenerateButton);
        mSortButton = (Button) findViewById(R.id.main_SortButton);

        mGenerateButton.setOnClickListener(this);
        mSortButton.setOnClickListener(this);

        //Spinner creation
        mSpinner = findViewById(R.id.main_Spinner);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.sorting_algorithm,android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(spinnerAdapter);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyService.MyBinder binder = (MyService.MyBinder) iBinder;
            mService = binder.getService();
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

        } else if (view == mSortButton) {

        }
    }
}