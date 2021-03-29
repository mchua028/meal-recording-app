package com.example.mealtracker;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class manualInput extends AppCompatActivity {
    public BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_input);

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        // TODO : create fragments
        // TODO: add more food, remove food button
        // TODO: connect to db
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new CameraFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_input_food:
                            break;
                        case R.id.nav_count_calories:
                            break;
                        case R.id.nav_cancel:
                            //Intent intent = new Intent(uploadPicture.this, MainActivity.class);
                            //startActivity(intent);
                            finish();
                            // selectedFragment = new CancelFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}