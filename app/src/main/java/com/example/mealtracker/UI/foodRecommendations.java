package com.example.mealtracker.UI;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealtracker.AppLogic.MealRecordManager;
import com.example.mealtracker.AppLogic.Recommender;
import com.example.mealtracker.DAO.MealRecord;
import com.example.mealtracker.Exceptions.EmptyResultException;
import com.example.mealtracker.R;

import java.util.ArrayList;
import java.util.HashMap;

public class foodRecommendations extends Fragment {

    private ArrayList<FoodRecommendationsExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private TextView mText1;
    private TextView mText2;

    HashMap<String, HashMap<String, Double>> recommend;
    HashMap<String, Double> recommendFoodHM;
    HashMap<String, Double> lackedNutrientHM;

    ArrayList<MealRecord> mealRecordsForToday;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_food_recommendations, container, false);
        getActivity().setTitle("Food Recommendations");

        try {
            Recommender recommender = new Recommender(MealRecordManager.getSingleton().calculateTotalNutrient());
            recommend = recommender.recommend();
            lackedNutrientHM = recommender.getLackedNutrient();
            recommendFoodHM = recommender.getRecommendFood();
            mealRecordsForToday = MealRecord.queryAll();
        } catch (EmptyResultException e) {
            Toast.makeText(getActivity(), "You haven't added any meal records", Toast.LENGTH_SHORT).show();
        }


        mExampleList = new ArrayList<>();

        mRecyclerView = v.findViewById(R.id.foodRecRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(v.getContext());
        mAdapter = new FoodRecommendationsExampleAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        // TODO: get real data from database
        if (mealRecordsForToday.isEmpty()) {
            mRecyclerView.setVisibility(v.GONE);
            Toast.makeText(getActivity(), "You haven't added any meal records", Toast.LENGTH_SHORT).show();
        }
        else {
            addMoreCardviews();
        }

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