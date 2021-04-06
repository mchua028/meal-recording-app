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

    public AccountManager() {
    }


    /**
     * @param email
     * @param password
     */
    public boolean logInAccount(String email, String password) {
        //  firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new onCompleteListener<AuthResult>);
        return true;

    }

    public void logInViaCacheInfo() {
        // TODO - implement com.example.healthtracker.business_layer.AccountManager.logInViaCacheInfo
        throw new UnsupportedOperationException();
    }


    public static AccountManager getSingleton() {
        return singleton;
    }

    /**
     * @param newPassword
     * @param verificationCode
     */
    public void forgotPassword(String newPassword, String verificationCode) {
        // TODO - implement com.example.healthtracker.business_layer.AccountManager.changePassword

    }

}