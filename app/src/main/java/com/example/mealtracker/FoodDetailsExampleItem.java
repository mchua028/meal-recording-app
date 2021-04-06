package com.example.mealtracker;

import com.google.android.material.textfield.TextInputLayout;

public class FoodDetailsExampleItem {

    private TextInputLayout mText1;
    private TextInputLayout mText2;

    public FoodDetailsExampleItem(TextInputLayout text1, TextInputLayout text2) {
        mText1 = text1;
        mText2 = text2;
    }

    public TextInputLayout getText1() {
        return mText1;
    }

    public TextInputLayout getText2() {
        return mText2;
    }
}
