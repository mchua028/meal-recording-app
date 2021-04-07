package com.example.mealtracker;

import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class EditCaloriesExampleItem {

    private TextView mText1;
    private TextView mText2;
    private ImageView mCancel;

    public EditCaloriesExampleItem(TextView text1, TextView text2, ImageView cancel) {
        mText1 = text1;
        mText2 = text2;
        mCancel = cancel;
    }

    public TextView getText1() {
        return mText1;
    }

    public TextView getText2() {
        return mText2;
    }

    public ImageView getCancel() { return mCancel; }
}
