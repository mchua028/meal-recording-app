package com.example.mealtracker.AppLogic;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.mealtracker.Activity;
import com.example.mealtracker.DAO.Database;
import com.example.mealtracker.Gender;
import com.example.mealtracker.DAO.HealthInfo;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

public class HealthInfoManager {


    private static HealthInfoManager singleton = null;

    public HealthInfo getHealthInfo() {
        HealthInfo healthInfo = HealthInfo.getSingleton();
        healthInfo.getHeight();
        healthInfo.getWeight();
        healthInfo.getAge();
        healthInfo.getGoalWeight();
        healthInfo.getGender();
        healthInfo.getDailyActivityLevel();
        healthInfo.getSuggestCalorieIntake();
        return healthInfo;

    }

    public static HealthInfoManager getSingleton() {
        if (singleton == null) {
            singleton = new HealthInfoManager();
        }
        return singleton;
    }


    /**
     * Parses the input as HashMap format and sends to the server
     * @param info, HashMap\<String, String\>
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void setHealthInfo(HashMap<String, String> info) {
        HealthInfo healthInfo = HealthInfo.getSingleton();
        parseHealthInfo(info);
        healthInfo.addToServer();
    }

    public static HealthInfo parseHealthInfo(HashMap<String, String> info) {
        HealthInfo healthInfo = HealthInfo.getSingleton();
        healthInfo.setHeight(Double.parseDouble(info.get("height")));
        healthInfo.setWeight(Double.parseDouble(info.get("weight")));
        healthInfo.setAge(Integer.parseInt(info.get("age")));
        healthInfo.setGoalWeight(Double.parseDouble(info.get("goal weight")));
        ;
        //Gender gender = new Gender();
        if (info.get("gender") == "Female"){
            healthInfo.setGender(Gender.FEMALE);
        }
        else if (info.get("gender") == "Male"){
            healthInfo.setGender(Gender.MALE);
        }
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

        healthInfo.calculateCalorie();  // update to healthInfo
        return healthInfo;
    }

    /**
     * @return the suggested Calorie intake of the user.
     */
    public double getSuggestedCalorie() {
        return Database.getSingleton().queryHealthInfo().getSuggestedCalorie();
    }
}