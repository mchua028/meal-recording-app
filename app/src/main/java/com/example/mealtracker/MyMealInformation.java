package com.example.mealtracker;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class MyMealInformation extends AppCompatActivity {
    private ArrayList<MyMealInformationExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private NavigationView navigationView;

    private TextView mText1;
    private TextView mText2;
    private TextView mText3;
    private TextView mText4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_meal_information);

        mExampleList = new ArrayList<>();
        mExampleList.add(new MyMealInformationExampleItem(mText1, mText2, mText3, mText4));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MyMealInformationExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        Button addToTodaysCaloriesBtn = (Button) findViewById(R.id.addToTodaysCaloriesBtn);
        addToTodaysCaloriesBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                // TODO: logic for adding to calories
                MealRecordManager mealRecordManager = MealRecordManager.getSingleton();
                mealRecordManager.getMealRecord().addToServer();
                double calorieConsumedToday = mealRecordManager.calculateCalorieConsumedToday();
                Toast.makeText(MyMealInformation.this,"calorieConsumedToday: "+Double.toString(calorieConsumedToday),
                        Toast.LENGTH_SHORT).show();
                double calorieQuotaRemaining = mealRecordManager.calculateCalorieQuotaRemainingToday();
                Toast.makeText(MyMealInformation.this,"calorieQuotaRemainingToday: "+Double.toString(calorieQuotaRemaining),
                        Toast.LENGTH_SHORT).show();
                // TODO: add food to mealrecord (added)

                Intent intent = new Intent(MyMealInformation.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button onClickCancelBtn = (Button) findViewById(R.id.myMealInformationCancelBtn);
        onClickCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    // get mExampleList size
    public int getExampleListSize() {
        return mExampleList.size();
    }
}