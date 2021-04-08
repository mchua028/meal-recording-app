package com.example.mealtracker;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.mealtracker.AppLogic.MealRecordManager;
import com.example.mealtracker.DAO.Database;
import com.example.mealtracker.DAO.HealthInfo;
import com.example.mealtracker.DAO.MealRecord;
import com.example.mealtracker.UI.InputFoodDetails;
import com.example.mealtracker.UI.editCalories;
import com.example.mealtracker.UI.uploadPicture;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.junit.experimental.theories.DataPoint;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class myCalories extends Fragment {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    BarChart mpBarChart;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    //BarDataSet barDataSet = new BarDataSet(null, null);
    ArrayList<IBarDataSet> iBarDataSets = new ArrayList<>();
    BarData barData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_calories, container, false);
        getActivity().setTitle("My Calories");

        // Upload Picture button
        Button onUploadPictureBtn = (Button) v.findViewById(R.id.uploadPictureBtn);
        onUploadPictureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), uploadPicture.class));
            }
        });

        // Manual input meal button
        Button onInputDetailsBtnClick = (Button) v.findViewById(R.id.inputDetailsBtn);
        onInputDetailsBtnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), InputFoodDetails.class));
            }
        });

        // Edit meal button
        ImageButton onEditCaloriesBtnClick = (ImageButton) v.findViewById(R.id.editCaloriesBtn);
        onEditCaloriesBtnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), editCalories.class));
            }
        });
        HealthInfo healthInfo = HealthInfo.getSingleton();
        MealRecordManager mealRecordManager = MealRecordManager.getSingleton();
        MealRecord[] mealRecords = null;
        //mealRecords = MealRecord.queryAll(); - app crash at this
        //if (mealRecords.length == 0) {
         //   Toast.makeText(getActivity(), "You haven't added any meal records", Toast.LENGTH_SHORT).show();
        //}


        Log.d("uiedittext", Double.toString(healthInfo.getSuggestCalorieIntake()));
        TextView text = (TextView) v.findViewById(R.id.myCaloriesTodayMiniTxt);
        text.setText("  Maximum Calories Today           " + Double.toString(healthInfo.getSuggestCalorieIntake()) +
                     "\n  Calories Eaten                              " + Double.toString(mealRecordManager.getCalorieConsumedToday()) +
                     "\n  Remaining Calories                      " + Double.toString(mealRecordManager.getCalorieRemaining()));


        /**
         * Create bar graph for weekly calories
         */
        mpBarChart = v.findViewById(R.id.myCaloriesBarChart);
        mpBarChart.setDescription(null);

        //String[] days = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        XAxis xAxis = mpBarChart.getXAxis();
        xAxis.setLabelCount(7);
        //xAxis.setValueFormatter(new IndexAxisValueFormatter(days));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        YAxis yAxisLeft = mpBarChart.getAxisLeft();
        YAxis yAxisRight = mpBarChart.getAxisRight();
        Log.d("aft", "barchart axis");

        // to show dummy data

        BarDataSet barDataSet1 = new BarDataSet(dataValues1(), "Calories");

        ArrayList<IBarDataSet> datasets = new ArrayList<>();
        datasets.add(barDataSet1);

        BarData barData = new BarData(datasets);
        mpBarChart.setData(barData);
        mpBarChart.invalidate();


        /*
        Log.d("b4", "firebase db");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");
        String id = databaseReference.push().getKey();
        //String id = Database.getSingleton().getUserReference().getKey();
        //String id = firebaseDatabase.getReference().child("MealRecords").getKey();
        Log.d("id", id);

        ArrayList<BarEntry> dataVals = new ArrayList<BarEntry>();
        LocalDate endDate = LocalDate.now().plusDays(1);
        LocalDate startDate = endDate.minusDays(7);
        float dayId = Integer.parseInt(startDate.format(DateTimeFormatter.ofPattern("ddMM")));
        float calories= 0;
        Log.d("b4", "for loop");
        for (int i=0; i<6; i++) {       // for each bar
            dayId = Integer.parseInt(startDate.plus(i, ChronoUnit.DAYS).format(DateTimeFormatter.ofPattern("ddMM")));
            Log.d("dayId", String.valueOf(dayId));
            //calories = MealRecordManager.getSingleton().getCalorieIntakeInWeek().get(i).floatValue();
            Log.d("caloriesInWeek", String.valueOf(calories));
            dataVals.add(new BarEntry(dayId, calories));
        }
        Log.d("aft", "for loop");

        DataPoints dataPoints = new DataPoints(dayId, calories);
        Log.d("b4", "setValue(dataPoints)");
        //error line
        databaseReference.child(id).setValue(dataPoints);

        Log.d("b4", "retrieve data");
        retrieveData();*/


        return v;
    }
    /*
    public class xAxisValueFormatter implements XAxisValueFormatter {

    }*/

    // when data is changed
    private void retrieveData() {
        (firebaseDatabase.getReference().child("MealRecords")).addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<BarEntry> dataVals = new ArrayList<BarEntry>();

                if (snapshot.hasChildren()) {
                    for (DataSnapshot myDataSnapshot : snapshot.getChildren()) {
                        LocalDate endDate = LocalDate.now().plusDays(1);
                        LocalDate startDate = endDate.minusDays(7);
                        float dayId = Integer.parseInt(startDate.format(DateTimeFormatter.ofPattern("ddMM")));
                        float calories= 0;

                        Log.d("b4", "for loop");
                        for (int i=0; i<6; i++) {       // for each bar
                            dayId = Integer.parseInt(startDate.plus(i, ChronoUnit.DAYS).format(DateTimeFormatter.ofPattern("ddMM")));
                            Log.d("dayId", String.valueOf(dayId));
                            //calories = MealRecordManager.getSingleton().getCalorieIntakeInWeek().get(i).floatValue();
                            Log.d("caloriesInWeek", String.valueOf(calories));
                            dataVals.add(new BarEntry(dayId, calories));
                        }
                        Log.d("aft", "for loop");

                        //error line
                        DataPoints dataPoints = (DataPoints) myDataSnapshot.getValue(DataPoints.class);
                        Log.d("aft", "datapoints");
                    }
                    showChart(dataVals);
                    Log.d("aft","showchart");
                }
                else {
                    mpBarChart.clear();
                    mpBarChart.invalidate();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void showChart(ArrayList<BarEntry> dataVals) {
        BarDataSet barDataSet = null;
        barDataSet.setValues(dataVals);
        barDataSet.setLabel("DataSet 1");
        iBarDataSets.clear();
        iBarDataSets.add(barDataSet);
        barData = new BarData(iBarDataSets);
        mpBarChart.clear();
        mpBarChart.setData(barData);
        mpBarChart.invalidate();
    }

    // dummy values
    /*
    private ArrayList<BarEntry> dataValues1() {
        ArrayList<BarEntry> dataVals1 = new ArrayList<BarEntry>();
        dataVals1.add(new BarEntry(0,1));
        dataVals1.add(new BarEntry(1,2));
        dataVals1.add(new BarEntry(2,3));
        dataVals1.add(new BarEntry(3,4));
        dataVals1.add(new BarEntry(4,5));
        dataVals1.add(new BarEntry(5,6));
        dataVals1.add(new BarEntry(6,7));
        return dataVals1;
    }
    */

    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<BarEntry> dataValues1() {
        ArrayList<BarEntry> dataVals1 = new ArrayList<BarEntry>();
        LocalDate endDate = LocalDate.now().plusDays(1);
        LocalDate startDate = endDate.minusDays(7);
        float dayId = Integer.parseInt(startDate.format(DateTimeFormatter.ofPattern("ddMM")));
        float calories= 0;

        Log.d("b4", "for loop");
        for (int i=0; i<6; i++) {       // for each bar
            dayId = Integer.parseInt(startDate.plus(i, ChronoUnit.DAYS).format(DateTimeFormatter.ofPattern("ddMM")));
            Log.d("dayId", String.valueOf(dayId));
            //calories = MealRecordManager.getSingleton().getCalorieIntakeInWeek().get(i).floatValue();
            Log.d("caloriesInWeek", String.valueOf(calories));
            dataVals1.add(new BarEntry(dayId, calories));
        }
        Log.d("aft", "for loop");
        return dataVals1;
    }

}
