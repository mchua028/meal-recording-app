package com.example.mealtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onLoginButtonClick (View view) {
        TextView txtEmail = findViewById(R.id.txtLoginEmailUser);
        TextView txtPassword = findViewById(R.id.txtLoginPassword);

        EditText editEmail = findViewById(R.id.txtLoginEmailUser);
        EditText editPassword = findViewById(R.id.txtLoginPassword);

        //get string from input
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            editEmail.setError("Email is required.");
            return;
        }

        if (TextUtils.isEmpty(password)){
            editPassword.setError("Password is required.");
            return;
        }

        //AccountManager accountManager = new AccountManager();
        //if(accountManager.logInAccount(email,password)) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Log.d("loginhellooooooooooo","successful");
                    Toast.makeText(Login.this, "Login successful.", Toast.LENGTH_SHORT).show();
                    Log.d("login2","successful2");
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                else{
                    Toast.makeText(Login.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    public void onLoginViaGoogleButtonClick (View view) {

        startActivity(new Intent(view.getContext(), GoogleLogin.class));
    }
//FB Login
    /*public void onLoginViaFBButtonClick (View view) {
        // go to main page
        startActivity(new Intent(view.getContext(), FBLogin.class));
    }
     */
}