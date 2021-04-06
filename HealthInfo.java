package com.example.mealtracker;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.HashMap;

public class HealthInfo {

    private double height;
    private double weight;
    private int age;
    private double goalWeight;
    private Gender gender;
    private Activity dailyActivityLevel;
    private int suggestCalorieIntake;

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGoalWeight() {
        return this.goalWeight;
    }

    public void setGoalWeight(double goalWeight) {
        this.goalWeight = goalWeight;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Activity getDailyActivityLevel() {
        return this.dailyActivityLevel;
    }

    public void setDailyActivityLevel(Activity dailyActivityLevel) {
        this.dailyActivityLevel = dailyActivityLevel;
    }

    public int getSuggestCalorieIntake() {
        return this.suggestCalorieIntake;
    }

    public void setSuggestCalorieIntake(int suggestCalorieIntake) {
        this.suggestCalorieIntake = suggestCalorieIntake;
    }

    public void calculateCalorie() {
        double bmi;
        bmi = weight/(height*height);
        if (getGender()==Gender.FEMALE){

        }

        if (getGender()==Gender.MALE){

        }

        //setSuggestCalorieIntake()
    }

    /**
     * Post health information to the server.
     * Author : Wang Binli
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addToServer() {
        Database.getSingleton().postHealthInfo(this);
    }

    /**
     * Update health information to the server.
     * Author : Wang Binli
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateToServer() {
        Database.getSingleton().updateHealthInfo(this);
    }

    /**
     *
     * @param newInfo
     */
    public void updateAttributes(HashMap<String, String> newInfo) {
        setHeight(Double.parseDouble(newInfo.get("height")));
        setWeight(Double.parseDouble(newInfo.get("weight")));
        setAge(Integer.parseInt(newInfo.get("age")));
        setGoalWeight(Double.parseDouble(newInfo.get("goal weight")));
        //Gender gender = new Gender():
        setGender(gender.valueOf(newInfo.get("gender")));
        //Activity activity = new Activity();
        setDailyActivityLevel(Activity.valueOf(newInfo.get("activity")));
        calculateCalorie();
        // TODO - implement com.example.healthtracker.data_access_layer.HealthInfo.updateAttributes
        throw new UnsupportedOperationException();
    }

}
