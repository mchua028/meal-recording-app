package com.example.mealtracker;

import android.os.Build;
import android.provider.ContactsContract;

import androidx.annotation.RequiresApi;

import com.example.mealtracker.Exceptions.EmptyInputException;
import com.example.mealtracker.Exceptions.EmptyResultException;
import com.example.mealtracker.Exceptions.RecordNotInServerException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class MealRecordManager {
    Database database = Database.getSingleton();

    private static MealRecordManager singleton;

    private MealRecord mealRecord;

    public static MealRecordManager getSingleton() {
        return singleton;
    }


    public MealRecordManager(){}

    public void setMealRecord(MealRecord mealRecord){
        this.mealRecord = mealRecord;
    }

    public MealRecord getMealRecord(){
        return mealRecord;
    }

    private ArrayList<MealRecord> mealRecords = new ArrayList<MealRecord>();

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


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addMealRecordToDB(MealRecord mealRecord) {
        mealRecord.addToServer();

        // TODO - implement com.example.healthtracker.business_layer.MealRecordManager.addMealRecord
    }

    /**
     *
     * @param mealRecordId
     */
    public void deleteMealRecord(int mealRecordId) {
        ArrayList<MealRecord> mealRecord = new ArrayList<MealRecord>();
        try {
            mealRecord.get(mealRecordId).deleteFromServer();
        } catch (RecordNotInServerException e) {
            e.printStackTrace();
        }
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
    }
*/
    /**
     *
     * @param foodInfo
     */
    public void addFood(ArrayList<String> foodInfo) {
        Food newFood = new Food();
        newFood.setName(foodInfo.get(0));
        Nutrient newFoodNutrients = new Nutrient();

        newFoodNutrients.setCaloriePer100g(Integer.parseInt(foodInfo.get(1)));
        newFoodNutrients.setFat(Integer.parseInt(foodInfo.get(2)));
        newFoodNutrients.setCholesterol(Integer.parseInt(foodInfo.get(3)));
        newFoodNutrients.setSodium(Integer.parseInt(foodInfo.get(4)));
        newFoodNutrients.setPotassium(Integer.parseInt(foodInfo.get(5)));
        newFoodNutrients.setSugar(Integer.parseInt(foodInfo.get(6)));
        newFoodNutrients.setDietaryFibre(Integer.parseInt(foodInfo.get(7)));
        newFoodNutrients.setProtein(Integer.parseInt(foodInfo.get(8)));
        newFoodNutrients.setCalcium(Integer.parseInt(foodInfo.get(9)));
        newFoodNutrients.setVitaminC(Integer.parseInt(foodInfo.get(10)));
        newFoodNutrients.setIron(Integer.parseInt(foodInfo.get(11)));
        newFoodNutrients.setMagnesium(Integer.parseInt(foodInfo.get(12)));

        newFood.setNutrients(newFoodNutrients);

        //food.setNutrients(foodInfo.get("nutrients"));
        //food.addFoodToServer();
        // TODO - implement com.example.healthtracker.business_layer.MealRecordManager.addFood
    }

    /**
     *
     * @param startDate
     * @param endDate
     */
    public ArrayList<MealRecord> query(Date startDate, Date endDate) {
        ArrayList<MealRecord> mealRecord = new ArrayList<MealRecord>();
        return mealRecord;
        //return mealRecord.queryByDate(startDate, endDate);
        // TODO - implement com.example.healthtracker.business_layer.MealRecordManager.query
    }

    /**
     *
     * @param idInList
     * @param newInfo
     */
    public void editMealRecord(double idInList, Food[] newInfo) {

        // TODO - implement com.example.healthtracker.business_layer.MealRecordManager.editMealRecord
    }

    //private MealRecordManager MealRecordManager() {
    //    // TODO - implement com.example.healthtracker.business_layer.MealRecordManager.com.example.healthtracker.business_layer.MealRecordManager
    //}

    @RequiresApi(api = Build.VERSION_CODES.O)
    public double calculateCalorieConsumedToday(){
        MealRecord[] mealRecordsForToday = MealRecord.queryByDate(LocalDate.now(),LocalDate.now());
        double calorieConsumed = 0;
        for (int i=0; i<mealRecordsForToday.length; i++){
            for (int j=0; j<mealRecordsForToday[i].getFoods().size(); j++) {
                calorieConsumed += mealRecordsForToday[i].getNutrient().getCaloriePer100g() * mealRecordsForToday[i].getFoods().get(j).getActualIntake();
            }
        }
        return calorieConsumed;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public double calculateCalorieQuotaRemainingToday() {
        double calorieConsumed = calculateCalorieConsumedToday();
        HealthInfo healthInfo = new HealthInfo();
        double calorieSuggested = healthInfo.getSuggestCalorieIntake();
        return calorieSuggested - calorieConsumed;
        // TODO - implement com.example.healthtracker.business_layer.MealRecordManager.calculateCalorieQuotaRemainingToday
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Nutrient calculateTotalNutrient(){
        Nutrient totalConsumed = new Nutrient();
        MealRecord[] mealRecordsForToday = MealRecord.queryByDate(LocalDate.now(),LocalDate.now());


        for (int i=0; i<mealRecordsForToday.length; i++){
            totalConsumed.setFat(totalConsumed.getFat()+mealRecordsForToday[i].getNutrient().getFat());
            totalConsumed.setCholesterol(totalConsumed.getCholesterol()+mealRecordsForToday[i].getNutrient().getCholesterol());
            totalConsumed.setSodium(totalConsumed.getSodium()+mealRecordsForToday[i].getNutrient().getSodium());
            totalConsumed.setPotassium(totalConsumed.getPotassium()+mealRecordsForToday[i].getNutrient().getPotassium());
            totalConsumed.setSugar(totalConsumed.getSugar()+mealRecordsForToday[i].getNutrient().getSugar());
            totalConsumed.setDietaryFibre(totalConsumed.getDietaryFibre()+mealRecordsForToday[i].getNutrient().getDietaryFibre());
            totalConsumed.setProtein(totalConsumed.getProtein()+mealRecordsForToday[i].getNutrient().getProtein());
            totalConsumed.setCalcium(totalConsumed.getCalcium()+mealRecordsForToday[i].getNutrient().getCalcium());
            totalConsumed.setVitaminC(totalConsumed.getVitaminC()+mealRecordsForToday[i].getNutrient().getVitaminC());
            totalConsumed.setIron(totalConsumed.getIron()+mealRecordsForToday[i].getNutrient().getIron());
            totalConsumed.setMagnesium(totalConsumed.getMagnesium()+mealRecordsForToday[i].getNutrient().getMagnesium());
        }
        return totalConsumed;
    }
}