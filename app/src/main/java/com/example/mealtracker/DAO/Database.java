/**
 * Database helper to access Firebase Realtime Database/
 * @Author: Tang Yuting, Wang Binli
 */
package com.example.mealtracker.DAO;

import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.mealtracker.UI.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
    private FirebaseAuth firebaseAuth;

    public  String userId = null;

    // for testing purpose
    private final String DATABASE_URL = "https://mealtracker-dc280-default-rtdb.firebaseio.com/";

    /**
     * Constructor,
     * Author: Tang Yuting
     */
    private Database() {
        database = FirebaseDatabase.getInstance(DATABASE_URL);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    static public Database getSingleton() {
        if (singleton == null) {
            singleton = new Database();
        }
        return singleton;
    }

    public FirebaseAuth getmAuth() {
        return firebaseAuth;
    }

    public DatabaseReference getUserReference() {
        if (userId == null) {
            return database.getReference().child("Users").child(firebaseAuth.getCurrentUser().getUid());
        } else {
            return database.getReference().child("Users").child(userId);
        }
    }

    /**
     * Post meal record to the server.
     *
     * @param mealRecord MealRecord
     * Author : Tang Yuting
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void postNewMealRecord(MealRecord mealRecord) {
        // create new meal record with generated id
        DatabaseReference newMealRecord = getUserReference().child("MealRecords").push();
        Log.d("into","postnewmealrecord");
        mealRecord.setId(newMealRecord.getKey());

        newMealRecord.child("Datetime").setValue(mealRecord.getTimeString());
        DatabaseReference foodRecords = newMealRecord.child("FoodRecords");

        for (Food food : mealRecord.getFoods()) {
            Log.d("into","pushFoodRecords");
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

        Query queryRef;
        queryRef = getUserReference().child("MealRecords").orderByChild("Datetime").startAt(formattedStartDate).endAt(formattedEndDate);

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

        ArrayList<MealRecord> mealRecords = parseMealRecords(result[0]);
        return mealRecords.toArray(new MealRecord[0]);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public MealRecord[] queryAllMealRecords() {
        Query queryRef;
        queryRef = getUserReference().child("MealRecords").orderByChild("Datetime");
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

        ArrayList<MealRecord> mealRecords = parseMealRecords(result[0]);
        return mealRecords.toArray(new MealRecord[0]);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static ArrayList<MealRecord> parseMealRecords(DataSnapshot dataSnapshot) {
        ArrayList<MealRecord> mealRecords = new ArrayList<>();
        for (DataSnapshot mealRecord : dataSnapshot.getChildren()) {
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
        return mealRecords;
    }


    /**
     * Post health information to the server.
     * @param healthInfo HealthInfo
     * Author : Wang Binli, Tang Yuting
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void postHealthInfo(HealthInfo healthInfo) {
        DatabaseReference userReference = getUserReference();
        userReference.child("age").setValue(healthInfo.getAge());
        userReference.child("dailyActivityLevel").setValue(healthInfo.getDailyActivityLevel());
        userReference.child("gender").setValue(healthInfo.getGender());
        userReference.child("goalWeight").setValue(healthInfo.getGoalWeight());
        userReference.child("height").setValue(healthInfo.getHeight());
        userReference.child("weight").setValue(healthInfo.getWeight());
        healthInfo.calculateCalorie();
        userReference.child("suggestCalorieIntake").setValue(healthInfo.getSuggestCalorieIntake());
    }


    /**
     * Update health information to the server.
     * @param healthInfo HealthInfo
     * Author : Wang Binli
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void updateHealthInfo(HealthInfo healthInfo) {
        DatabaseReference userReference = getUserReference();
        userReference.child("age").setValue(healthInfo.getAge());
        userReference.child("dailyActivityLevel").setValue(healthInfo.getDailyActivityLevel());
        userReference.child("gender").setValue(healthInfo.getGender());
        userReference.child("goalWeight").setValue(healthInfo.getGoalWeight());
        userReference.child("height").setValue(healthInfo.getHeight());
        userReference.child("weight").setValue(healthInfo.getWeight());
        healthInfo.calculateCalorie();
        userReference.child("suggestCalorieIntake").setValue(healthInfo.getSuggestCalorieIntake());
    }

    public double retrieveHealthInfo(HealthInfo healthInfo) {

        Log.d("retrieve", "went in");
        DatabaseReference userReference = getUserReference();
        //final HealthInfo[] changeInfo = {null};
        Log.d("retrieve", "healthinfo");

        userReference.child("suggestCalorieIntake").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {

            //double calorieSuggest = 1200;
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                    if (String.valueOf(task.getResult().getValue()) == "null") {
                        healthInfo.setSuggestCalorieIntake(1200);


                    } else {
                        healthInfo.setSuggestCalorieIntake(Double.parseDouble(String.valueOf(task.getResult().getValue())));
                    }
                }
            }
        });
        /*double suggestCalorie = 0;
        while (suggestCalorie == 0){
            //Log.d("waiting", "wait");
            suggestCalorie = healthInfo.getSuggestCalorieIntake();
        }*/
        Log.d("before getcalorie", Double.toString(healthInfo.getSuggestCalorieIntake()));
        return healthInfo.getSuggestCalorieIntake();

    }



    /**
     * Creates account dir under database "Users".
     * Author : Wang Binili, Tang Yuting
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void postNewAccount() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        String userId = firebaseAuth.getCurrentUser().getUid();
        HashMap<String, String> value = new HashMap<>();
        // add value as a place-holder, otherwise creation will fail
        value.put("registeredTime", LocalDateTime.now().format(formatter));
        database.getReference().child("User").child(userId).setValue(value);
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