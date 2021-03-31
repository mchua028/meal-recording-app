package com.example.mealtracker;

//import com.example.healthtracker.data_access_layer.HealthInfo;

import java.util.HashMap;

public class HealthInfoManager {

    private static HealthInfoManager singleton = null;

    public HealthInfo getHealthInfo() {
        HealthInfo healthInfo = new HealthInfo();
        /*return healthInfo.getHeight(),
        healthInfo.getWeight(),
                healthInfo.getAge(),
                healthInfo.getGoalWeight(),
                healthInfo.getGender(),
                healthInfo.getDailyActivityLevel(),
                healthInfo.getSuggestCalorieIntake();*/
        // TODO - implement com.example.mealtracker.business_layer.HealthInfoManager.getHealthInfo
        throw new UnsupportedOperationException();
    }

    public static HealthInfoManager getSingleton() {
        return singleton;
    }

    private HealthInfoManager HealthInfoManager() {
        // TODO - implement com.example.healthtracker.business_layer.HealthInfoManager.com.example.healthtracker.business_layer.HealthInfoManager
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param info
     */
    public void setHealthInfo(HashMap<String, String> info) {
        HealthInfo healthInfo = new HealthInfo();
        healthInfo.setHeight(Double.parseDouble(info.get("height")));
        healthInfo.setWeight(Double.parseDouble(info.get("weight")));
        healthInfo.setAge(Integer.parseInt(info.get("age")));
        healthInfo.setGoalWeight(Double.parseDouble(info.get("goal weight")));
        //Gender gender = new Gender();
        healthInfo.setGender(Gender.valueOf(info.get("gender")));
        //Activity activity = new Activity();
        healthInfo.setDailyActivityLevel(Activity.valueOf(info.get("activity")));
        healthInfo.calculateCalorie();

        // TODO - implement com.example.healthtracker.business_layer.HealthInfoManager.setHealthInfo
        throw new UnsupportedOperationException();
    }}