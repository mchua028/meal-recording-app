package com.example.mealtracker;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealtracker.AppLogic.MealRecordManager;
import com.example.mealtracker.AppLogic.Recommender;
import com.example.mealtracker.Exceptions.EmptyResultException;

import java.util.ArrayList;
import java.util.HashMap;

public class FoodRecommendationsExampleAdapter extends RecyclerView.Adapter<FoodRecommendationsExampleAdapter.ExampleViewHolder> {

    private ArrayList<FoodRecommendationsExampleItem> mExampleList;

    private String lackedNutrient;
    private String lackedNutrientHM;
    private String recommendFoodHM;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView3;
        public TextView mTextView4;
        public TextView mTextView5;
        public TextView mTextView6;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.vitaminNameTxt);
            mTextView2 = itemView.findViewById(R.id.foodsLabel);
            /*mTextView3 = itemView.findViewById(R.id.vitaminName2Txt);
            mTextView4 = itemView.findViewById(R.id.foodsLabel2);
            mTextView5 = itemView.findViewById(R.id.vitaminName3Txt);
            mTextView6 = itemView.findViewById(R.id.foodsLabel3);*/
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
            recommender.getLackedNutrient();
            lackedNutrientHM = recommender.getLackedNutrient().toString();
            recommender.getRecommendFood();
            recommendFoodHM = recommender.getRecommendFood().toString();
        } catch (EmptyResultException e) {
            e.printStackTrace();
        }

        if (position == 0) {
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
        }
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
