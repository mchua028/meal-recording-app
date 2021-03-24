package com.example.healthtracker.app_logic;

import com.example.healthtracker.data_access.HealthInfo;

import java.util.HashMap;

public class HealthInfoManager {

	private static HealthInfoManager singleton = null;

	public HealthInfo getHealthInfo() {
		// TODO - implement com.example.healthtracker.app_logic.HealthInfoManager.getHealthInfo
		throw new UnsupportedOperationException();
	}

	public static HealthInfoManager getSingleton() {
		return singleton;
	}

	private HealthInfoManager() {
		// TODO - implement com.example.healthtracker.app_logic.HealthInfoManager.com.example.healthtracker.app_logic.HealthInfoManager
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param info
	 */
	public void setHealthInfo(HashMap<String, String> info) {
		// TODO - implement com.example.healthtracker.app_logic.HealthInfoManager.setHealthInfo
		throw new UnsupportedOperationException();
	}

}