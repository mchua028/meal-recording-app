package com.example.mealtracker.AppLogic;

import com.example.mealtracker.DAO.Account;
import com.example.mealtracker.DAO.Database;
import com.example.mealtracker.Exceptions.RegisterError;
import com.google.firebase.auth.FirebaseAuth;

/**
 * skeleton
 */
public class AccountManager {
    public static AccountManager singleton = null;
    private boolean isSuccessfulLoggedIn = false;
    private boolean isSuccessfulRegistered = false;
    private FirebaseAuth firebaseAuth;

    private AccountManager() {
        firebaseAuth = Database.getSingleton().getmAuth();
    }

    public static AccountManager getSingleton() {
        if (singleton == null) {
            singleton = new AccountManager();
        }
        return singleton;
    }

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public static void setSingleton(AccountManager singleton) {
        AccountManager.singleton = singleton;
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


    /**
     * @param newPassword
     * @param verificationCode
     */
    public void forgotPassword(String newPassword, String verificationCode) {
        // TODO - implement com.example.healthtracker.business_layer.AccountManager.changePassword

    }

}