package com.example.mealtracker;

import com.example.mealtracker.Exceptions.EmptyInputException;
import com.example.mealtracker.Exceptions.EmptyResultException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class MealRecordManager {

    private static MealRecordManager singleton;

    public MealRecordManager getSingleton() {
        return this.singleton;
    }

    private ArrayList<MealRecord> mealRecord = new ArrayList<MealRecord>();

    /**
     *
     * @param foodName
     */
    public Food query(String foodName) {
        Food food = new Food();
        try {
            return food.searchFood(foodName);
        } catch (EmptyInputException | EmptyResultException e) {
            e.printStackTrace();
        }
        return new Food();
    }

    /**
     *
     * @param foods
     */
    public void addMealRecord(Food foods) {
        MealRecord mealRecord = new MealRecord();
        mealRecord.getFoods().add(foods);

        // TODO - implement com.example.healthtracker.business_layer.MealRecordManager.addMealRecord
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param mealRecordId
     */
    public void deleteMealRecord(int mealRecordId) {
        ArrayList<MealRecord> mealRecord = new ArrayList<MealRecord>();
        mealRecord.get(mealRecordId).deleteFromServer();
        // TODO - implement com.example.healthtracker.business_layer.MealRecordManager.deleteMealRecord
    }

    /**
     *
     * @param img
     */
    /*public Food[] queryFoodInImage(Image img) {
        ArrayList<Food> foodImage = new ArrayList<Food>();
        return foodImage.searchFoods(img);
        // TODO - implement com.example.healthtracker.business_layer.MealRecordManager.queryFoodInImage
        throw new UnsupportedOperationException();
    }
*/
    /**
     *
     * @param foodInfo
     */
    public void addFood(HashMap<String, String> foodInfo) {
        Food newFood = new Food();
        newFood.setName(foodInfo.get("name"));
        Nutrient newFoodNutrients = new Nutrient();
        newFood.setNutrients(newFoodNutrients);

        //food.setNutrients(foodInfo.get("nutrients"));
        //food.addFoodToServer();
        // TODO - implement com.example.healthtracker.business_layer.MealRecordManager.addFood
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param startDate
     * @param endDate
     */
    public ArrayList<MealRecord> query(Date startDate, Date endDate) {
        ArrayList<MealRecord> mealRecord = new ArrayList<MealRecord>();
        //return mealRecord.queryByDate(startDate, endDate);
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

    public double calculateCalorieQuotaRemainingToday() {
        double calorieConsumed = 0;
        for (int i=0; i<mealRecord.size(); i++){ //TODO - iterate through the meal records in that day
            for (int j=0; j<mealRecord.get(i).getFoods().size(); j++) {
                calorieConsumed += mealRecord.get(i).getNutrient().getCaloriePer100g() * mealRecord.get(i).getFoods().get(j).getActualIntake();
            }
        }
        HealthInfo healthInfo = new HealthInfo();
        double calorieSuggested = healthInfo.getSuggestCalorieIntake();
        return calorieSuggested - calorieConsumed;
        // TODO - implement com.example.healthtracker.business_layer.MealRecordManager.calculateCalorieQuotaRemainingToday
    }

    public Nutrient calculateTotalNutrient(){
        Nutrient totalConsumed = new Nutrient();

        for (int i=0; i<mealRecord.size(); i++){
            totalConsumed.setFat(totalConsumed.getFat()+mealRecord.get(i).getNutrient().getFat());
            totalConsumed.setCholesterol(totalConsumed.getCholesterol()+mealRecord.get(i).getNutrient().getCholesterol());
            totalConsumed.setSodium(totalConsumed.getSodium()+mealRecord.get(i).getNutrient().getSodium());
            totalConsumed.setPotassium(totalConsumed.getPotassium()+mealRecord.get(i).getNutrient().getPotassium());
            totalConsumed.setSugar(totalConsumed.getSugar()+mealRecord.get(i).getNutrient().getSugar());
            totalConsumed.setDietaryFibre(totalConsumed.getDietaryFibre()+mealRecord.get(i).getNutrient().getDietaryFibre());
            totalConsumed.setProtein(totalConsumed.getProtein()+mealRecord.get(i).getNutrient().getProtein());
            totalConsumed.setCalcium(totalConsumed.getCalcium()+mealRecord.get(i).getNutrient().getCalcium());
            totalConsumed.setVitaminC(totalConsumed.getVitaminC()+mealRecord.get(i).getNutrient().getVitaminC());
            totalConsumed.setIron(totalConsumed.getIron()+mealRecord.get(i).getNutrient().getIron());
            totalConsumed.setCobalamin(totalConsumed.getCobalamin()+mealRecord.get(i).getNutrient().getCobalamin());
            totalConsumed.setMagnesium(totalConsumed.getMagnesium()+mealRecord.get(i).getNutrient().getMagnesium());
        }
        return totalConsumed;
    }
}