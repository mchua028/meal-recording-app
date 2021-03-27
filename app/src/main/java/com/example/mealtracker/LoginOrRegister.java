package com.example.mealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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