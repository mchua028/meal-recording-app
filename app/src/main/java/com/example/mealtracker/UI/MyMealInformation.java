package com.example.mealtracker.UI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
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

import com.example.mealtracker.AppLogic.MealRecordManager;
import com.example.mealtracker.MyMealInformationExampleAdapter;
import com.example.mealtracker.MyMealInformationExampleItem;
import com.example.mealtracker.R;
import com.google.android.material.navigation.NavigationView;

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
                Log.d("into ","myMealInfo");
                MealRecordManager mealRecordManager = MealRecordManager.getSingleton();
                Log.d("mealRecrodManager",mealRecordManager.getMealRecord().getFoods().get(0).getName());
                mealRecordManager.getMealRecord().addToServer();
                double calorieConsumedToday = mealRecordManager.getCalorieConsumedToday();
                Toast.makeText(MyMealInformation.this,"calorieConsumedToday: "+Double.toString(calorieConsumedToday),
                        Toast.LENGTH_SHORT).show();
                double calorieQuotaRemaining = mealRecordManager.getCalorieRemaining();
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
        addMoreCardviews();
    }

    public void addMoreCardviews(){
        int position = getExampleListSize();

        // for each food entered in previous page (input food details)
        for (int i=1; i<3; i++) {   // TODO: insert actual num of datasets instead of dummy number 3
            //int position = Integer.parseInt(editTextInsert.getText().toString());
            insertItem(position);
        }
    }

    // insert card views
    public void insertItem(int position) {
        Log.d("insert item","position is" + position);
        mExampleList.add(position, new MyMealInformationExampleItem(mText1, mText2, mText3, mText4));
        mAdapter.notifyItemInserted(position);
    }

    // get mExampleList size
    public int getExampleListSize() {
        return mExampleList.size();
    }
}