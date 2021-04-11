package com.example.mealtracker.UI;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mealtracker.AppLogic.MealRecordManager;
import com.example.mealtracker.AppLogic.Recommender;
import com.example.mealtracker.Exceptions.EmptyResultException;
import com.example.mealtracker.R;

import java.util.ArrayList;
import java.util.HashMap;

public class FoodRecommendationsExampleAdapter extends RecyclerView.Adapter<FoodRecommendationsExampleAdapter.ExampleViewHolder> {

    private ArrayList<FoodRecommendationsExampleItem> mExampleList;

    private String lackedNutrient;
    private String recommendedFood;
    private HashMap<String, HashMap<String, Double>> recommend;
    private HashMap<String, Double> recommendFoodHM;
    private HashMap<String, Double> lackedNutrientHM;

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
            recommend = recommender.recommend();
            lackedNutrientHM = recommender.getLackedNutrient();
            recommendFoodHM = recommender.getRecommendFood();
        } catch (EmptyResultException e) {
            e.printStackTrace();
        }
        lackedNutrient = lackedNutrientHM.keySet().toArray()[position].toString();
        int i = recommend.get(lackedNutrient).toString().lastIndexOf(", ");
        int j = recommend.get(lackedNutrient).toString().lastIndexOf("}");
        recommendedFood = recommend.get(lackedNutrient).toString().split("\\{")[1].split(",")[0] + "g/100g\n"
                          + recommend.get(lackedNutrient).toString().split(", ")[1].split(",")[0] + "g/100g\n"
                          + recommend.get(lackedNutrient).toString().substring(i+2, j) + "g/100g";
        recommendedFood = recommendedFood.replaceAll("=", ": ");
        Log.d("numberoffoodrec", Integer.toString(position));
        holder.mTextView1.setText(lackedNutrient);
        holder.mTextView2.setText(recommendedFood);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
