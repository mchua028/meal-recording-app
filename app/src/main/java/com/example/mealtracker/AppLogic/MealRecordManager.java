package com.example.mealtracker.AppLogic;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.example.mealtracker.DAO.Database;
import com.example.mealtracker.DAO.Food;
import com.example.mealtracker.DAO.HealthInfo;
import com.example.mealtracker.DAO.MealRecord;
import com.example.mealtracker.DAO.Nutrient;
import com.example.mealtracker.Exceptions.EmptyInputException;
import com.example.mealtracker.Exceptions.EmptyResultException;
import com.example.mealtracker.Exceptions.RecordNotInServerException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;


public class MealRecordManager {
    private static MealRecordManager singleton = null;

    private MealRecord mealRecord;

    private ArrayList<MealRecord> mealRecords;

    private MealRecord[] mealRecords1;

    static public MealRecordManager getSingleton() {
        if (singleton == null) {
            singleton = new MealRecordManager();
        }
        return singleton;
    }

    private double calorieConsumedToday;
    private double calorieRemaining;

    public void setCalorieConsumedToday(double calorieConsumedToday) {
        this.calorieConsumedToday = calorieConsumedToday;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public double getCalorieConsumedToday() {
        double total = 0;
        try {
            ArrayList<MealRecord> allMealRecords = Database.getSingleton().queryAllMealRecords();
            for (MealRecord mealRecord: allMealRecords) {
                if (LocalDate.now().equals(LocalDate.from(mealRecord.getTime()))) {
                    total += mealRecord.getTotalCalorie();
                }
            }
        } catch (EmptyResultException e) {
            return 0;
        }
        return total;
    }

    public void setCalorieRemaining(double calorieRemaining) {
        this.calorieRemaining = calorieRemaining;
    }

    public double getCalorieRemaining() {
        return calorieRemaining;
    }


    public void setMealRecord(MealRecord mealRecord){
        Log.d("entering","mealreocrdmgr");
        this.mealRecord = mealRecord;
    }

    public MealRecord getMealRecord(){
        return mealRecord;
    }

    public void setMealRecords(ArrayList<MealRecord> mealRecords){
        Log.d("entering","mealreocrdmgr");
        this.mealRecords = mealRecords;
    }

    public ArrayList<MealRecord> getMealRecords(){
        return mealRecords;
    }

    public void setMealRecords1(MealRecord[] mealRecords1){
        Log.d("entering","mealreocrdmgr");
        this.mealRecords1 = mealRecords1;
    }

    public MealRecord[] getMealRecords1(){
        return mealRecords1;
    }

    /**
     *
     * @param foodName
     */
    public Food query(String foodName) {
        //Food food = new Food();
        try {
            Log.d("entering searchfood","hi");
            return Food.searchFood(foodName);
        } catch (EmptyInputException | EmptyResultException e) {
            Log.d("emptyinputexception","e!!!");
            e.printStackTrace();
        }
        return new Food();
    }


    /**
     * Adds the mealRecord with the current timestamp to server.
     * @param mealRecord
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addMealRecordToDB(MealRecord mealRecord) {
        mealRecord.setTime(LocalDateTime.now());
        mealRecord.addToServer();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<MealRecord> getMealRecordsFromDB() throws EmptyResultException {
        return MealRecord.queryAll();
    }

    /**
     *
     * @param mealRecord
     */
    public void deleteMealRecord(MealRecord mealRecord) {
        //ArrayList<MealRecord> mealRecord = new ArrayList<MealRecord>();
        try {
            mealRecord.deleteFromServer();
        } catch (RecordNotInServerException e) {
            e.printStackTrace();
        }
        //Database.getSingleton().deleteMealRecord(mealRecordId);
        // TODO - implement com.example.healthtracker.business_layer.MealRecordManager.deleteMealRecord
    }

    /**
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
     * Updates meal record
     * @param mealRecord MealRecord to edit
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void editMealRecord(MealRecord mealRecord) {
        Database.getSingleton().updateMealRecord(mealRecord);
    }

    /**
     * Calculate the total calorie consumption today
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public double calculateCalorieConsumedToday(){
        ArrayList<MealRecord> mealRecordsForToday = new ArrayList<MealRecord>();
        try {
            mealRecordsForToday = MealRecord.queryByDate(LocalDate.now(),LocalDate.now());
        } catch (EmptyResultException e) {
            return 0;
        }
        double calorieTakenToday = 0;
        for (MealRecord mealRecord: mealRecordsForToday) {
            try {
                calorieTakenToday += mealRecord.getTotalCalorie();
            } catch (EmptyResultException e) {
                continue;
            }
        }
        return calorieTakenToday;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void calculateCalorieQuotaRemainingToday() {
        HealthInfo healthInfo = HealthInfo.getSingleton(); //TODO: get from database instead
        double calorieSuggested = healthInfo.getSuggestCalorieIntake();
        calorieRemaining = calorieSuggested - calorieConsumedToday;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Nutrient calculateTotalNutrient() throws EmptyResultException {
        Nutrient totalConsumed = new Nutrient();
        ArrayList<MealRecord> mealRecordsForToday = MealRecord.queryAll();

        for (int i=0; i<mealRecordsForToday.size(); i++){
            totalConsumed.setFat(totalConsumed.getFat()+mealRecordsForToday.get(i).getNutrient().getFat());
            totalConsumed.setCholesterol(totalConsumed.getCholesterol()+mealRecordsForToday.get(i).getNutrient().getCholesterol());
            totalConsumed.setSodium(totalConsumed.getSodium()+mealRecordsForToday.get(i).getNutrient().getSodium());
            totalConsumed.setPotassium(totalConsumed.getPotassium()+mealRecordsForToday.get(i).getNutrient().getPotassium());
            totalConsumed.setSugar(totalConsumed.getSugar()+mealRecordsForToday.get(i).getNutrient().getSugar());
            totalConsumed.setDietaryFibre(totalConsumed.getDietaryFibre()+mealRecordsForToday.get(i).getNutrient().getDietaryFibre());
            totalConsumed.setProtein(totalConsumed.getProtein()+mealRecordsForToday.get(i).getNutrient().getProtein());
            totalConsumed.setCalcium(totalConsumed.getCalcium()+mealRecordsForToday.get(i).getNutrient().getCalcium());
            totalConsumed.setVitaminC(totalConsumed.getVitaminC()+mealRecordsForToday.get(i).getNutrient().getVitaminC());
            totalConsumed.setIron(totalConsumed.getIron()+mealRecordsForToday.get(i).getNutrient().getIron());
            totalConsumed.setMagnesium(totalConsumed.getMagnesium()+mealRecordsForToday.get(i).getNutrient().getMagnesium());
        }
        return totalConsumed;
    }

    /**
     * returns the actual calorie intake records within the past week (including today).
     * @return meal records, in ArrayList Double, null if no records
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<Double> getCalorieIntakeInWeek() {
        LocalDate endDate = LocalDate.now().plusDays(1);
        LocalDate startDate = endDate.minusDays(7);

        // init
        ArrayList<Double> results = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            results.add(0.0);
        }
        ArrayList<MealRecord> mealRecords;
        try {
            mealRecords= MealRecord.queryByDate(startDate, endDate);
        } catch (EmptyResultException e) {
            return null;
        }
        for (MealRecord mealRecord: mealRecords) {
            int index = Period.between(startDate, LocalDate.from(mealRecord.getTime())).getDays();
            double element = 0;
            try {
                element = results.get(index) + mealRecord.getTotalCalorie();
            } catch (EmptyResultException e) {
                return null;
            }
            results.set(index, element);
        }
        return results;
    }

    /**
     * Gets the total amount of nutrient intake within one week (including today)
     * @return Nutrient
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Nutrient getNutrientInWeek() {
        LocalDate endDate = LocalDate.now().plusDays(1);
        LocalDate startDate = endDate.minusDays(7);

        // init
        ArrayList<Double> results = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            results.add(0.0);
        }
        ArrayList<MealRecord> mealRecords;
        // request meal records
        try {
            mealRecords= MealRecord.queryByDate(startDate, endDate);
        } catch (EmptyResultException e) {
            return null;
        }
        Nutrient nutrient = new Nutrient();
        for (MealRecord mealRecord: mealRecords) {
            nutrient = mealRecord.addWithNutrient(nutrient);
        }
        return nutrient;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<MealRecord> queryFood(LocalDate date) throws EmptyResultException {
        return Database.getSingleton().queryByDate(date);
    }
}