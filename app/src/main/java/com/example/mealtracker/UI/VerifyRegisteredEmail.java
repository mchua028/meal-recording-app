package com.example.mealtracker.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.mealtracker.AppLogic.AccountManager;
import com.example.mealtracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class VerifyRegisteredEmail extends AppCompatActivity {
    FirebaseAuth firebaseAuth = AccountManager.getSingleton().getFirebaseAuth();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_registered_email);
        /*firebaseAuth.getCurrentUser().sendEmailVerification(ActionCodeSettings.newBuilder().build());
        firebaseAuth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(VerifyRegisteredEmail.this,"A verification email has been sent to your email.",Toast.LENGTH_SHORT).show();
            }
        });*/
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        user.sendEmailVerification()
                .addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(VerifyRegisteredEmail.this,
                                    "Verification email sent to " + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("emailError", "sendEmailVerification", task.getException());
                            Toast.makeText(VerifyRegisteredEmail.this,
                                    "Failed to send verification email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        Log.d("VERIFYING","Emailaddress");

    }

    public void onContinueClick(View view) {
        firebaseAuth.getCurrentUser().reload();
        if(firebaseAuth.getCurrentUser().isEmailVerified()) {
            Toast.makeText(VerifyRegisteredEmail.this,"Email successfully verified.",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(view.getContext(), setupHealthInfo.class));
        }
        else{
            Toast.makeText(VerifyRegisteredEmail.this,"Please verify your email address to continue.",Toast.LENGTH_SHORT).show();
        }
    }


}