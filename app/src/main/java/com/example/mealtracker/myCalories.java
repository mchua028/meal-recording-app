package com.example.mealtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

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

        return v;
    }
}