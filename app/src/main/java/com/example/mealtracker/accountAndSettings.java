package com.example.mealtracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class accountAndSettings extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Account and Settings");

        View v = inflater.inflate(R.layout.fragment_account_and_settings, container, false);

        // Gender dropdown box
        Spinner gender_spinner = (Spinner) v.findViewById(R.id.gender_spinner);
        List<String> gender_spinner_values = new ArrayList<>();
        gender_spinner_values.add(0, "Select a gender ");
        gender_spinner_values.add("Male");
        gender_spinner_values.add("Female");
        gender_spinner_values.add("Other");

        ArrayAdapter<String> gender_adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, gender_spinner_values);
        gender_adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        gender_spinner.setAdapter(gender_adapter);
        gender_spinner.setPrompt("Gender: ");
        gender_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select a gender ")) {
                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected gender: " + item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Activity dropdown box
        Spinner activity_spinner = (Spinner) v.findViewById(R.id.activity_spinner);
        List<String> activity_spinner_values = new ArrayList<>();
        activity_spinner_values.add(0, "Select an activity ");
        activity_spinner_values.add("None");
        activity_spinner_values.add("Little");
        activity_spinner_values.add("Moderate");
        activity_spinner_values.add("High");

        ArrayAdapter<String> activity_adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, activity_spinner_values);
        activity_adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        activity_spinner.setAdapter(activity_adapter);
        activity_spinner.setPrompt("Activity: ");
        activity_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select an activity ")) {
                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected activity: " + item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Submit button
        Button submitBtn = (Button) v.findViewById(R.id.submit_btn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: go to control logic - check input
                // TODO: save to database or change database data

                // if input correct, save data and show success message
                Toast.makeText(getActivity().getApplicationContext(), "Edit successful", Toast.LENGTH_SHORT).show();
            }

        });
        return v;
    }

    public void onChangePWBtnClick (View view) {
        // TODO: go to change password UI
    }
}
