package com.example.mealtracker.UI;import androidx.annotation.NonNull;import androidx.appcompat.app.ActionBarDrawerToggle;import androidx.appcompat.app.AppCompatActivity;import androidx.appcompat.widget.Toolbar;import androidx.core.view.GravityCompat;import androidx.drawerlayout.widget.DrawerLayout;import android.content.Intent;import android.os.Bundle;import android.util.Log;import android.view.MenuItem;import android.view.View;import android.widget.TextView;import android.widget.Toast;import com.example.mealtracker.DAO.Database;import com.example.mealtracker.R;import com.example.mealtracker.foodRecommendations;import com.google.android.gms.auth.api.signin.GoogleSignIn;import com.google.android.gms.auth.api.signin.GoogleSignInOptions;import com.google.android.gms.tasks.OnFailureListener;import com.google.android.gms.tasks.OnSuccessListener;import com.google.android.material.navigation.NavigationView;import com.google.firebase.auth.FirebaseAuth;public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {    private DrawerLayout drawerLayout;    private NavigationView navigationView;    View headerView;    // TODO: Change password    @Override    protected void onCreate(Bundle savedInstanceState) {        Database.getSingleton();  // init        Log.d("helloooooooooo1","hi2");        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_main);        Toolbar toolbar = findViewById(R.id.main_toolbar);        setSupportActionBar(toolbar);        drawerLayout = findViewById(R.id.drawer_layout);        navigationView = findViewById(R.id.nav_view);//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();//        headerView = navigationView.getHeaderView(0);//        TextView textViewUsername = (TextView) headerView.findViewById(R.id.textViewUsernameNav);//        if (firebaseAuth.getCurrentUser().isEmailVerified()//                && firebaseAuth.getCurrentUser().getEmail().trim() != null//                && !firebaseAuth.getCurrentUser().getEmail().trim().isEmpty()) {//            String userEmail = firebaseAuth.getCurrentUser().getEmail().split("@")[0];//            textViewUsername.setText(userEmail);//        }//        else {//            textViewUsername.setText("null");//        }        navigationView.setNavigationItemSelectedListener(this);        /*headerView = navigationView.getHeaderView(0);        textViewUsername = (TextView) headerView.findViewById(R.id.textViewUsernameNav);        SharedPreferences sharedPrefManager = this.getSharedPreferences(textViewUsername.toString(), 0);        textViewUsername.setText(sharedPrefManager.getInstance(this).getUsername());        navigationView.setNavigationItemSelectedListener(this);        */        Log.d("helloooooooooo2","hi2");        // get hamburger icon on top left to toggle nav bar ///////////        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(                this,                drawerLayout,                toolbar,                R.string.open_nav_drawer,                R.string.close_nav_drawer        );        drawerLayout.addDrawerListener(actionBarDrawerToggle);        actionBarDrawerToggle.syncState();        ///////////////////////////////////////////////////////////////        // show MyCalories fragment when start main activity        if (savedInstanceState == null) {            // show My Calories is selected in navigation bar            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new myCalories()).commit();            // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new myCalories()).commit();            navigationView.setCheckedItem(R.id.nav_myCalories);        }        navigationView.setNavigationItemSelectedListener(this);        Log.d("helloooooooooo3","hi2");        // extract email from google        // FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();//        Log.d("tag", "onCreate: " + firebaseAuth.getCurrentUser().getEmail());//        Log.d("helloooooooooo4","hi2");    }    public void logout(final View view) {        FirebaseAuth.getInstance().signOut();        GoogleSignIn.getClient(this, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build())                .signOut().addOnSuccessListener(new OnSuccessListener<Void>() {            @Override            public void onSuccess(Void aVoid) {                FirebaseAuth.getInstance().signOut();                startActivity(new Intent(view.getContext(), LoginOrRegister.class));            }        }).addOnFailureListener(new OnFailureListener() {            @Override            public void onFailure(@NonNull Exception e) {                Toast.makeText(MainActivity.this, "Signout Failed", Toast.LENGTH_SHORT).show();            }        });    }    @Override    public boolean onNavigationItemSelected(@NonNull MenuItem item) {        switch(item.getItemId()) {            case R.id.nav_myCalories:                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new myCalories()).commit();                break;            case R.id.nav_myMeals:                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new myMeals()).commit();                break;            case R.id.nav_foodRecommendations:                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new foodRecommendations()).commit();                break;            case R.id.nav_accountAndSettings:                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new accountAndSettings()).commit();                break;            case R.id.nav_logout:                logout(navigationView);                break;        }        drawerLayout.closeDrawer(GravityCompat.START);        return true;    }    // close navigation menu on back press    @Override    public void onBackPressed() {        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {            drawerLayout.closeDrawer(GravityCompat.START);        } else {            super.onBackPressed();        }    }}