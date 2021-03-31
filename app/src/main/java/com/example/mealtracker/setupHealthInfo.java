package com.example.mealtracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class setupHealthInfo extends AppCompatActivity {

    private String gender, activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_health_info);

        // Gender dropdown box
        Spinner gender_spinner = (Spinner) findViewById(R.id.gender_spinner);
        List<String> gender_spinner_values = new ArrayList<>();
        gender_spinner_values.add(0, "Select a gender ");
        gender_spinner_values.add("Male");
        gender_spinner_values.add("Female");
        gender_spinner_values.add("Other");

        ArrayAdapter<String> gender_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gender_spinner_values);
        gender_adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        gender_spinner.setAdapter(gender_adapter);
        gender_spinner.setPrompt("Gender: ");
        gender_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select a gender ")) {
                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    gender = item;
                    Toast.makeText(parent.getContext(), "Selected gender: " + item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Activity dropdown box
        Spinner activity_spinner = (Spinner) findViewById(R.id.activity_spinner);
        List<String> activity_spinner_values = new ArrayList<>();
        activity_spinner_values.add(0, "Select an activity ");
        activity_spinner_values.add("None");
        activity_spinner_values.add("Little");
        activity_spinner_values.add("Moderate");
        activity_spinner_values.add("High");

        ArrayAdapter<String> activity_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, activity_spinner_values);
        activity_adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        activity_spinner.setAdapter(activity_adapter);
        activity_spinner.setPrompt("Activity: ");
        activity_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select an activity ")) {
                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    activity = item;
                    Toast.makeText(parent.getContext(), "Selected activity: " + item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private TextInputLayout textInputHeight, textInputWeight, textInputAge, textInputGoalWeight;
    private EditText editHeight, editWeight, editAge, editGoalWeight;

    public void onSubmitBtnClick (View view) {
        // TODO: go to control logic - check input
        textInputHeight = findViewById(R.id.height_txt);
        textInputWeight = findViewById(R.id.weight_txt);
        textInputAge = findViewById(R.id.age_txt);
        textInputGoalWeight = findViewById(R.id.goal_weight_txt);

        editHeight = findViewById(R.id.editHeight);
        editWeight = findViewById(R.id.editWeight);
        editAge = findViewById(R.id.editAge);
        editGoalWeight = findViewById(R.id.editGoalWeight);

        //get string from input
        String heightInput = textInputHeight.getEditText().getText().toString().trim();
        String weightInput = textInputWeight.getEditText().getText().toString().trim();
        String ageInput = textInputAge.getEditText().getText().toString().trim();
        String goalWeightInput = textInputGoalWeight.getEditText().getText().toString().trim();

        HealthInfoManager healthInfoManager = new HealthInfoManager();
        HashMap<String, String> info = new HashMap<String, String>();
        info.put("height", heightInput);
        info.put("weight", weightInput);
        info.put("age", ageInput);
        info.put("goal weight", goalWeightInput);
        info.put("gender", gender);
        info.put("activity", activity);

        healthInfoManager.setHealthInfo(info);

        Toast.makeText(view.getContext(), "Registration successful", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        // TODO: save to database or change database data
    }

}