package com.example.test;

import android.content.Intent;
import android.os.Bundle;
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

    public static final int SIZE_OF_RECYCLER_ELEMENTS = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


    @Override
    public void onClick(View view) {
        if (view == mGenerateButton) {
            startService(new Intent(this, MyService.class));
        } else if (view == mSortButton) {
            stopService(new Intent(this, MyService.class));
        }
    }
}