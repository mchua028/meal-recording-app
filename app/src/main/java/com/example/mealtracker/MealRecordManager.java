package com.example.mealtracker;

import com.example.healthtracker.data_access_layer.Food;
import com.example.healthtracker.data_access_layer.MealRecord;

import java.util.Date;
import java.util.HashMap;

public class MealRecordManager {

    private static MealRecordManager singleton;

    public MealRecordManager getSingleton() {
        return this.singleton;
    }

    /**
     *
     * @param foodName
     */
    public Food query(String foodName) {
        Food food = new Food();
        return food.searchFood(foodName);
        // TODO - implement com.example.healthtracker.business_layer.MealRecordManager.query
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param foods
     */
    public void addMealRecord(Food[] foods) {
        MealRecord mealRecord = new MealRecord();
        mealRecord.updateFood(foods);

        // TODO - implement com.example.healthtracker.business_layer.MealRecordManager.addMealRecord
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param mealRecordId
     */
    public void deleteMealRecord(double mealRecordId) {
        MealRecord[] mealRecord = new MealRecord[]();
        mealRecord[mealRecordId].deleteFromServer();
        // TODO - implement com.example.healthtracker.business_layer.MealRecordManager.deleteMealRecord
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param img
     */
    public Food[] queryFoodInImage(Image img) {
        Food[] foodImage = new Food[]();
        return foodImage.searchFoods(img);
        // TODO - implement com.example.healthtracker.business_layer.MealRecordManager.queryFoodInImage
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param foodInfo
     */
    public void addFood(HashMap<String, String> foodInfo) {
        Food food = new Food();
        food.setName(foodInfo.get("name"));
        food.setNutrients(foodInfo.get("nutrients"));
        food.addFoodToServer();
        // TODO - implement com.example.healthtracker.business_layer.MealRecordManager.addFood
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param startDate
     * @param endDate
     */
    public MealRecord[] query(Date startDate, Date endDate) {
        return mealRecord.queryByDate(Date startDate, Date endDate)
        // TODO - implement com.example.healthtracker.business_layer.MealRecordManager.query
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param idInList
     * @param newInfo
     */
    public void editMealRecord(double idInList, Food[] newInfo) {

        // TODO - implement com.example.healthtracker.business_layer.MealRecordManager.editMealRecord
        throw new UnsupportedOperationException();
    }

    private MealRecordManager MealRecordManager() {
        // TODO - implement com.example.healthtracker.business_layer.MealRecordManager.com.example.healthtracker.business_layer.MealRecordManager
        throw new UnsupportedOperationException();
    }

    public int calculateCalorieQuotaRemainingToday() {
        Food food = new food();
        return food.getSuggestedIntake-food.getActualIntake;
        // TODO - implement com.example.healthtracker.business_layer.MealRecordManager.calculateCalorieQuotaRemainingToday
        throw new UnsupportedOperationException();
    }

    public Nutrients calculateNutrientRemaining(){

    }
}