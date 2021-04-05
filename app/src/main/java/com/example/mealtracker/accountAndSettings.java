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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class accountAndSettings extends Fragment {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

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

        //change password button
        Button changePWBtn = (Button) v.findViewById(R.id.change_password_btn);
        changePWBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), ChangePassword.class));

                String email = firebaseAuth.getCurrentUser().getEmail();
                /*firebaseAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.d("resetpwemail", "Email sent.");
                                    Toast.makeText(getActivity().getApplicationContext(),
                                            "Reset password link sent to" + email,
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.e("emailError", "sendResetPwEmail", task.getException());
                                    Toast.makeText(getActivity().getApplicationContext(),
                                            "Failed to send reset password email.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                firebaseAuth.getCurrentUser().reauthenticateAndRetrieveData();
            }

        });*/
                /*AuthCredential credential = EmailAuthProvider
                        .getCredential(email, "password1234");

                // Prompt the user to re-provide their sign-in credentials
                user.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    user.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Log.d(TAG, "Password updated");
                                            } else {
                                                Log.d(TAG, "Error password not updated")
                                            }
                                        }
                                    });
                                } else {
                                    Log.d(TAG, "Error auth failed")
                                }
                            }
                        });

                 */
            }
        });
        return v;

    }
}