package com.example.healthtracker.data_access;

import java.util.HashMap;

public class HealthInfoCacheHelper {

	private String StringPath;
	private static HealthInfoCacheHelper singleton;

	public static HealthInfoCacheHelper getSingleton() {
		return singleton;
	}

	private HealthInfoCacheHelper() {
		// TODO - implement com.example.healthtracker.data_access.HealthInfoCacheHelper.com.example.healthtracker.data_access.HealthInfoCacheHelper
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param newInfo
	 */
	public void updateCache(HashMap<String, String> newInfo) {
		// TODO - implement com.example.healthtracker.data_access.HealthInfoCacheHelper.updateCache
		throw new UnsupportedOperationException();
	}

}