package com.example.healthtracker.data_access;

import java.util.HashMap;

public class AccountInfoCacheHelper {

	private String cacheFilePath;
	private static AccountInfoCacheHelper singleton;
	private String username;
	private String password;

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	/**
	 * private initializer
	 */
	private AccountInfoCacheHelper() {
		// TODO - implement com.example.healthtracker.data_access.AccountInfoCacheHelper.com.example.healthtracker.data_access.AccountInfoCacheHelper
		throw new UnsupportedOperationException();
	}

	public Account parseAccountInfo() {
		// TODO - implement com.example.healthtracker.data_access.AccountInfoCacheHelper.parseAccountInfo
		throw new UnsupportedOperationException();
	}

	public static AccountInfoCacheHelper getSingleton() {
		return singleton;
	}

	/**
	 * 
	 * @param parameter
	 */
	public void updateCache(HashMap<String, String> parameter) {
		// TODO - implement com.example.healthtracker.data_access.AccountInfoCacheHelper.updateCache
		throw new UnsupportedOperationException();
	}

}