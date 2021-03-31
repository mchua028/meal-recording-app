package com.example.mealtracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class editCalories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_calories);

        // Save button
        // TODO: to connect to db to edit meal records
        Button onClickSaveBtn = (Button) findViewById(R.id.saveBtn);
        onClickSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Cancel button
        Button onClickCancelBtn = (Button) findViewById(R.id.cancelBtn);
        onClickCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}