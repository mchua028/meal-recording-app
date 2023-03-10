/**
 * Database helper to access Firebase Realtime Database/
 * @Author: Tang Yuting, Wang Binli
 */
package com.example.mealtracker;

import android.os.Build;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;


/**
 * Singleton.
 */
public class Database {
    static private Database singleton;
    private FirebaseDatabase database;
    // Database Reference: like the table in relational database
    private DatabaseReference userReference;
    private DatabaseReference mealRecordReference;

    // for testing purpose
    private final String DATABASE_URL = "https://healthtracker-cz2006-default-rtdb.firebaseio.com/";
    private final String username = "christang";

    /**
     * Constructor,
     * Author: Tang Yuting
     */
    private Database() {
        database = FirebaseDatabase.getInstance(DATABASE_URL);
        userReference = database.getReference("User").child(username);
        mealRecordReference = userReference.child("MealRecords");

    }

    static public Database getSingleton() {
        if (singleton == null) {
            singleton = new Database();
        }
        return singleton;
    }

    /**
     * Post meal record to the server.
     *
     * @param mealRecord MealRecord
     * Author : Tang Yuting
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void postNewMealRecord(MealRecord mealRecord) {
        DatabaseReference newMealRecord = mealRecordReference.push();
        mealRecord.setId(newMealRecord.getKey());
        newMealRecord.child("Datetime").setValue(mealRecord.getTimeString());
        DatabaseReference foodRecords = newMealRecord.child("FoodRecords");

        for (Food food : mealRecord.getFoods()) {
            DatabaseReference foodRecord = foodRecords.push();
            foodRecord.child("name").setValue(food.getName());
            Nutrient nutrient = food.getNutrients();
            foodRecord.child("Calcium").setValue(nutrient.getCalcium());
            foodRecord.child("Calorie").setValue(nutrient.getCaloriePer100g());
            foodRecord.child("Fat").setValue(nutrient.getFat());
            foodRecord.child("Magnesium").setValue(nutrient.getMagnesium());
            foodRecord.child("Potassium").setValue(nutrient.getPotassium());
            foodRecord.child("Sodium").setValue(nutrient.getSodium());
            foodRecord.child("Sugar").setValue(nutrient.getSugar());
            foodRecord.child("VitaminC").setValue(nutrient.getVitaminC());
            foodRecord.child("weight").setValue(food.getActualIntake());
        }
    }

    /**
     * Delete meal record from the server.
     *
     * @param mealRecord MealRecord
     * Author : Tang Yuting
     */
    public void deleteMealRecord(MealRecord mealRecord) {
        DatabaseReference mealRecordReference = this.mealRecordReference.child(mealRecord.getId());
        mealRecordReference.setValue(null);  // the deletion operation mentioned in Firebase api
    }


