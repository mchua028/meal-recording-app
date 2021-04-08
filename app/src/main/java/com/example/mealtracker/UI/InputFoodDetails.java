package com.example.mealtracker.UI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mealtracker.AppLogic.MealRecordManager;
import com.example.mealtracker.DAO.Food;
import com.example.mealtracker.DAO.MealRecord;
import com.example.mealtracker.Exceptions.EmptyInputException;
import com.example.mealtracker.Exceptions.EmptyResultException;
import com.example.mealtracker.R;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Interface where the user adds foods to meal record.
 * FIXME:
 *  - There is a new empty food detail field. How would you deal with it when you "click" remove which actually deletes the food?
 *  After I delete, how do I have the empty field to add food again.
 */
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

        /**
         * Adds new food information
         */
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // to call the networking thread synchronously
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                int position = getExampleListSize();

                textInputFoodName = findViewById(R.id.txtFood);
                textInputFoodWeight = findViewById(R.id.txtFoodWeight);

                editInputFoodName = findViewById(R.id.editFood);
                editInputFoodWeight = findViewById(R.id.editFoodWeight); //TODO:multiply the weight to the nutrition obtained to get actual amount of nutrients

                String foodName = textInputFoodName.getEditText().getText().toString().trim();
                if (!checkFoodNameCorrectness(foodName)) return;

                String weightInputStr = textInputFoodWeight.getEditText().getText().toString().trim();
                try {
                    int foodWeight = Integer.parseInt(weightInputStr);
                    if (!(foodWeight >= 1 & foodWeight <= 2000)) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    Toast.makeText(InputFoodDetails.this, "The weight is invalid.", Toast.LENGTH_SHORT).show();
                }

                Log.d("got foodinfo","foodinfo:"+foodName);

                //ArrayList<String> newFoodInfo = new ArrayList<String>();
                //newFoodInfo.add(foodName);

                Log.d("starting","queryFoodName");
                //Log.d("newFood",newFood.getName()) ;
                Log.d("foodName",foodName) ;
                Food food;
                try {
                    food = Food.searchFood(foodName);
                    Log.d("complete","queryFoodName");
                    mealRecord.addFood(food);
                    Log.d("complete","addFood");
                    Toast.makeText(InputFoodDetails.this, String.format("%s is added.", foodName), Toast.LENGTH_SHORT).show();
                } catch (EmptyInputException e) {
                    e.printStackTrace();
                } catch (EmptyResultException e) {
                    // when no result is found
                    Toast.makeText(InputFoodDetails.this, String.format("%s is not found.", foodName), Toast.LENGTH_SHORT).show();
                    return;
                }
                //newFood.setNutrients(nutrient);
                //int position = Integer.parseInt(editTextInsert.getText().toString());
                insertItem(position);
            }

            private boolean checkFoodNameCorrectness(String foodName) {
                if (foodName == null || foodName.isEmpty()) {
                    Toast.makeText(InputFoodDetails.this, "Food name cannot be empty.", Toast.LENGTH_SHORT).show();
                    return false;
                }
                Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(foodName);
                boolean b = m.find();
                if (b) {
                    Toast.makeText(InputFoodDetails.this, String.format("%s is invalid food name.", foodName), Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            }
        });

        /**
         * Deletes the input field.
         */
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getExampleListSize()-1;
                //int position = Integer.parseInt(editTextInsert.getText().toString());
                textInputFoodName = findViewById(R.id.txtFood);
                textInputFoodWeight = findViewById(R.id.txtFoodWeight);

                editInputFoodName = findViewById(R.id.editFood);
                editInputFoodWeight = findViewById(R.id.editFoodWeight); //TODO:multiply the weight to the nutrition obtained to get actual amount of nutrients
                try {
                    mealRecord.deleteLastFood();
                    removeItem(position);
                    Toast.makeText(InputFoodDetails.this, "Delete successfully.", Toast.LENGTH_SHORT).show();
                } catch (IndexOutOfBoundsException e) {
                    Toast.makeText(InputFoodDetails.this, "There is no food record to delete.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button onClickCancelBtn = (Button) findViewById(R.id.cancelBtn);
        onClickCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /**
         * Submits the MealRecord Added.
         */
        countCaloriesBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (mealRecord.getFoods().isEmpty()) {
                    Toast.makeText(InputFoodDetails.this, "Empty Meal Record " +
                            "cannot be uploaded", Toast.LENGTH_SHORT).show();
                }
                MealRecordManager.getSingleton().addMealRecordToDB(mealRecord);
                startActivity(new Intent(v.getContext(), MyMealInformation.class));
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void goToMyMealInformation(View view) {
        Log.d("mealReocrd",mealRecord.getFoods().get(0).getName());
        mealRecord.setTime(LocalDateTime.now());
        Log.d("mealRecord",mealRecord.getTimeString());
        mealRecordMgr.setMealRecord(mealRecord);
        Intent intent = new Intent(this, MyMealInformation.class);
        startActivity(intent);
    }

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