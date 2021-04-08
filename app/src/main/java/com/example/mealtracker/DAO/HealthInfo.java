package com.example.mealtracker.DAO;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.mealtracker.Activity;
import com.example.mealtracker.AppLogic.MealRecordManager;
import com.example.mealtracker.Gender;

import java.util.HashMap;

public class HealthInfo {

    private double height;
    private double weight;
    private int age;
    private double goalWeight;
    private Gender gender;
    private Activity dailyActivityLevel;
    private double suggestCalorieIntake = 1200;

    private HealthInfo(){};

    static public HealthInfo getSingleton() {
        if (singleton == null) {
            singleton = new HealthInfo();
        }
        return singleton;
    }
    private static HealthInfo singleton;

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
        //Database.getSingleton().retrieveHealthInfo(singleton);
        //Log.d("gethealthinfo", Double.toString(this.suggestCalorieIntake));
        return this.suggestCalorieIntake;
    }

    public void setSuggestCalorieIntake(double suggestCalorieIntake) {
        Log.d("sethealthinfo", Double.toString(suggestCalorieIntake));
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
        if (bmr < 1200)
            bmr = 1200; //minimum calories required per day for any type of people

        setSuggestCalorieIntake(bmr);
        Log.d("calorie", Double.toString(suggestCalorieIntake));
        Log.d("calorie", Double.toString(getSuggestCalorieIntake()));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addToServer(){
        Database.getSingleton().postHealthInfo(this);
    }

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

        if (newInfo.get("gender") == "Female"){
            setGender(Gender.FEMALE);
        }
        else if (newInfo.get("gender") == "Male"){
            setGender(Gender.MALE);
        }
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

    public double getSuggestedCalorie() {
        return Database.getSingleton().queryHealthInfo().suggestCalorieIntake;
    }
}

