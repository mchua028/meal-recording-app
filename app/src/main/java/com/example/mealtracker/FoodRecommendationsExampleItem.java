package com.example.mealtracker;

import android.widget.TextView;

public class FoodRecommendationsExampleItem {

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
