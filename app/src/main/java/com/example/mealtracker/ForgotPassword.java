package com.example.mealtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
    }
    public void onResetPasswordButtonClick(View view) {
        TextView txtEmail = findViewById(R.id.txtResetPwUser);

        EditText editEmail = findViewById(R.id.txtResetPwUser);

        //get string from input
        String email = editEmail.getText().toString().trim();


        firebaseAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("resetpwemail", "Email sent.");
                            Toast.makeText(ForgotPassword.this,"Reset password link sent to"+email,
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Log.e("emailError", "sendResetPwEmail", task.getException());
                            Toast.makeText(ForgotPassword.this,
                                    "Failed to send reset password email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}