package com.example.mealtracker.UI;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealtracker.AppLogic.HealthInfoManager;
import com.example.mealtracker.AppLogic.MealRecordManager;
import com.example.mealtracker.DAO.Food;
import com.example.mealtracker.DAO.HealthInfo;
import com.example.mealtracker.DAO.MealRecord;
import com.example.mealtracker.DAO.Nutrient;
import com.example.mealtracker.EditCaloriesExampleItem;
import com.example.mealtracker.Exceptions.EmptyResultException;
import com.example.mealtracker.Exceptions.RecordNotInServerException;
import com.example.mealtracker.R;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.ArrayList;

public class editCalories extends AppCompatActivity {

    private ArrayList<EditCaloriesExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private NavigationView navigationView;

    private EditCaloriesExampleAdapter.OnItemClickListener mListener;

    private TextView mText1;
    private TextView mText2;
    private ImageView mCancel;

    MealRecordManager mealRecordManager = MealRecordManager.getSingleton();
    MealRecord mealRecord = mealRecordManager.getMealRecord();;
    ArrayList<MealRecord> foods = new ArrayList<>();
    ArrayList<MealRecord> MRfoods = new ArrayList<MealRecord>();

    private double calorieConsumed;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("start", "go in");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_calories);
        Log.d("start", "go in2");
        createExampleList();
        buildRecyclerView();
        Log.d("start", "go in3");

        try {
            MRfoods = mealRecord.queryByDate(LocalDate.now(), LocalDate.now());
        } catch (EmptyResultException e) {
            e.printStackTrace();
        }

        HealthInfo healthInfo = HealthInfo.getSingleton();
        double suggestedCalorie;
        suggestedCalorie = HealthInfoManager.getSingleton().getSuggestedCalorie();
        //MealRecordManager mealRecordManager = MealRecordManager.getSingleton();

        calorieConsumed = mealRecordManager.getCalorieConsumedToday();

        double calorieRemain = suggestedCalorie - calorieConsumed;

        TextView text = (TextView) findViewById(R.id.textViewMaxCalories);
        text.setText(String.format("%.1f", suggestedCalorie));

        TextView text2 = (TextView) findViewById(R.id.textViewRemainingCalories);
        text2.setText(String.format("%.1f", calorieRemain));

        TextView text3 = (TextView) findViewById(R.id.textViewCaloriesConsumed);
        text3.setText(String.format("%.1f", calorieConsumed));

        // Save button
        // TODO: to connect to db to edit meal records
        Button onClickSaveBtn = (Button) findViewById(R.id.saveBtn);
        onClickSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calorieConsumed = 0;
                int i;
                for (i = 0; i<foods.size(); i++){
                    try {
                        calorieConsumed += foods.get(i).getTotalCalorie();
                    } catch (EmptyResultException e) {
                        e.printStackTrace();
                    }
                    double calorieRemain = suggestedCalorie - calorieConsumed;

                    TextView text2 = (TextView) findViewById(R.id.textViewRemainingCalories);
                    text2.setText(String.format("%.1f", calorieRemain));

                    TextView text3 = (TextView) findViewById(R.id.textViewCaloriesConsumed);
                    text3.setText(String.format("%.1f", calorieConsumed));
                    //finish();
                }
                goToMainActivity();
            }
        });

        // Cancel button
        Button onClickCancelBtn = (Button) findViewById(R.id.cancelBtn);
        onClickCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Log.d("add", "morecardviews");
        addMoreCardviews();
    }

    public void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addMoreCardviews(){
        int position = getExampleListSize();

        // for each food entered in previous page (input food details)
        Log.d("before cal","noOfCardViews");
        int noOfCardViews = MRfoods.size();
        Log.d("noOfCardViews",Integer.toString(noOfCardViews));
        for (int i=0; i<noOfCardViews; i++) {
            Log.d("int i =",Integer.toString(i)+"th cardView");
            insertItem(position);
        }
        /*
        try {
            Food food1 = new Food();
            food1.setName("egg");
            food1.setActualIntake(100);
            Nutrient nutrient = new Nutrient();
            nutrient.setCaloriePer100g(513);
            food1.setNutrients(nutrient);
            food1.setSuggestedIntake(200);
            foods.add(food1);

            Food food2 = new Food();
            food2.setName("chicken");
            food2.setActualIntake(200);
            Nutrient nutrient2 = new Nutrient();
            nutrient2.setCaloriePer100g(158);
            food2.setNutrients(nutrient2);
            food2.setSuggestedIntake(150);
            foods.add(food2);

            Food food3 = new Food();
            food3.setName("bread");
            food3.setActualIntake(100);
            Nutrient nutrient3 = new Nutrient();
            nutrient3.setCaloriePer100g(265);
            food3.setNutrients(nutrient3);
            food3.setSuggestedIntake(100);
            foods.add(food3);

            //get values from database
        } catch (Exception e) {
            e.printStackTrace();
        }
        mealRecord.setFoods(foods);
        mealRecordManager.setMealRecord(mealRecord);

        // for each type of food in Today's meals
        if (foods!=null) {
            Log.d("go in", Integer.toString(foods.size()));
            for (int i = 0; i < foods.size(); i++) {
                //int position = Integer.parseInt(editTextInsert.getText().toString());
                //mExampleList.add(mText1.setText(foods.get(i).getName()), mText2.setText(Double.toString(foods.get(i).getTotalCalorie())), mCancel);
                insertItem(position);
            }
        }
        else return;*/
    }

    // insert card views
    public void insertItem(int position) {
        Log.d("insert item","position is" + position);
        mExampleList.add(position, new EditCaloriesExampleItem(mText1, mText2, mCancel));
        mAdapter.notifyItemInserted(position);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void removeItem(int position) throws RecordNotInServerException {
        Log.d("tag: remove item:", "removing item");
        mExampleList.remove(position);
        //foods.remove(position);
        try {
            foods = mealRecord.queryByDate(LocalDate.now(), LocalDate.now());
        } catch (EmptyResultException e) {
            e.printStackTrace();
        }
        foods.get(position).deleteFromServer();
        mAdapter.notifyItemRemoved(position);
    }

    public void createExampleList() {
        Log.d("go in", "create eg");
        mExampleList = new ArrayList<>();
        //mExampleList.add(new EditCaloriesExampleItem(mText1, mText2, mCancel));
        //mExampleList.add(new EditCaloriesExampleItem(mText1, mText2, mCancel));

    }

    public void buildRecyclerView() {
        Log.d("go in", "recycler view");
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new EditCaloriesExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        EditCaloriesExampleAdapter.setOnItemClickListener(new EditCaloriesExampleAdapter.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDeleteClick(int position) throws RecordNotInServerException {
                removeItem(position);
            }
        });
    }

    // remove card views
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void editCaloriesRemoveItem(int position) throws RecordNotInServerException {
        if (getExampleListSize() != 0) {
            Log.d("remove item","position is" + position);
            mExampleList.remove(position);
            // foods.remove(position);
            try {
                foods = mealRecord.queryByDate(LocalDate.now(), LocalDate.now());
            } catch (EmptyResultException e) {
                e.printStackTrace();
            }
            foods.get(position).deleteFromServer();
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