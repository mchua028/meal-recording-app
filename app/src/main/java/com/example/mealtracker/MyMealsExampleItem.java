package com.example.mealtracker;

import android.widget.TextView;

public class MyMealsExampleItem {

    private TextView mText1;
    private TextView mText2;
    private TextView mText3;

    public MyMealsExampleItem(TextView text1, TextView text2, TextView text3) {
        mText1 = text1;
        mText2 = text2;
        mText3 = text3;
    }

    public TextView getText1() {
        return mText1;
    }

    public TextView getText2() {
        return mText2;
    }

    public TextView getText3() {
        return mText3;
    }
}
