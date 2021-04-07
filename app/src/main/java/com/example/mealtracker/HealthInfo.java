package com.example.mealtracker;

import android.util.Log;

import java.util.HashMap;

public class HealthInfo {

    private double height;
    private double weight;
    private int age;
    private double goalWeight;
    private Gender gender;
    private Activity dailyActivityLevel;
    private double suggestCalorieIntake;

    public HealthInfo(){};

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

    public double getSuggestCalorieIntake() {
        return this.suggestCalorieIntake;
    }

    public void setSuggestCalorieIntake(double suggestCalorieIntake) {
        this.suggestCalorieIntake = suggestCalorieIntake;
    }

    public void calculateCalorie() {
        double bmr = 0;
        double new_weight;

        if (getGender() == Gender.FEMALE) {
            bmr = 9.247*getWeight() + 3.098*getHeight() - 4.330*getAge() + 447.593;
        }

        else if (getGender() == Gender.MALE) {
            bmr = 13.397*getWeight() + 4.799*getHeight() - 5.677*getAge() + 88.362;
        }

        if (getDailyActivityLevel() == Activity.HIGH) {
            bmr = bmr * 1.8;
        } else if (getDailyActivityLevel() == Activity.MODERATE) {
            bmr = bmr * 1.65;
        } else if (getDailyActivityLevel() == Activity.LITTLE) {
            bmr = bmr * 1.55;
        } else if (getDailyActivityLevel() == Activity.NONE){
            bmr = bmr * 1.3;
        }

        bmr = bmr - getGoalWeight()/0.45 * 3500/30; //assume one month has 30 days, to lose 0.45kg, need to reduce 3500calories in total
        suggestCalorieIntake = bmr;
    }

    public void addToServer(){
        // TODO - implement com.example.healthtracker.data_access_layer.HealthInfo.addToServer
        throw new UnsupportedOperationException();
    }

    public void updateToServer() {
        // TODO - implement com.example.healthtracker.data_access_layer.HealthInfo.updateToServer
        throw new UnsupportedOperationException();
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
        //Gender gender = new Gender();
        if (newInfo.get("gender") == "Female"){
            setGender(Gender.FEMALE);
        }
        else if (newInfo.get("gender") == "Male"){
            setGender(Gender.MALE);
        }
        Log.d("manager", "sethealthinfo2");
        //Activity activity = new Activity();
        if (newInfo.get("activity") == "None"){
            setDailyActivityLevel(Activity.NONE);
        }

        else if (newInfo.get("activity") == "Little"){
            setDailyActivityLevel(Activity.LITTLE);
        }

        else if (newInfo.get("activity") == "Moderate (Min 2 hours/day of activity)"){
            setDailyActivityLevel(Activity.MODERATE);
        }

        else if (newInfo.get("activity") == "High (Min 4 hours/day of activity)"){
            setDailyActivityLevel(Activity.HIGH);
        }

        calculateCalorie();

    }

}
