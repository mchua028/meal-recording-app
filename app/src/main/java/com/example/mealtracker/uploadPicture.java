package com.example.mealtracker;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class uploadPicture extends AppCompatActivity {
    private static final String TAG = "uploadPictureActivity";

    // constants
    private static final int ACTIVITY_NUM = 2;
    private static final int VERIFY_PERMISSIONS_REQUEST = 1;
    public static int INSTANT_FRAGMENT_ID;
    public static int IMPORT_FRAGMENT_ID;

    private Context mContext = uploadPicture.this;
    public BottomNavigationView bottomNav;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_calories_upload_picture);
        Log.d(TAG, "onCreate: started.");

        // check permissions (camera, read/write to external storage)
        if (checkPermissionsArray(Permissions.PERMISSIONS)) {

        } else {
            verifyPermissions(Permissions.PERMISSIONS);
        }

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new CameraFragment()).commit();
    }

    /**
     * return current tab number
     * 0 = Instant
     * 1 = Import
     * 2 = Cancel
     * @return
     */
    public int getCurrentTabNumber() {
        return bottomNav.getSelectedItemId();
    }

    /**
     * Verify all the permissions passed to the array
     * @param permissions
     */
    public void verifyPermissions(String[] permissions) {
        Log.d(TAG, "verifyPermissions: verifying permissions.");

        ActivityCompat.requestPermissions(
                uploadPicture.this,
                permissions,
                VERIFY_PERMISSIONS_REQUEST
        );
    }

    /**
     * Check an array of permissions
     * @param permissions
     * @return
     */
    public boolean checkPermissionsArray(String[] permissions) {
        Log.d(TAG, "checkPermissionsArray: checking permissions array.");

        for (int i=0; i<permissions.length; i++) {
            String check = permissions[i];
            if (!checkPermissions(check)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check a single permission if it has been verified
     * @param permission
     * @return
     */
    public boolean checkPermissions(String permission) {
        Log.d(TAG, "checkPermissions: checking permission: " + permission);

        int permissionRequest = ActivityCompat.checkSelfPermission(uploadPicture.this, permission);

        if (permissionRequest != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "checkPermissions: \n Permission was not granted for: " + permission);
            return false;
        } else {
            Log.d(TAG, "checkPermissions: \n Permission was granted for: " + permission);
            return true;
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_instant:
                            selectedFragment = new CameraFragment();
                            INSTANT_FRAGMENT_ID = bottomNav.getSelectedItemId();
                            break;
                        case R.id.nav_import:
                            selectedFragment = new GalleryFragment();
                            IMPORT_FRAGMENT_ID = bottomNav.getSelectedItemId();
                            break;
                        case R.id.nav_cancel:
                            Intent intent = new Intent(uploadPicture.this, MainActivity.class);
                            startActivity(intent);
                            //finish();
                            // selectedFragment = new CancelFragment();
                            // TODO: fix this or try to put a cancel button
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };


}
