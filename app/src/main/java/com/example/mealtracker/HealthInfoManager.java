package com.example.mealtracker;

//import com.example.healthtracker.data_access_layer.HealthInfo;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

public class HealthInfoManager {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    private static HealthInfoManager singleton = null;

    public HealthInfo getHealthInfo() {
        HealthInfo healthInfo = new HealthInfo();
        healthInfo.getHeight();
        healthInfo.getWeight();
        healthInfo.getAge();
        healthInfo.getGoalWeight();
        healthInfo.getGender();
        healthInfo.getDailyActivityLevel();
        healthInfo.getSuggestCalorieIntake();
        return healthInfo;
        // TODO - implement com.example.mealtracker.business_layer.HealthInfoManager.getHealthInfo

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
        Log.d("manager", "went into sethealthinfo");
        HealthInfo healthInfo = new HealthInfo();
        healthInfo.setHeight(Double.parseDouble(info.get("height")));
        healthInfo.setWeight(Double.parseDouble(info.get("weight")));
        healthInfo.setAge(Integer.parseInt(info.get("age")));
        healthInfo.setGoalWeight(Double.parseDouble(info.get("goal weight")));
        Log.d("manager", "sethealthinfo1");
        //Gender gender = new Gender();
        if (info.get("gender") == "Female"){
            healthInfo.setGender(Gender.FEMALE);
        }
        else if (info.get("gender") == "Male"){
            healthInfo.setGender(Gender.MALE);
        }
        Log.d("manager", "sethealthinfo2");
        //Activity activity = new Activity();
        if (info.get("activity") == "None"){
            healthInfo.setDailyActivityLevel(Activity.NONE);
        }

        else if (info.get("activity") == "Little"){
            healthInfo.setDailyActivityLevel(Activity.LITTLE);
        }

        else if (info.get("activity") == "Moderate (Min 2 hours/day of activity)"){
            healthInfo.setDailyActivityLevel(Activity.MODERATE);
        }

        else if (info.get("activity") == "High (Min 4 hours/day of activity)"){
            healthInfo.setDailyActivityLevel(Activity.HIGH);
        }
        Log.d("manager", "sethealthinfo3");

        healthInfo.calculateCalorie();

        Log.d("manager", "finish sethealthinfo");

        // TODO - implement com.example.healthtracker.business_layer.HealthInfoManager.setHealthInfo
        //throw new UnsupportedOperationException();
    }

}