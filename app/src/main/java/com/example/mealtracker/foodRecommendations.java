package com.example.mealtracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.*;

public class foodRecommendations extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Food Recommendations");
        return inflater.inflate(R.layout.fragment_food_recommendations, container, false);
        /*Recommender recommender = new Recommender();
        recommender.setSuggestedNutrientAmt();
        recommender.setActualNutrientAmt();
        recommender.setLackedNutrients();
        recommender.setRecommendedFoods();
        HashMap<String, String> recommendedFoods = recommender.getRecommendedFoods();

         */

    }
}