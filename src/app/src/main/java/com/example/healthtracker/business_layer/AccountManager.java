package com.example.healthtracker.business_layer;

import java.util.HashMap;

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

	private AccountManager AccountManager() {
		// TODO - implement com.example.healthtracker.business_layer.AccountManager.com.example.healthtracker.business_layer.AccountManager
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param registerInfo
	 */
	public void registerAccount(HashMap<String, String> registerInfo) {
		// TODO - implement com.example.healthtracker.business_layer.AccountManager.registerAccount
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param username
	 * @param password
	 */
	public void logInAccount(String username, String password) {
		// TODO - implement com.example.healthtracker.business_layer.AccountManager.logInAccount
		throw new UnsupportedOperationException();
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