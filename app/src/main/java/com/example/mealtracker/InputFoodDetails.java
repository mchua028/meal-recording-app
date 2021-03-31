package com.example.mealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InputFoodDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_food_details);

        Button onClickAddMoreFoodBtn = (Button) findViewById(R.id.addMoreFoodBtn);
        onClickAddMoreFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: add more empty food cardviews
            }
        });

        Button onClickRemoveFoodBtn = (Button) findViewById(R.id.removeFoodBtn);
        onClickRemoveFoodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: make cardview disappear
            }
        });

        Button onClickCountCaloriesBtn = (Button) findViewById(R.id.countCaloriesBtn);
        onClickCountCaloriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(v.getContext(), countCalories.class));
            }
        });

        Button onClickCancelBtn = (Button) findViewById(R.id.cancelBtn);
        onClickCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}