package com.example.mealtracker;

import android.widget.TextView;

public class FoodRecommendationsExampleItem {

    private TextView mText1;
    private TextView mText2;
    private TextView mText3;
    private TextView mText4;
    private TextView mText5;
    private TextView mText6;

    public FoodRecommendationsExampleItem(TextView text1, TextView text2) {
        mText1 = text1;
        mText2 = text2;
        /*mText3 = text3;
        mText4 = text4;
        mText5 = text5;
        mText6 = text6;*/
    }

    public TextView getText1() {
        return mText1;
    }
    public TextView getText2() {
        return mText2;
    }
    /*
    public TextView getText3() {
        return mText3;
    }
    public TextView getText4() {
        return mText4;
    }

    public TextView getText5() {
        return mText5;
    }
    public TextView getText6() {
        return mText6;
    }*/
}
