package com.example.mealtracker.UI;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mealtracker.AppLogic.AccountManager;
import com.example.mealtracker.DAO.Database;
import com.example.mealtracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    private TextInputLayout textInputUsername, textInputFirstName, textInputLastName, textInputEmail, textInputPassword, textInputConfirmPassword;
    private EditText editUsername, editFirstName, editLastName, editEmail, editPassword, editConfirmPassword;


    public void onCreateAcctBtnClick(View view) {
        Intent goToSetupHealthInfo = new Intent(getApplicationContext(), setupHealthInfo.class);
        Intent goToVerifyRegisteredEmail = new Intent(getApplicationContext(), VerifyRegisteredEmail.class);

        // access to textInputLayout
        textInputUsername = findViewById(R.id.txtUsername);
        textInputFirstName = findViewById(R.id.txtFirstName);
        textInputLastName = findViewById(R.id.txtLastName);
        textInputEmail = findViewById(R.id.txtEmail);
        textInputPassword = findViewById(R.id.txtPassword);
        textInputConfirmPassword = findViewById(R.id.txtConfirmPassword);

        editUsername = findViewById(R.id.editUsername);
        editFirstName = findViewById(R.id.editFirstName);
        editLastName = findViewById(R.id.editLastName);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        editConfirmPassword = findViewById(R.id.editConfirmPassword);

        //get string from input
        String usernameInput = textInputUsername.getEditText().getText().toString().trim();
        String firstNameInput = textInputFirstName.getEditText().getText().toString().trim();
        String lastNameInput = textInputLastName.getEditText().getText().toString().trim();
        String emailInput = textInputEmail.getEditText().getText().toString().trim();
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();
        String confirmPasswordInput = textInputConfirmPassword.getEditText().getText().toString().trim();

        // just for counting length and comparing passwords
        String password = textInputPassword.getEditText().getText().toString().trim();
        String confirmPassword = textInputConfirmPassword.getEditText().getText().toString().trim();

        if (TextUtils.isEmpty(usernameInput)) {
            textInputUsername.setError("Username is required.");
            return;
        }

        if (TextUtils.isEmpty(firstNameInput)){
            textInputFirstName.setError("First Name is required.");
            return;
        }

        if (TextUtils.isEmpty(lastNameInput)){
            textInputLastName.setError("Last Name is required.");
            return;
        }

        if (TextUtils.isEmpty(emailInput)){
            textInputEmail.setError("Email is required.");
            return;
        }

        if (TextUtils.isEmpty(passwordInput)){
            textInputPassword.setError("Password is required.");
            return;
        }

        if (password.length()<6){
            textInputPassword.setError("Password must be at least 6 characters.");
            return;
        }

        if(password.equals(confirmPassword)==false) {
            textInputConfirmPassword.setError("Passwords do not match");
            return;
        }

        HashMap<String,String> registerInfo  = new HashMap<String,String>();
        registerInfo.put("username",usernameInput);
        registerInfo.put("firstName",firstNameInput);
        registerInfo.put("lastName",lastNameInput);
        registerInfo.put("email",emailInput);
        registerInfo.put("password",passwordInput);
        registerInfo.put("confirmPassword",confirmPasswordInput);
        FirebaseAuth firebaseAuth = AccountManager.getSingleton().getFirebaseAuth();
        firebaseAuth.createUserWithEmailAndPassword(emailInput,passwordInput).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Log.d("login","successful");
                    Toast.makeText(Register.this, "User created.", Toast.LENGTH_SHORT).show();
                    Log.d("login2","successful2");
                    //startActivity(new Intent(getApplicationContext(), setupHealthInfo.class));
                    Log.d("verifyemail","hello");
                    //Account newAccount = new Account(usernameInput,firstNameInput,lastNameInput,emailInput,passwordInput);
                    //Database database = Database.getSingleton();
                    //database.postNewAccount(newAccount);

                    startActivity(goToVerifyRegisteredEmail);
                    Log.d("verifyemail","successful");
                    Database.getSingleton().postNewAccount();

                    //startActivity(goToSetupHealthInfo);
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

}