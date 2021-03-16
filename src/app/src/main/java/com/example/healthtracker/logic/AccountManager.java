package com.example.healthtracker.logic;

import java.util.HashMap;

public class AccountManager {
    static private AccountManager singleton;
    private boolean isSuccessfulLogin = false;
    private Account account = null;
    
    private AccountManager() {
    }

    public void registerAccount(HashMap<String, String> registerInfo) {

    }

    public void loginAccount(String username, String password) {

    }

    public void loginViaFacebook() {

    }

    public void loginViaGoogle() {

    }

    public void loginViaCacheInfo() {

    }

    public void registerUsingGoogle() {

    }

    public void registerUsingFacebook() {

    }

    static public AccountManager getSingleton() {

    }

    public void changePassword(String newPassword, String verificationCode) {

    }

    private boolean verifyUser() {

    }
}
