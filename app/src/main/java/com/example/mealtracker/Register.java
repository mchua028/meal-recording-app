package com.example.mealtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

        //get string from input
        String username = editUsername.getText().toString().trim();
        String firstName = editFirstName.getText().toString().trim();
        String lastName = editLastName.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        String confirmPassword = editConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username)){
            editUsername.setError("Username is required.");
            return;
        }

        if (TextUtils.isEmpty(firstName)){
            editFirstName.setError("First Name is required.");
            return;
        }

        if (TextUtils.isEmpty(lastName)){
            editLastName.setError("Last Name is required.");
            return;
        }

        if (TextUtils.isEmpty(email)){
            editEmail.setError("Email is required.");
            return;
        }

        if (TextUtils.isEmpty(password)){
            editPassword.setError("Password is required.");
            return;
        }

        if (password.length()<6){
            editPassword.setError("Password must be at least 6 characters.");
            return;
        }

        if(password.equals(confirmPassword)==false) {
            editConfirmPassword.setError("Passwords do not match");
            return;
        }

        //verify email


        AccountManager accountManager = new AccountManager();
        HashMap<String,String> registerInfo  = new HashMap<String,String>();
        registerInfo.put("username",username);
        registerInfo.put("firstName",firstName);
        registerInfo.put("lastName",lastName);
        registerInfo.put("email",email);
        registerInfo.put("password",password);
        registerInfo.put("confirmPassword",confirmPassword);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Log.d("login","successful");
                    Toast.makeText(Register.this, "User created.", Toast.LENGTH_SHORT).show();
                    Log.d("login2","successful2");
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                else{
                    Toast.makeText(Register.this,"Error!"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                }

            }
        });

    }



    public void onAlrHaveAcctBtnClick (View view) {
        // return to log in page
        startActivity(new Intent(view.getContext(), Login.class));
    }

    //TODO: Connect to input health information

}