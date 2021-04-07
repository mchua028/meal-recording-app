package com.example.mealtracker.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mealtracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        //Toolbar toolbar = findViewById(R.id.main_toolbar);
        //setSupportActionBar(toolbar);
        //ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        //actionBar.setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void onSubmitClick(View view) {
        TextView txtOldPw = findViewById(R.id.txtOldPw);
        TextView txtNewPw = findViewById(R.id.txtNewPw);
        TextView txtConfirmNewPw = findViewById(R.id.txtConfirmNewPw);

        EditText editOldPw = findViewById(R.id.txtOldPw);
        EditText editNewPw = findViewById(R.id.txtNewPw);
        EditText editConfirmNewPw = findViewById(R.id.txtConfirmNewPw);

        //get string from input
        String oldPw = editOldPw.getText().toString().trim();
        String newPw = editNewPw.getText().toString().trim();
        String confirmNewPw = editConfirmNewPw.getText().toString().trim();

        if (TextUtils.isEmpty(newPw)){
            txtNewPw.setError("Password is required.");
            return;
        }

        if (newPw.length()<6){
            txtNewPw.setError("Password must be at least 6 characters");
            return;
        }

        if (newPw.equals(oldPw)){
            txtNewPw.setError("New password cannot be the same as old password");
            return;
        }

        if(newPw.equals(confirmNewPw)==false) {
            txtConfirmNewPw.setError("Passwords do not match");
            return;
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();
        String email = user.getEmail();

        AuthCredential credential = EmailAuthProvider
                .getCredential(email, oldPw);




        // Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            user.updatePassword(newPw).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("changePw:", "Password updated");
                                        Toast.makeText(ChangePassword.this,"Password updated successfully",Toast.LENGTH_SHORT).show();
                                    } else {
                                        Log.d("changePw:", "Error password not updated");
                                    }
                                }
                            });
                        } else {
                            Log.d("reauthentication:", "Error auth failed");
                            Toast.makeText(ChangePassword.this,"Old Password is wrong",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}