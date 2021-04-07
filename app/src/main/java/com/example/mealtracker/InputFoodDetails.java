package com.example.mealtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealtracker.DAO.MealRecord;
import com.example.mealtracker.Exceptions.EmptyInputException;
import com.example.mealtracker.Exceptions.EmptyResultException;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class InputFoodDetails extends AppCompatActivity {
    private ArrayList<InputFoodDetailsExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button insertBtn;
    private Button removeBtn;
    private Button countCaloriesBtn;

    private TextInputLayout textInputFoodName;
    private TextInputLayout textInputFoodWeight;

    private EditText editInputFoodName, editInputFoodWeight;

    MealRecordManager mealRecordMgr = MealRecordManager.getSingleton(); //TODO: remove all mealRecords stored inside first?
    MealRecord mealRecord = new MealRecord();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_food_details);

        createExampleList();
        buildRecyclerView();

        insertBtn = findViewById(R.id.insertBtn);
        removeBtn = findViewById(R.id.removeBtn);
        countCaloriesBtn = findViewById(R.id.inputDetailsCountCaloriesBtn);

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getExampleListSize();

                //int position = Integer.parseInt(editTextInsert.getText().toString());
                insertItem(position);

                textInputFoodName = findViewById(R.id.txtFood);
                textInputFoodWeight = findViewById(R.id.txtFoodWeight);

                editInputFoodName = findViewById(R.id.editFood);
                editInputFoodWeight = findViewById(R.id.editFoodWeight); //TODO:multiply the weight to the nutrition obtained to get actual amount of nutrients

                String foodName = textInputFoodName.getEditText().getText().toString().trim();
                int foodWeight = Integer.parseInt(textInputFoodWeight.getEditText().getText().toString().trim());
                Log.d("got foodinfo","foodinfo:"+foodName);

                //ArrayList<String> newFoodInfo = new ArrayList<String>();
                //newFoodInfo.add(foodName);

                Food newFood = new Food();
                Log.d("starting","queryFoodName");
                newFood = mealRecordMgr.query(foodName);
                Log.d("complete","queryFoodName");
                mealRecord.addFood(newFood);
                Log.d("complete","addFood");

            }
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getExampleListSize()-1;
                //int position = Integer.parseInt(editTextInsert.getText().toString());
                textInputFoodName = findViewById(R.id.txtFood);
                textInputFoodWeight = findViewById(R.id.txtFoodWeight);

                editInputFoodName = findViewById(R.id.editFood);
                editInputFoodWeight = findViewById(R.id.editFoodWeight); //TODO:multiply the weight to the nutrition obtained to get actual amount of nutrients

                String foodName = textInputFoodName.getEditText().getText().toString().trim();
                int foodWeight = Integer.parseInt(textInputFoodWeight.getEditText().getText().toString().trim());

                //ArrayList<String> newFoodInfo = new ArrayList<String>();
                //newFoodInfo.add(foodName);

                Food newFood = new Food();
                newFood = mealRecordMgr.query(foodName);
                mealRecord.delFood(newFood);

                removeItem(position);
            }
        });

        Button onClickCancelBtn = (Button) findViewById(R.id.cancelBtn);
        onClickCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void goToMyMealInformation(View view) {
        mealRecordMgr.setMealRecord(mealRecord);
        Intent intent = new Intent(this, MyMealInformation.class);
        startActivity(intent);
    }
    /*countCaloriesBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(v.getContext(), MyMealInformation.class));
        }
    });*/


    // for recycle view
    public void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new InputFoodDetailsExampleItem(textInputFoodName, textInputFoodWeight));
    }

    // for recycle view
    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new InputFoodDetailsExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    // insert card views
    public void insertItem(int position) {
        Log.d("insert item","position is" + position);
        mExampleList.add(position, new InputFoodDetailsExampleItem(textInputFoodName, textInputFoodWeight));
        mAdapter.notifyItemInserted(position);
    }

    // remove card views
    public void removeItem(int position) {
        if (getExampleListSize() != 0) {
            Log.d("remove item","position is" + position);
            mExampleList.remove(position);
            mAdapter.notifyItemRemoved(position);
        }
        else {
            Toast.makeText(this, "No more items to remove!",Toast.LENGTH_SHORT).show();
        }
    }

    // get mExampleList size
    public int getExampleListSize() {
        return mExampleList.size();
    }
}