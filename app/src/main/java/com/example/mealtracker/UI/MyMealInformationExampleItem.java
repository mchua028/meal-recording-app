package com.example.mealtracker.UI;

import android.widget.TextView;

public class MyMealInformationExampleItem {

    private TextView mText1;
    private TextView mText2;
    private TextView mText3;
    private TextView mText4;

    public MyMealInformationExampleItem(TextView text1, TextView text2, TextView text3, TextView text4) {
        mText1 = text1;
        mText2 = text2;
        mText3 = text3;
        mText4 = text4;
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

    public TextView getText4() { return mText4; }
}
