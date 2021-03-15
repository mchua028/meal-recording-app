package com.example.mealtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onCreateAcctBtnClick(View view) {
        // access to TextView
        TextView txtUsername = findViewById(R.id.txtUsername);
        TextView txtFirstName = findViewById(R.id.txtFirstName);
        TextView txtLastName = findViewById(R.id.txtLastName);
        TextView txtEmail = findViewById(R.id.txtEmail);
        TextView txtPassword = findViewById(R.id.txtPassword);
        TextView txtConfirmPassword = findViewById(R.id.txtConfirmPassword);

        EditText editUsername = findViewById(R.id.txtUsername);
        EditText editFirstName = findViewById(R.id.txtFirstName);
        EditText editLastName = findViewById(R.id.txtLastName);
        EditText editEmail = findViewById(R.id.txtEmail);
        EditText editPassword = findViewById(R.id.txtPassword);
        EditText editConfirmPassword = findViewById(R.id.txtConfirmPassword);

        // change text
        txtUsername.setText("Username: " + editUsername.getText().toString());
        txtFirstName.setText("First Name: " + editFirstName.getText().toString());
        txtLastName.setText("Last Name: " + editLastName.getText().toString());
        txtEmail.setText("Email: " + editEmail.getText().toString());
        txtPassword.setText("Password: " + editPassword.getText().toString());
        txtConfirmPassword.setText("Confirm Password: " + editConfirmPassword.getText().toString());
    }


    public void onAlrHaveAcctBtnClick (View view) {
        // return to log in page
        startActivity(new Intent(view.getContext(), GoogleLogin.class));
    }

}