    /**
     * Update meal record to the server.
     *
     * @param mealRecord MealRecord
     * Author : Wang Binli
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateMealRecord(MealRecord mealRecord) {
        DatabaseReference mealRecordReference = this.mealRecordReference.child(mealRecord.getId());
        DatabaseReference foodRecords = mealRecordReference.child("FoodRecords");

        mealRecordReference.child("Datetime").setValue(mealRecord.getTimeString());
        foodRecords.removeValue();
        for (Food food : mealRecord.getFoods()) {
            DatabaseReference foodRecord = foodRecords.push();
            foodRecord.child("name").setValue(food.getName());
            Nutrient nutrient = food.getNutrients();
            foodRecord.child("Calcium").setValue(nutrient.getCalcium());
            foodRecord.child("Calorie").setValue(nutrient.getCaloriePer100g());
            foodRecord.child("Fat").setValue(nutrient.getFat());
            foodRecord.child("Magnesium").setValue(nutrient.getMagnesium());
            foodRecord.child("Potassium").setValue(nutrient.getPotassium());
            foodRecord.child("Sodium").setValue(nutrient.getSodium());
            foodRecord.child("Sugar").setValue(nutrient.getSugar());
            foodRecord.child("VitaminC").setValue(nutrient.getVitaminC());
            foodRecord.child("weight").setValue(food.getActualIntake());
        }
    }


    /**
     * Search meal record from database within the dates (inclusive)
     *
     * @param startDate, LocalDate including only
     * @param endDate,   LocalDate
     * @Author: Wang binli
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public MealRecord[] queryByDate(LocalDate startDate, LocalDate endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        String formattedStartDate = startDate.format(formatter);
        String formattedEndDate = endDate.format(formatter);

        ArrayList<MealRecord> mealRecords = new ArrayList<>();

        Query queryRef;
        queryRef = mealRecordReference.orderByChild("Datetime").startAt(formattedStartDate).endAt(formattedEndDate);

        final DataSnapshot[] result = {null};
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                result[0] = snapshot;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // waiting for query result
        while (result[0] == null) {
            ;
        }

        for (DataSnapshot mealRecord : result[0].getChildren()) {
            MealRecord mealRecord1 = new MealRecord();
            mealRecord1.setId(mealRecord.getKey());
            LocalDateTime mealRecordDateTime = LocalDateTime.parse(mealRecord.child("Datetime").getValue(String.class));
            mealRecord1.setTime(mealRecordDateTime);
            // parse foods in meal record
            for (DataSnapshot foodRecord : mealRecord.child("FoodRecords").getChildren()) {
                Food food = new Food();
                Nutrient nutrient = new Nutrient();
                nutrient.setCaloriePer100g(foodRecord.child("Calorie").getValue(Double.class));
                nutrient.setCalcium(foodRecord.child("Calcium").getValue(Double.class));
                nutrient.setSodium(foodRecord.child("Sodium").getValue(Double.class));
                nutrient.setFat(foodRecord.child("Fat").getValue(Double.class));
                food.setName(foodRecord.child("name").getValue(String.class));
                nutrient.setMagnesium(foodRecord.child("Magnesium").getValue(Double.class));
                nutrient.setPotassium(foodRecord.child("Potassium").getValue(Double.class));
                nutrient.setSugar(foodRecord.child("Sugar").getValue(Double.class));
                nutrient.setVitaminC(foodRecord.child("VitaminC").getValue(Double.class));
                food.setNutrients(nutrient);
                mealRecord1.addFood(food);
                mealRecords.add(mealRecord1);
            }
        }
        return mealRecords.toArray(new MealRecord[0]);
    }


    /**
     * Post health information to the server.
     * @param healthInfo HealthInfo
     * Author : Wang Binli
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void postHealthInfo(HealthInfo healthInfo) {
        DatabaseReference HealthInfo = this.mealRecordReference.push();
        HealthInfo.child("age").setValue(healthInfo.getAge());
        HealthInfo.child("dailyActivityLevel").setValue(healthInfo.getDailyActivityLevel());
        HealthInfo.child("gender").setValue(healthInfo.getGender());
        HealthInfo.child("goalWeight").setValue(healthInfo.getGoalWeight());
        HealthInfo.child("height").setValue(healthInfo.getHeight());
        HealthInfo.child("weight").setValue(healthInfo.getWeight());
    }


    /**
     * Update health information to the server.
     * @param healthInfo HealthInfo
     * Author : Wang Binli
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateHealthInfo(HealthInfo healthInfo) {
        DatabaseReference healthInoReference = this.userReference.child("HealthInfo");
        healthInoReference.child("age").setValue(healthInfo.getAge());
        healthInoReference.child("dailyActivityLevel").setValue(healthInfo.getDailyActivityLevel());
        healthInoReference.child("gender").setValue(healthInfo.getGender());
        healthInoReference.child("goalWeight").setValue(healthInfo.getGoalWeight());
        healthInoReference.child("height").setValue(healthInfo.getHeight());
        healthInoReference.child("weight").setValue(healthInfo.getWeight());
    }

    /**
     * Post account to the server.
     * @param account Account
     * Author : Wang Binili
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void postNewAccount(Account account) {
        DatabaseReference newUserAccount = userReference.push();
        newUserAccount.setValue(account.getUsername());
        newUserAccount.child("firstName").setValue(account.getFirstName());
        newUserAccount.child("lastName").setValue(account.getLastName());
        newUserAccount.child("email").setValue(account.getEmail());
        newUserAccount.child("password").setValue(account.getPassword());
    }

    /**
     * Update account information to the server.
     * @param account Account
     * Author : Wang Binli
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateHealthInfo(Account account) {
        DatabaseReference userRef = this.userReference;
        userRef.setValue(account.getUsername());
        userRef.child("firstName").setValue(account.getFirstName());
        userRef.child("lastName").setValue(account.getLastName());
        userRef.child("email").setValue(account.getEmail());
        userRef.child("password").setValue(account.getPassword());
    }
}


//    public void getUserRecord(String username) {
//        DatabaseReference ref = database.getReference("message");
////        ref.setValue("Hello, world!!");
//        final String[] result = new String[1];
////        System.out.println("Start debugging");
////        ref.setValue("Hello, world!");
//        ref.addValueEventListener(new ValueEventListener() {
//                                              @Override
//                                              public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                                  System.out.print("==========");
//                                                  System.out.print(snapshot.getValue());
//                                                  result[0] = snapshot.getValue(String.class);
//                                              }
//
//                                              @Override
//                                              public void onCancelled(@NonNull DatabaseError error) {
//
//                                              }
//                                          });
//        while (result[0]==null) {
//            ;
//        }
//        System.out.print(result[0]);
//    }