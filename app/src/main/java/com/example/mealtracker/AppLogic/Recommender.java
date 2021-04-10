package com.example.mealtracker.AppLogic;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.mealtracker.DAO.Database;
import com.example.mealtracker.DAO.HealthInfo;
import com.example.mealtracker.DAO.MealRecord;
import com.example.mealtracker.DAO.Nutrient;
import com.example.mealtracker.Gender;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Class for highlighting the lacked nutrient within one week as well as recommend food
 */
public class Recommender {
    private Nutrient consumedNutrient;
    private HashMap<String, Double> lackedNutrient;
    private HashMap<String, Double> recommendFood;

    public Recommender(Nutrient consumedNutrient) {
        this.consumedNutrient = consumedNutrient;
    }

    /**
     * Calculates the lacked nutrient and get recommended food.
     * It must be used before accessing the attributes.
     * @return HashMap<NutrientName(String), HashMap<(FoodName)String, (ContainInfoOfNutrient)Double>>
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public HashMap<String, HashMap<String, Double>> recommend() {
        // calculate lacked nutrient
        Nutrient actualIntake = consumedNutrient;
        Nutrient suggestion = HealthInfo.getSingleton().getSuggestedNutrientIntakePerWeek();
        lackedNutrient = actualIntake.compare(suggestion);
        // get recommend from database
        HashMap<String, HashMap<String, Double>> results = new HashMap<>();
        for (Map.Entry<String, Double> entry : lackedNutrient.entrySet()) {
            String nutrientName = entry.getKey();
            results.put(nutrientName, Database.getSingleton().queryRecommendFood(nutrientName));
        }
        return results;
    }

    public Nutrient getConsumedNutrient() {
        return consumedNutrient;
    }

    public void setConsumedNutrient(Nutrient consumedNutrient) {
        this.consumedNutrient = consumedNutrient;
    }

    public HashMap<String, Double> getLackedNutrient() {
        return lackedNutrient;
    }

    public void setLackedNutrient(HashMap<String, Double> lackedNutrient) {
        this.lackedNutrient = lackedNutrient;
    }

    public HashMap<String, Double> getRecommendFood() {
        return recommendFood;
    }

    public void setRecommendFood(HashMap<String, Double> recommendFood) {
        this.recommendFood = recommendFood;
    }
}