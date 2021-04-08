/*
  Data access object for Meal Record
  @Author: Tang Yuting
 */

package com.example.mealtracker.DAO;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.mealtracker.Exceptions.EmptyResultException;
import com.example.mealtracker.Exceptions.RecordNotInServerException;
import com.example.mealtracker.Exceptions.ValueCannotBeNonPositiveException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class MealRecord {
    private ArrayList<Food> foods = new ArrayList<>();
    private LocalDateTime time;
    private String id;

    /**
     * Constructor
     * @param foods ArrayList of Food, the actual intake cannot be non-positive.
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
        return time.format(formatter);
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


    /**
     * adds the meal record to database under the user, with the meal record id updated.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addToServer() {
        Database.getSingleton().postNewMealRecord(this);
    }

    /**
     * Deletes the meal record from the server.
     * @throws RecordNotInServerException when the meal record has empty ID,
     * which means not found in the server.
     */
    public void deleteFromServer() throws RecordNotInServerException {
        if (this.id.isEmpty()) throw new RecordNotInServerException();
        Database.getSingleton().deleteMealRecord(this);
        this.id = "";  // indicates the index is removed from the server
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
    public static MealRecord[] queryByDate (LocalDate startDate, LocalDate endDate) throws EmptyResultException {
        return Database.getSingleton().queryByDate(startDate, endDate);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static MealRecord[] queryAll() throws EmptyResultException {
        return Database.getSingleton().queryAllMealRecords();
    }

    /**
     * @return Nutrient that contains the amount of consumption of nutrients in the meal record
     */
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
    }

    /**
     * Appends new food at the end of the food list
     * @param food Food, new food to add to the array list
     */
    public void addFood(Food food) {
        foods.add(food);
    }

    /**
     * Deletes the last element in the food
     * @throws IndexOutOfBoundsException, when the food is empty
     */
    public void deleteLastFood() throws IndexOutOfBoundsException{
        if (foods.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        foods.remove(foods.size() - 1);
    }

    public double getTotalCalorie() throws EmptyResultException {
        if (foods.isEmpty()) {
            throw new EmptyResultException();
        }
        double result = 0;
        for (Food f: foods) {
            result += f.getTotalCalorie();
        }
        return result;
    }

    public LocalDateTime getTime() {
        return time;
    }
}