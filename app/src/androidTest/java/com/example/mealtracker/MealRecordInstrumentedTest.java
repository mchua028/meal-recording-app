package com.example.mealtracker;

import androidx.test.runner.AndroidJUnit4;

import com.example.mealtracker.Exceptions.EmptyInputException;
import com.example.mealtracker.Exceptions.EmptyResultException;
import com.example.mealtracker.Exceptions.RecordNotInServerException;
import com.example.mealtracker.Exceptions.ValueCannotBeNonPositiveException;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;


/**
 * TODO:
 *  - change account
 */
@RunWith(AndroidJUnit4.class)
public class MealRecordInstrumentedTest {
    private String testUserName = "christang";
    private Database d = Database.getSingleton();


    @Test
    public MealRecord post_MealRecord() {
        ArrayList<Food> foods = getFoods();
        MealRecord mealRecord = null;
        try {
            mealRecord = new MealRecord(foods, LocalDateTime.now());
        } catch (ValueCannotBeNonPositiveException e) {
            e.printStackTrace();
        }
        d.postNewMealRecord(mealRecord);
        return mealRecord;
    }

    private ArrayList<Food> getFoods() {
        String[] foodNames = new String[3];
        ArrayList<Food> foods = new ArrayList<>();
        foodNames[0] = "Steak";
        foodNames[1] = "Subway Sandwich";
        foodNames[2] = "Apple";

        for (int i = 0; i < foodNames.length; i++) {
            try {
                Food newFood = Food.searchFood(foodNames[i]);
                newFood.setActualIntake(15);
                foods.add(newFood);
            } catch (EmptyInputException e) {

            } catch (EmptyResultException e) {
                System.out.printf("%s is not found", foodNames[i]);
            }

        }
        return foods;
    }

    @Test
    public void delete_MealRecord() {
        MealRecord m = post_MealRecord();
        try {
            m.deleteFromServer();
        } catch (RecordNotInServerException e) {
            e.printStackTrace();
        }
    }
}