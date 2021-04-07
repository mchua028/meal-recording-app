package com.example.mealtracker.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mealtracker.R;

public class LoginOrRegister extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_register);
    }

    // onClick listener
    public void onRegisterBtnClick (View view) {
        startActivity(new Intent(view.getContext(), Register.class));
    }

    public void onLoginBtnClick (View view) {
        startActivity(new Intent(view.getContext(), Login.class));
    }

}