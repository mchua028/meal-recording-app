package com.example.mealtracker;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class foodRecommendations extends Fragment {

    private ArrayList<FoodRecommendationsExampleItem> mExampleList;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private TextView mText1;
    private TextView mText2;
    private TextView mText3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_food_recommendations, container, false);
        getActivity().setTitle("Food Recommendations");

        /*Recommender recommender = new Recommender();
        recommender.setSuggestedNutrientAmt();
        recommender.setActualNutrientAmt();
        recommender.setLackedNutrients();
        recommender.setRecommendedFoods();
        HashMap<String, String> recommendedFoods = recommender.getRecommendedFoods();
         */

        mExampleList = new ArrayList<>();
        mExampleList.add(new FoodRecommendationsExampleItem(mText1, mText2));

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

    public void addMoreCardviews(){
        int position = getExampleListSize();

        // for each type of nutrient i'm lacking in
        for (int i=1; i<3; i++) {   // TODO: insert actual num of datasets instead of dummy number 3
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