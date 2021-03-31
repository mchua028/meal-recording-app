package com.example.mealtracker;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Recommender {
    //private ArrayList<String> nutrients = new ArrayList<String>();
    private HashMap<String,Double> suggestedNutrientAmt = new HashMap<String,Double>();
    private HashMap<String,Double> actualNutrientAmt;
    private ArrayList<String> lackedNutrients=new ArrayList<String>();
    private HashMap<String,ArrayList<String>> recommendedFoods;

    public Recommender(){
    }



    public HashMap<String, Double> getSuggestedNutrientAmt() {
        return this.suggestedNutrientAmt;
    }

    public void setSuggestedNutrientAmt() {
        HealthInfo info  = new HealthInfo();
        //values in grams
        double carbohydrate = 12/100*info.getSuggestCalorieIntake();
        double protein = 0.9*info.getGoalWeight();
        double fat = info.getSuggestCalorieIntake()/30;
        double vitaminc = 80/1000;
        double vitamina = 8/10000;
        double vitaminb1 = 0.9/1000;
        double vitaminb2 = 1.2/1000;
        double iron;
        if(info.getGender()==Gender.FEMALE)
            iron = 18/1000;
        else iron = 8/1000;
        double magnesium = 310/1000;
        double calcium = 1;
        double fibre = 25;
        double potassium = 2.8;
        double sodium = 0.23;

        suggestedNutrientAmt.put("carbohydrate",carbohydrate);
        suggestedNutrientAmt.put("protein",protein);
        suggestedNutrientAmt.put("fat",fat);
        suggestedNutrientAmt.put("vitaminc",vitaminc);
        suggestedNutrientAmt.put("vitamina",vitamina);
        suggestedNutrientAmt.put("vitaminb1",vitaminb1);
        suggestedNutrientAmt.put("vitaminb2",vitaminb2);
        suggestedNutrientAmt.put("iron",iron);
        suggestedNutrientAmt.put("magnesium",magnesium);
        suggestedNutrientAmt.put("calcium",calcium);
        suggestedNutrientAmt.put("fibre",fibre);
        suggestedNutrientAmt.put("potassium",potassium);
        suggestedNutrientAmt.put("sodium",sodium);

    }

    public HashMap<String, Double> getActualNutrientAmt() {
        return this.actualNutrientAmt;
    }

    public void setActualNutrientAmt() {
        MealRecordManager mrm = new MealRecordManager();
       // actualNutrientAmt = mrm.calculateNutrientRemaining();
        actualNutrientAmt=actualNutrientAmt;
    }

    public ArrayList<String> getLackedNutrients() {
        return this.lackedNutrients;
    }

    public void setLackedNutrients() {
        for (String key:suggestedNutrientAmt.keySet()){
            if (actualNutrientAmt.get(key) < suggestedNutrientAmt.get(key)) {
                lackedNutrients.add(key);
            }
        }
    }

    /*public Nutrient getSuggestedNutrients() {
        /
    }*/

    /**
     * boolean value: 0 means lower than suggested and 1 means higher than suggested
     */
    /*public HashMap<String, String> getAbnormalNutrient() {
        // TODO - implement com.example.healthtracker.business_layer.Recommender.getAbnormalNutrient
        throw new UnsupportedOperationException();
    }*/



    /*private Nutrient calculateActualIntake() {
        // TODO - implement com.example.healthtracker.business_layer.Recommender.calculateActualIntake
        throw new UnsupportedOperationException();
    }*/

    public void setRecommendedFoods(){
        for(String nutrient:lackedNutrients){
            recommendedFoods.put(nutrient,Food.searchFoodsRichInNutrient(nutrient,10));
        }
    }

    public HashMap<String,ArrayList<String>> getRecommendedFoods(){
        return recommendedFoods;
    }

}