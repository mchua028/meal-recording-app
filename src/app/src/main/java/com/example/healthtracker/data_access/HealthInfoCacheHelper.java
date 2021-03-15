package com.example.healthtracker.data_access;

import java.util.HashMap;

enum Gender {
    FEMALE,
    MALE,
    OTHERS
}


enum Activity {
    NONE,
    LITTLE,
    MODERATE,
    HIGH
}



public class HealthInfoCacheHelper {
    private double height;
    private double weight;
    private double goalWeight;
    private int age;
    private Gender gender;
    private Activity dailyActivityLevel;
    private String stringPath;
    static private HealthInfoCacheHelper singleton;

    public HealthInfoCacheHelper(double height, double weight, double goalWeight, int age, Gender gender, Activity dailyActivityLevel, String stringPath) {
        this.height = height;
        this.weight = weight;
        this.goalWeight = goalWeight;
        this.age = age;
        this.gender = gender;
        this.dailyActivityLevel = dailyActivityLevel;
        this.stringPath = stringPath;
    }

    static public HealthInfoCacheHelper getSingleton() {

    }

    public void updateCache(HashMap<String, String> newInfo) {

    }

}

