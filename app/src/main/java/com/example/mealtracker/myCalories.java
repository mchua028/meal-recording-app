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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.mealtracker.AppLogic.MealRecordManager;
import com.example.mealtracker.DAO.HealthInfo;
import com.example.mealtracker.DAO.MealRecord;
import com.example.mealtracker.UI.InputFoodDetails;
import com.example.mealtracker.UI.MyMealInformation;
import com.example.mealtracker.UI.editCalories;
import com.example.mealtracker.UI.uploadPicture;
/*import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;*/
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.junit.experimental.theories.DataPoint;

import java.util.ArrayList;

public class myCalories extends Fragment {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    //BarChart mpBarChart;

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
        return v;
    }
}

        /**
         * Create bar graph for weekly calories
         */
        /*mpBarChart = v.findViewById(R.id.myCaloriesBarChart);
        BarDataSet barDataSet1 = new BarDataSet(dataValues1(), "Data Set 1");
        ArrayList<IBarDataSet> datasets = new ArrayList<>();
        datasets.add(barDataSet1);

        BarData barData = new BarData(datasets);
        mpBarChart.setData(barData);
        mpBarChart.invalidate();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("MealRecords");
        String id = databaseReference.push().getKey();
        int xDay = BarGraphValues.getxValue();
        int yCalories = BarGraphValues.getyValue();

        return v;
    }

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
    }*/


