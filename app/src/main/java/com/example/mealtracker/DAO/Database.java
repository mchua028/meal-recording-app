/**
 * Database helper to access Firebase Realtime Database/
 * @Author: Tang Yuting, Wang Binli
 */
package com.example.mealtracker.DAO;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.mealtracker.Activity;
import com.example.mealtracker.Exceptions.EmptyResultException;
import com.example.mealtracker.Gender;
import com.example.mealtracker.UI.MainActivity;
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
    private DatabaseReference foodRecommend;

    private FirebaseAuth firebaseAuth;
    public final DataSnapshot[] dataSnapshot = {null, null};
    private HealthInfo healthInfo;

    public  String userId;  // this attribute only for testing purpose

    // for testing purpose
    private final String DATABASE_URL = "https://mealtracker-dc280-default-rtdb.firebaseio.com/";

    /**
     * Constructor,
     * Author: Tang Yuting
     */
    private Database() {
        Log.d("database","constructor");
        database = FirebaseDatabase.getInstance(DATABASE_URL);
        Log.d("firebaseauth","get instance");
        firebaseAuth = FirebaseAuth.getInstance();
        Log.d("get","userid");
        userReference = database.getReference("Users").child(firebaseAuth.getCurrentUser().getUid());
        Log.d("going","addvalueeventlistener");
        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("getting","datasnapshot");
                dataSnapshot[0] = snapshot;
                Log.d("finsih","snapshot");
                MainActivity.isInit = true;
                Log.d("isinit","set to true");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("on","cancelled");
            }

        });
        foodRecommend = database.getReference().child("FoodRichInNutrient");
        Query foodRichInNutrient = foodRecommend.orderByKey();
        // make the query
        foodRichInNutrient.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataSnapshot[1] = snapshot;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    static public Database getSingleton() {
        Log.d("getting","singleton");
        if (singleton == null) {
            Log.d("singleton","null");
            singleton = new Database();
            Log.d("database","new");
        }
        Log.d("done with ","singleton");
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
     * @param mealRecord MealRecord, the id shouldn't be empty
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
     * @param startDate, LocalDate
     * @param endDate,   LocalDate
     * @Author: Wang binli
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public MealRecord[] queryByDate(LocalDate startDate, LocalDate endDate) throws EmptyResultException {
        MealRecord[] mealRecords = queryAllMealRecords();
        ArrayList<MealRecord> results = new ArrayList<>();
        for (MealRecord mealRecord : mealRecords) {
            LocalDate date = mealRecord.getTime().toLocalDate();
            if (date.minusDays(7).isAfter(startDate) || date.plusDays(7).isBefore(endDate)) {
                continue;
            }
            results.add(mealRecord);
        }
        MealRecord[] returnVals = new MealRecord[results.size()];
        returnVals = results.toArray(returnVals);
        return returnVals;
    }

    /**
     * Queries all meal records within one day
     * @param queryDate
     * @return
     * @throws EmptyResultException
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public MealRecord[] queryByDate(LocalDate queryDate) throws EmptyResultException{
        MealRecord[] mealRecords = queryAllMealRecords();
        ArrayList<MealRecord> results = new ArrayList<>();
        for (MealRecord mealRecord : mealRecords) {
            LocalDate date = mealRecord.getTime().toLocalDate();
            if (queryDate.equals(date)) {
                results.add(mealRecord);
            }
        }
        MealRecord[] returnVals = new MealRecord[results.size()];
        returnVals = results.toArray(returnVals);
        return returnVals;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public MealRecord[] queryAllMealRecords() throws EmptyResultException {
        ArrayList<MealRecord> mealRecords = parseMealRecords(dataSnapshot[0].child("MealRecords"));
        return mealRecords.toArray(new MealRecord[0]);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static ArrayList<MealRecord> parseMealRecords(DataSnapshot dataSnapshot) throws EmptyResultException {
        ArrayList<MealRecord> mealRecords = new ArrayList<>();
        try {
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
                    food.setActualIntake(foodRecord.child("weight").getValue(Double.class));
                    mealRecord1.addFood(food);
                    mealRecords.add(mealRecord1);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new EmptyResultException();
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
     * Queries the user information
     * @return
     */
    public HealthInfo queryHealthInfo() {
        HealthInfo healthInfo = new HealthInfo();
        for (DataSnapshot attribute: dataSnapshot[0].getChildren()) {
            String key = attribute.getKey();
            switch (key) {
                case "age":
                    healthInfo.setAge(attribute.getValue(Integer.class));
                    break;
                case "dailyActivityLevel":
                    String level = attribute.getValue(String.class);
                    if (level.equals("NONE")) {
                        healthInfo.setDailyActivityLevel(Activity.NONE);
                    } else if (level.equals("LITTLE")) {
                        healthInfo.setDailyActivityLevel(Activity.LITTLE);
                    } else if (level.equals("MODERATE")) {
                        healthInfo.setDailyActivityLevel(Activity.MODERATE);
                    }  else if (level.equals("HIGH")) {
                        healthInfo.setDailyActivityLevel(Activity.HIGH);
                    }
                    break;
                case "gender":
                    String gender = attribute.getValue(String.class);
                    if (gender.equals("FEMALE")) {
                        healthInfo.setGender(Gender.FEMALE);
                    } else if (gender.equals("MALE")) {
                        healthInfo.setGender(Gender.OTHERS);
                    } else if (gender.equals("OTHERS")) {
                        healthInfo.setGender(Gender.OTHERS);
                    }
                    break;
                case "goalWeight":
                    double goalWeight = attribute.getValue(Double.class);
                    healthInfo.setGoalWeight(goalWeight);
                    break;
                case "height":
                    double height = attribute.getValue(Double.class);
                    healthInfo.setHeight(height);
                    break;
                case "suggestCalorieIntake":
                    Log.d("went into", "suggest");
                    double suggestCalorie = attribute.getValue(Double.class);
                    Log.d("went into", "suggest2");
                    healthInfo.setSuggestCalorieIntake(suggestCalorie);
                    Log.d("finish", "suggest2");
                    break;
                case "weight":
                    double weight = attribute.getValue(Double.class);
                    healthInfo.setWeight(weight);
            }
        }
        return healthInfo;
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
        database.getReference().child("Users").child(userId).setValue(value);
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

    /**
     * @param nutrientName the nutrientName to query
     * @return HashMap<String, Double>, key is the name of food, Double is the value of the containment
     */
    public HashMap<String, Double> queryRecommendFood(String nutrientName) {
        HashMap<String, Double> recommendFood = new HashMap<>();
        for (DataSnapshot dataSnapshot: dataSnapshot[1].child(nutrientName).getChildren()) {
            String foodName = dataSnapshot.getKey();
            try {
                Double value = dataSnapshot.child("value").getValue(Double.class);
                recommendFood.put(foodName, value);
            } catch (com.google.firebase.database.DatabaseException e) {
                continue;
            }
        }
        return recommendFood;
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