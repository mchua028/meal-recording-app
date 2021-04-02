/**
 * Database helper to access Firebase Realtime Database/
 * @Author: Tang Yuting
 */
package com.example.mealtracker;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


/**
 * Singleton.
 */
public class Database {

    static private Database singleton;
    private FirebaseDatabase database;
    // Database Reference: like the table in relational database
    private DatabaseReference userReference;
    private DatabaseReference mealReference;

    /**
     * Constructor,
     */
    private Database() {
        database = FirebaseDatabase.getInstance(DATABASE_URL);
        userReference = database.getReference("User").child(username);
        mealReference = userReference.child("MealRecords");
    }

    static public Database getSingleton() {
        if (singleton == null) {
            singleton = new Database();
        }
        return singleton;
    }

    /**
     * Post meal record to the server.
     * @param mealRecord MealRecord
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void postNewMealRecord(MealRecord mealRecord) {
        DatabaseReference newMealRecord = mealReference.push();
        mealRecord.setId(newMealRecord.getKey());
        newMealRecord.child("Datetime").setValue(mealRecord.getTimeString());
        DatabaseReference foodRecords = newMealRecord.child("FoodRecords");

        for (Food food: mealRecord.getFoods()) {
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


    public void deleteMealRecord(MealRecord mealRecord) {
        DatabaseReference mealRecordReference = mealReference.child(mealRecord.getId());
        mealRecordReference.setValue(null);  // the deletion operation mentioned in Firebase api
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
}