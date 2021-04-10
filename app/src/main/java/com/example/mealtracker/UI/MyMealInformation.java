package com.example.mealtracker.UI;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealtracker.AppLogic.HealthInfoManager;
import com.example.mealtracker.AppLogic.MealRecordManager;
import com.example.mealtracker.AppLogic.Recommender;
import com.example.mealtracker.DAO.Food;
import com.example.mealtracker.DAO.MealRecord;
import com.example.mealtracker.Exceptions.EmptyResultException;
import com.example.mealtracker.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;

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
                    //mealRecordManager.addMealRecordToDB(mealRecordManager.getMealRecord());
                    double suggestedCalorie = HealthInfoManager.getSingleton().getSuggestedCalorie();
                    //mealRecordManager.calculateCalorieConsumedToday();//error
                    double calorieConsumedToday = mealRecordManager.getCalorieConsumedToday();
                    Toast.makeText(MyMealInformation.this, "calorieConsumedToday: " + Double.toString(calorieConsumedToday),
                            Toast.LENGTH_SHORT).show();
                    //mealRecordManager.calculateCalorieQuotaRemainingToday();//error
                    //double calorieQuotaRemaining = mealRecordManager.getCalorieRemaining();
                    Toast.makeText(MyMealInformation.this, "calorieQuotaRemainingToday: " + Double.toString(suggestedCalorie-calorieConsumedToday),
                            Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(MyMealInformation.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button onClickCancelBtn = (Button) findViewById(R.id.myMealInformationCancelBtn);
        onClickCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("getting","mealrecordmgrsingleton");
                MealRecordManager mealRecordManager = MealRecordManager.getSingleton();
                Log.d("deleting","deleting mealrecord");
                Log.d("mealrecordid",mealRecordManager.getMealRecord().getId());
                mealRecordManager.deleteMealRecord(mealRecordManager.getMealRecord());
                Toast.makeText(MyMealInformation.this,"no food record added",Toast.LENGTH_SHORT).show();

                //finish();
                Intent intent = new Intent(MyMealInformation.this, MainActivity.class);
                startActivity(intent);
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

    public static class FoodRecommendationsExampleAdapter extends RecyclerView.Adapter<FoodRecommendationsExampleAdapter.ExampleViewHolder> {

        private ArrayList<FoodRecommendationsExampleItem> mExampleList;

        private String lackedNutrient;
        private String recommendFood;
        private HashMap<String, Double> lackedNutrientHM;
        private String recommendFoodHM;

        public static class ExampleViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView1;
            public TextView mTextView2;

            public ExampleViewHolder(View itemView) {
                super(itemView);
                mTextView1 = itemView.findViewById(R.id.vitaminNameTxt);
                mTextView2 = itemView.findViewById(R.id.foodsLabel);
            }
        }

        public FoodRecommendationsExampleAdapter(ArrayList<FoodRecommendationsExampleItem> exampleList) {
            mExampleList = exampleList;
        }

        @NonNull
        @Override
        public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_recommendations_example_item, parent, false);
            ExampleViewHolder evh = new ExampleViewHolder(v);
            return evh;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
            FoodRecommendationsExampleItem currentItem = mExampleList.get(position);

            try {
                Recommender recommender = new Recommender(MealRecordManager.getSingleton().calculateTotalNutrient());
                recommender.recommend();
                lackedNutrientHM = recommender.getLackedNutrient();
                //lackedNutrientHM = recommender.getLackedNutrient().toString();
                recommender.getRecommendFood();
                recommendFoodHM = recommender.getRecommendFood().toString();
            } catch (EmptyResultException e) {
                e.printStackTrace();
            }
            lackedNutrient = lackedNutrientHM.keySet().toArray()[position].toString();
            Log.d("numberoffoodrec", Integer.toString(position));
            holder.mTextView1.setText(lackedNutrient);
            holder.mTextView2.setText(recommendFoodHM);

            /*if (position == 0) {
                holder.mTextView1.setText(lackedNutrientHM.split("\\{")[1].split("=")[0]);
                holder.mTextView2.setText("Foods containing this nutrient:\n"
                        + "1. " + recommendFoodHM.split(", ")[1].split("=")[0] + "\n"
                        + "2. " + recommendFoodHM.split(", ")[4].split("=")[0] + "\n"
                        + "3. " + recommendFoodHM.split(", ")[3].split("=")[0] + "\n");
            }
            else if (position == 1) {
                Log.d("here", "1");
                holder.mTextView1.setText(lackedNutrientHM.split(", ")[2].split("=")[0]);
                holder.mTextView2.setText("Foods containing this nutrient:\n"
                        + "1. " + recommendFoodHM.split(", ")[2].split("=")[0] + "\n"
                        + "2. " + recommendFoodHM.split(", ")[8].split("=")[0] + "\n"
                        + "3. " + recommendFoodHM.split(", ")[3].split("=")[0] + "\n");

            }

            else if (position == 2) {
                Log.d("here", "2");
                holder.mTextView1.setText(lackedNutrientHM.split(", ")[3].split("=")[0]);
                holder.mTextView2.setText("Foods containing this nutrient:\n"
                        + "1. " + recommendFoodHM.split(", ")[5].split("=")[0] + "\n"
                        + "2. " + "Nuts\n"
                        + "3. " + "Broccoli\n");
            }*/
        }

        @Override
        public int getItemCount() {
            return mExampleList.size();
        }
    }

    public static class foodRecommendations extends Fragment {

        private ArrayList<FoodRecommendationsExampleItem> mExampleList;

        private RecyclerView mRecyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager mLayoutManager;

        private TextView mText1;
        private TextView mText2;

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.fragment_food_recommendations, container, false);
            getActivity().setTitle("Food Recommendations");

            try {
                Recommender recommender = new Recommender(MealRecordManager.getSingleton().calculateTotalNutrient());
                recommender.recommend();
                recommender.getLackedNutrient();
                recommender.getRecommendFood();
            } catch (EmptyResultException e) {
                Toast.makeText(getActivity(), "You haven't added any meal records", Toast.LENGTH_SHORT).show();
            }


            mExampleList = new ArrayList<>();

            mRecyclerView = v.findViewById(R.id.myMealsRecyclerView);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(v.getContext());
            mAdapter = new FoodRecommendationsExampleAdapter(mExampleList);

            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);

            // TODO: get real data from database

            addMoreCardviews();
            return v;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void addMoreCardviews(){
            int position = getExampleListSize();

            // for each type of nutrient i'm lacking in
            for (int i=0; i<3; i++) {
                //int position = Integer.parseInt(editTextInsert.getText().toString());
                insertItem(position);
            }
        }

        // insert card views
        public void insertItem(int position) {
            Log.d("insert item","position is" + position);
            mExampleList.add(position, new FoodRecommendationsExampleItem(mText1, mText2));
            mAdapter.notifyItemInserted(position);
        }

        // get mExampleList size
        public int getExampleListSize() {
            return mExampleList.size();
        }
    }

    public static class FoodRecommendationsExampleItem {

        private TextView mText1;
        private TextView mText2;

        public FoodRecommendationsExampleItem(TextView text1, TextView text2) {
            mText1 = text1;
            mText2 = text2;
        }

        public TextView getText1() {
            return mText1;
        }
        public TextView getText2() {
            return mText2;
        }
    }
}