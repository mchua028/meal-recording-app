package com.example.mealtracker.UI;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealtracker.AppLogic.MealRecordManager;
import com.example.mealtracker.DAO.Database;
import com.example.mealtracker.DAO.Food;
import com.example.mealtracker.DAO.MealRecord;
//import com.example.mealtracker.Exceptions.DateValidatorUsingLocalDate;
import com.example.mealtracker.Exceptions.EmptyResultException;
import com.example.mealtracker.MyMealsExampleAdapter;
import com.example.mealtracker.MyMealsExampleItem;
import com.example.mealtracker.R;
import com.google.android.material.textfield.TextInputLayout;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


/**
 * Displays the meal records
 */
public class myMeals extends Fragment {
    private ArrayList<MyMealsExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private TextView mText1;
    private TextView mText2;
    private TextView mText3;
    private SearchView mSearchView;

    MealRecordManager mealRecordManager = MealRecordManager.getSingleton();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_meals, container, false);
        getActivity().setTitle("My Meals");

        mExampleList = new ArrayList<>();
        //mExampleList.add(new MyMealsExampleItem(mText1, mText2, mText3));

        mRecyclerView = v.findViewById(R.id.myMealsRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(v.getContext());
        mAdapter = new MyMealsExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mSearchView = v.findViewById(R.id.searchView);
        Log.d("before","editsearchdate");
        //EditText editSearchDate = null;
        //Log.d("before2","editsearchdate");

        //editSearchDate.setText(mSearchView.getQuery());
        //Log.d("after","editsearchdate");


        //get string from input
        //String searchDate = editSearchDate.getText().toString().trim();

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String searchDate = mSearchView.getQuery().toString();
                Log.d("searchDate",searchDate);
                ArrayList<MealRecord> mealRecords = new ArrayList<MealRecord>();
                //MealRecord[] mealRecords1 = null;
                Log.d("onQueryTextSubmit", "called");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate startDate = LocalDate.parse(searchDate,formatter);
                LocalDate endDate = LocalDate.parse(searchDate,formatter);
                Log.d("dategotten",startDate.toString()+"hiii");
                try {
                    Log.d("inside","try");
                    //mealRecords = Database.getSingleton().queryByDate(startDate,endDate);
                    MealRecord mealRecord1 = new MealRecord();
                    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    mealRecord1.setTime(LocalDateTime.parse("08-04-2021 08:14",formatter2));
                    ArrayList<Food> foods = new ArrayList<Food>();
                    Food food1 = new Food();
                    food1.setName("egg");
                    foods.add(food1);
                    mealRecord1.setFoods(foods);
                    mealRecords.add(mealRecord1);
                    Log.d("queryfrom","databasebydate");
                    mealRecordManager.setMealRecords(mealRecords);
                    Log.d("set","mealrecords");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("after","trycatch");
                if(mealRecords.size()==0){
                    Toast.makeText(getActivity(),"There are no meal records for the chosen date",Toast.LENGTH_SHORT).show();
                }
                Log.d("before","addMoreCardViews");
                addMoreCardviews(mealRecords.size());
                Log.d("after","addMoreCardViews");

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        //addMoreCardviews(mealRecords.length);
        return v;
    }



    public void addMoreCardviews(int noOfRecords){
        int position = getExampleListSize();

        // for each meal record in existence
        for (int i=0; i<noOfRecords; i++) {   // TODO: insert actual num of datasets instead of dummy number 3
            //int position = Integer.parseInt(editTextInsert.getText().toString());
            insertItem(position);
        }
    }

    // insert card views
    public void insertItem(int position) {
        Log.d("insert item","position is" + position);

        mExampleList.add(position, new MyMealsExampleItem(mText1, mText2, mText3));

        mAdapter.notifyItemInserted(position);
    }

    // get mExampleList size
    public int getExampleListSize() {
        return mExampleList.size();
    }
}