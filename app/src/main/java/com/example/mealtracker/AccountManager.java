package com.example.mealtracker;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.*;
import java.io.*;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * skeleton
 */
public class AccountManager {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private static AccountManager singleton;
    private boolean isSuccessfulLoggedIn = false;
    private boolean isSuccessfulRegistered = false;

    public boolean getIsSuccessfulLoggedIn() {
        return this.isSuccessfulLoggedIn;
    }

    public void setIsSuccessfulLoggedIn(boolean isSuccessfulLoggedIn) {
        this.isSuccessfulLoggedIn = isSuccessfulLoggedIn;
    }

    public boolean getIsSuccessfulRegistered() {
        return this.isSuccessfulRegistered;
    }

    public void setIsSuccessfulRegistered(boolean isSuccessfulRegistered) {
        this.isSuccessfulRegistered = isSuccessfulRegistered;
    }

    public AccountManager() {}

    /**
     *
     * @param registerInfo
     */
    public boolean registerAccount(HashMap<String, String> registerInfo) {
        String username = registerInfo.get("username");
        String firstName = registerInfo.get("firstName");
        String lastName = registerInfo.get("lastName");
        String email = registerInfo.get("email");
        String password = registerInfo.get("password");
        String confirmPassword = registerInfo.get("confirmPassword");
        Log.d("password", password);
        /*firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Toast.makeText(Register.this,"User created.",Toast.LENGTH_SHORT)
            }
        });
        Log.d("login","successful");


         */
        return true;


    }




    /**
     *
     * @param email
     * @param password
     */
    public boolean logInAccount(String email, String password) {
      //  firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new onCompleteListener<AuthResult>);
        return true;

    }

    public void LogInViaFacebook() {
        // TODO - implement com.example.healthtracker.business_layer.AccountManager.LogInViaFacebook
        throw new UnsupportedOperationException();
    }

    public void LogInViaGoogle() {
        // TODO - implement com.example.healthtracker.business_layer.AccountManager.LogInViaGoogle
        throw new UnsupportedOperationException();
    }

    public void logInViaCacheInfo() {
        // TODO - implement com.example.healthtracker.business_layer.AccountManager.logInViaCacheInfo
        throw new UnsupportedOperationException();
    }

    public void registerUsingGoogle() {
        // TODO - implement com.example.healthtracker.business_layer.AccountManager.registerUsingGoogle
        throw new UnsupportedOperationException();
    }

    public void registerUsingFacebook() {
        // TODO - implement com.example.healthtracker.business_layer.AccountManager.registerUsingFacebook
        throw new UnsupportedOperationException();
    }

    public static AccountManager getSingleton() {
        return singleton;
    }

    /**
     *
     * @param newPassword
     * @param verificationCode
     */
    public void changePassword(String newPassword, String verificationCode) {
        // TODO - implement com.example.healthtracker.business_layer.AccountManager.changePassword
        throw new UnsupportedOperationException();
    }

    private boolean verifyUser() {
        // TODO - implement com.example.healthtracker.business_layer.AccountManager.verifyUser
        throw new UnsupportedOperationException();
    }

}