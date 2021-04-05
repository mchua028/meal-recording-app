package com.example.mealtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.VerifiedInputEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

public class VerifyEmailUI extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("enteringgg","VerifyEmailUI!!!");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_email);
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.getCurrentUser().sendEmailVerification(ActionCodeSettings.newBuilder().build());
        firebaseAuth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(VerifyEmailUI.this,"A verification email has been sent to your email.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private TextInputLayout textInputVerificationCode;
    private EditText editVerificationCode;

    public void onVerifyEmailClick(View view) {
        Intent goToSetupHealthInfo = new Intent(this, setupHealthInfo.class);

        // access to textInputLayout
        textInputVerificationCode = findViewById(R.id.txtVerifiCode);

        editVerificationCode = findViewById(R.id.editVerifiCode);

        //get string from input
        String verifiCode = textInputVerificationCode.getEditText().getText().toString();
        /*if(TextUtils.isEmpty(verifiCode)){
               Toast.makeText(VerifyEmailUI.this,"Please enter")
           }*/
    }
    public boolean verifyCode(){
            return true;
        }
}
