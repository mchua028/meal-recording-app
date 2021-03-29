package com.example.healthtracker.business_layer;

import com.example.healthtracker.data_access_layer.HealthInfo;

import java.util.HashMap;

public class HealthInfoManager {

	private static HealthInfoManager singleton = null;

	public HealthInfo getHealthInfo() {
		HealthInfo healthInfo = new HealthInfo();
		return healthInfo.getHeight(),
		healthInfo.getWeight(),
		healthInfo.getAge(),
		healthInfo.getGoalWeight(),
		healthInfo.getGender(),
		healthInfo.getDailyActivityLevel(),
		healthInfo.getSuggestCalorieIntake();
		// TODO - implement com.example.healthtracker.business_layer.HealthInfoManager.getHealthInfo
		throw new UnsupportedOperationException();
	}

	public static HealthInfoManager getSingleton() {
		return singleton;
	}

	private HealthInfoManager HealthInfoManager() {
		//HashMap<String, String> info = new HashMap<String, String>();
		//info.put()
		// TODO - implement com.example.healthtracker.business_layer.HealthInfoManager.com.example.healthtracker.business_layer.HealthInfoManager
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param info
	 */
	public void setHealthInfo(HashMap<String, String> info) {
		healthInfo.setHeight(info.get("height"));
		healthInfo.setWeight(info.get("weight"));
		healthInfo.setAge(info.get("age"));
		healthInfo.setGoalWeight(info.get("goal weight"));
		Gender gender = new Gender():
		healthInfo.setGender(gender.valueOf(info.get("gender")));
		Activity activity = new Activity();
		healthInfo.setDailyActivityLevel(activity.valueOf(info.get("activity")));
		healthInfo.setSuggestCalorieIntake(info.get("suggested calorie intake"));

		// TODO - implement com.example.healthtracker.business_layer.HealthInfoManager.setHealthInfo
		throw new UnsupportedOperationException();
	}

	public void updateCache() {
		// TODO - implement com.example.healthtracker.business_layer.HealthInfoManager.updateCache
		throw new UnsupportedOperationException();
	}

}