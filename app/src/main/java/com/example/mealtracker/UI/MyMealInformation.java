package com.example.mealtracker.UI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealtracker.AppLogic.MealRecordManager;
import com.example.mealtracker.DAO.Food;
import com.example.mealtracker.DAO.MealRecord;
import com.example.mealtracker.R;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyMealInformation extends AppCompatActivity {
    private ArrayList<MyMealInformationExampleItem> mExampleList= new ArrayList<MyMealInformationExampleItem>();

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private NavigationView navigationView;

    private TextView mText1;//=(TextView) findViewById(R.id.foodLabel);
    private TextView mText2;//=(TextView) findViewById(R.id.foodWeight);
    private TextView mText3;//=(TextView) findViewById(R.id.nutrientName);
    private TextView mText4;//=(TextView) findViewById(R.id.nutrientWeight);

    private MealRecord[] mealRecords=null;
    private ArrayList<Food> foods=null;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_meal_information);
        Log.d("entered","mymealinformation");
        //mealRecords = MealRecord.queryAll(); - moved to myCalories for display
        //Log.d("complete query","mealrecords")
        MealRecordManager mrm = MealRecordManager.getSingleton();
        foods = mrm.getMealRecord().getFoods();


        // todo: display them in the app - food

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new MyMealInformationExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        Log.d("before","addMoreCardViews");
        //addMoreCardviews();
        Log.d("after","addMoreCardViews");

        Button addToTodaysCaloriesBtn = (Button) findViewById(R.id.addToTodaysCaloriesBtn);
        addToTodaysCaloriesBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                // TODO: logic for adding to calories
                Log.d("into ","myMealInfo");
                if (foods.size() == 0) {
                    Toast.makeText(MyMealInformation.this, "You haven't added any food records", Toast.LENGTH_SHORT).show();
                }
                else {
                    MealRecordManager mealRecordManager = MealRecordManager.getSingleton();
                    Log.d("mealRecrodManager", mealRecordManager.getMealRecord().getFoods().get(0).getName());
                    //mealRecordManager.getMealRecord().addToServer();
                    //mealRecordManager.calculateCalorieConsumedToday();//error
                    double calorieConsumedToday = mealRecordManager.getCalorieConsumedToday();
                    Toast.makeText(MyMealInformation.this, "calorieConsumedToday: " + Double.toString(calorieConsumedToday),
                            Toast.LENGTH_SHORT).show();
                    //mealRecordManager.calculateCalorieQuotaRemainingToday();//error
                    double calorieQuotaRemaining = mealRecordManager.getCalorieRemaining();
                    Toast.makeText(MyMealInformation.this, "calorieQuotaRemainingToday: " + Double.toString(calorieQuotaRemaining),
                            Toast.LENGTH_SHORT).show();
                    // TODO: add food to mealrecord (added)
                }

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
        Log.d("before cal","noOfCardViews");
        int noOfCardViews = foods.size();
        Log.d("noOfCardViews",Integer.toString(noOfCardViews));
        for (int i=0; i<noOfCardViews; i++) {   // TODO: insert actual num of datasets instead of dummy number 3
            //int position = Integer.parseInt(editTextInsert.getText().toString());
            Log.d("int i =",Integer.toString(i)+"th cardView");
            insertItem(position);
        }
        Log.d("after","inaddMorecardviews");
    }

    // insert card views
    public void insertItem(int position) {
        Log.d("insert item","position is" + position);
        /*
        Log.d("setting","name");
        mText1.setText(foods.get(position).getName());
        Log.d("setting","weight");
        mText2.setText(Double.toString(foods.get(position).getActualIntake()));
        String nutritionAmt = "";
        nutritionAmt+=Double.toString(foods.get(position).getNutrients().getCaloriePer100g());
        Log.d("nutritionAmt",nutritionAmt);
        nutritionAmt+="\n"+Double.toString(foods.get(position).getNutrients().getFat());
        nutritionAmt+="\n"+Double.toString(foods.get(position).getNutrients().getCholesterol());
        nutritionAmt+="\n"+Double.toString(foods.get(position).getNutrients().getSodium());
        nutritionAmt+="\n"+Double.toString(foods.get(position).getNutrients().getPotassium());
        nutritionAmt+="\n"+Double.toString(foods.get(position).getNutrients().getSugar());
        nutritionAmt+="\n"+Double.toString(foods.get(position).getNutrients().getDietaryFibre());
        nutritionAmt+="\n"+Double.toString(foods.get(position).getNutrients().getProtein());
        nutritionAmt+="\n"+Double.toString(foods.get(position).getNutrients().getCalcium());
        nutritionAmt+="\n"+Double.toString(foods.get(position).getNutrients().getVitaminC());
        nutritionAmt+="\n"+Double.toString(foods.get(position).getNutrients().getIron());
        nutritionAmt+="\n"+Double.toString(foods.get(position).getNutrients().getMagnesium());
        Log.d("nutritionamt",nutritionAmt);
        Log.d("setting","nutritionamt");
        mText4.setText(nutritionAmt);

         */
        Log.d("adding","card");
        mExampleList.add(position, new MyMealInformationExampleItem(mText1, mText2, mText3, mText4));
        //mExampleList.add(position, new MyMealInformationExampleItem(mText1, mText2, mText3, mText4));
        mAdapter.notifyItemInserted(position);
    }

    // get mExampleList size
    public int getExampleListSize() {
        return mExampleList.size();
    }
}