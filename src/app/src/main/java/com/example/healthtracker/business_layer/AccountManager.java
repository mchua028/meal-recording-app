package com.example.healthtracker.business_layer;

import java.util.*;
import java.io.*;

import com.google.firebase.auth.FirebaseAuth;

/**
 * skeleton
 */
public class AccountManager {

	private static AccountManager singleton;
	private int isSuccessfulLoggedIn = 0;

	public int getIsSuccessfulLoggedIn() {
		return this.isSuccessfulLoggedIn;
	}

	public void setIsSuccessfulLoggedIn(int isSuccessfulLoggedIn) {
		this.isSuccessfulLoggedIn = isSuccessfulLoggedIn;
	}

	private AccountManager() {}

	/**
	 * 
	 * @param registerInfo
	 */
	public void registerAccount(HashMap<String, String> registerInfo) {

		Account account = new Account(registerInfo.get("username"),registerInfo.get("password"),
				registerInfo.get("firstname"),register.get("lastname"));
		account.registerAccount();
		logInAccount(registerInfo.get("username"),registerInfo.get("password"));
	}

	/**
	 * 
	 * @param username
	 * @param password
	 */
	public boolean logInAccount(String username, String password) {
		Account account = new Account();
		account.setUsername(username);
		account.setPassword(password);
		return(account.verifyAccountForLogin());//return to submitlogininfo in loginui, what if is from register?

	}

	public void LogInViaGoogle() {
		// TODO - implement com.example.healthtracker.business_layer.AccountManager.LogInViaGoogle
	}

	public void logInViaCacheInfo() {
		// TODO - implement com.example.healthtracker.business_layer.AccountManager.logInViaCacheInfo
	}

	public void registerUsingGoogle() {
		// TODO - implement com.example.healthtracker.business_layer.AccountManager.registerUsingGoogle
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
	}

	private boolean verifyUser() {
		// TODO - implement com.example.healthtracker.business_layer.AccountManager.verifyUser
	}

}