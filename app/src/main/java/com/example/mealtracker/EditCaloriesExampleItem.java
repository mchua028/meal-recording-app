package com.example.mealtracker;

public class EditCaloriesExampleItem {

    private String mText1;
    private String mText2;
    private int mCancel;

    public EditCaloriesExampleItem(String text1, String text2, int cancel) {
        mText1 = text1;
        mText2 = text2;
        mCancel = cancel;
    }

    public String getText1() {
        return mText1;
    }

    public String getText2() {
        return mText2;
    }

    public int getCancel() { return mCancel; }
}
