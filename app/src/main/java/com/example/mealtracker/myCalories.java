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
import com.example.mealtracker.UI.InputFoodDetails;
import com.example.mealtracker.UI.editCalories;
import com.example.mealtracker.UI.uploadPicture;

public class myCalories extends Fragment {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

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
        HealthInfo healthInfo = new HealthInfo();
        MealRecordManager mealRecordManager = new MealRecordManager();

        Log.d("uiedittext", Double.toString(healthInfo.getSuggestCalorieIntake()));
        TextView text = (TextView) v.findViewById(R.id.myCaloriesTodayMiniTxt);
        text.setText("  Maximum Calories Today           " + Double.toString(healthInfo.getSuggestCalorieIntake()) +
                     "\n  Calories Eaten                              " + Double.toString(mealRecordManager.getCalorieConsumedToday()) +
                     "\n  Remaining Calories                      " + Double.toString(mealRecordManager.getCalorieRemaining()));

        return v;
    }
}