/**
 * Data access object
 * @Author: Tang Yuting
 */

package com.example.mealtracker;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.mealtracker.Exceptions.RecordNotInServerException;
import com.example.mealtracker.Exceptions.ValueCannotBeNonPositiveException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class MealRecord {
    private ArrayList<Food> foods = new ArrayList<Food>();
    private LocalDateTime time;
    private String id;

    /**
     * Constructor
     * @param foods ArrayList of Food
     * @param time LocalDateTime which the meal record refers to
     * @throws ValueCannotBeNonPositiveException when there is food with actualIntake 0
     */
    public MealRecord(ArrayList<Food> foods, LocalDateTime time) throws ValueCannotBeNonPositiveException {
        for (Food f: foods) {
            if (f.getActualIntake() <= 0) {
                throw new ValueCannotBeNonPositiveException();
            }
        }
        this.foods = foods;
        this.time = time;
    }

    public MealRecord() {
    }

    /**
     * Get time attribute as formatted date time.
     * @return String, formattedDateTime
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getTimeString() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String formattedDateTime = time.format(formatter);
        return formattedDateTime;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Food> getFoods(){
        return foods;
    }

    public void setFoods(ArrayList<Food> foods){
        this.foods = foods;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addToServer() {
        Database.getSingleton().postNewMealRecord(this);
    }

    public void deleteFromServer() throws RecordNotInServerException {
        if (this.id.isEmpty()) throw new RecordNotInServerException();
        Database.getSingleton().deleteMealRecord(this);
    }

    /**
     * @Author: Wang Binli
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateToServer() {
        Database.getSingleton().updateMealRecord(this);
    }

    /**
     * @param startDate LocalDate
     * @param endDate, LocalDate
     * @Author: WANG1448
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static MealRecord[] queryByDate (LocalDate startDate, LocalDate endDate){
        return Database.getSingleton().queryByDate(startDate, endDate);
    }


    /**
     *
     * @param newFoods
     */
    public void updateFood(Food[] newFoods) {
        // TODO - implement com.example.healthtracker.data_access_layer.MealRecord.updateFood
        throw new UnsupportedOperationException();
    }

    public Nutrient getNutrient() {
        Nutrient nutrientConsumedInOneMeal = new Nutrient();
        for (int i=0; i<foods.size(); i++){
            nutrientConsumedInOneMeal.setFat(nutrientConsumedInOneMeal.getFat()+nutrientConsumedInOneMeal.getFat());
            nutrientConsumedInOneMeal.setCholesterol(nutrientConsumedInOneMeal.getCholesterol()+nutrientConsumedInOneMeal.getCholesterol());
            nutrientConsumedInOneMeal.setSodium(nutrientConsumedInOneMeal.getSodium()+nutrientConsumedInOneMeal.getSodium());
            nutrientConsumedInOneMeal.setPotassium(nutrientConsumedInOneMeal.getPotassium()+nutrientConsumedInOneMeal.getPotassium());
            nutrientConsumedInOneMeal.setSugar(nutrientConsumedInOneMeal.getSugar()+nutrientConsumedInOneMeal.getSugar());
            nutrientConsumedInOneMeal.setDietaryFibre(nutrientConsumedInOneMeal.getDietaryFibre()+nutrientConsumedInOneMeal.getDietaryFibre());
            nutrientConsumedInOneMeal.setProtein(nutrientConsumedInOneMeal.getProtein()+nutrientConsumedInOneMeal.getProtein());
            nutrientConsumedInOneMeal.setCalcium(nutrientConsumedInOneMeal.getCalcium()+nutrientConsumedInOneMeal.getCalcium());
            nutrientConsumedInOneMeal.setVitaminC(nutrientConsumedInOneMeal.getVitaminC()+nutrientConsumedInOneMeal.getVitaminC());
            nutrientConsumedInOneMeal.setIron(nutrientConsumedInOneMeal.getIron()+nutrientConsumedInOneMeal.getIron());
            nutrientConsumedInOneMeal.setMagnesium(nutrientConsumedInOneMeal.getMagnesium()+nutrientConsumedInOneMeal.getMagnesium());
        }
        return nutrientConsumedInOneMeal;

        // TODO - implement com.example.healthtracker.data_access_layer.MealRecord.getNutrient

    }

    public void addFood(Food food) {
        foods.add(food);
    }

    public void delFood(Food food) {
        foods.remove(food);
    }


}