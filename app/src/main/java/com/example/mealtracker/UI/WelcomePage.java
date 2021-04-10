package com.example.mealtracker.UI;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealtracker.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i=new Intent(WelcomePage.this,MainActivity.class);
                startActivity(i);
            }
        }, 5000);
    }
}