package com.example.mealtracker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ParseException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

//import com.squareup.picasso.Picasso;

import com.example.mealtracker.DAO.Food;
import com.example.mealtracker.Exceptions.EmptyResultException;
import com.example.mealtracker.Exceptions.HttpsErrorException;
import com.example.mealtracker.UI.MainActivity;
import com.example.mealtracker.UI.MyMealInformation;
import com.example.mealtracker.UI.uploadPicture;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.ContentBody;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class CameraFragment extends Fragment {

    private static final String TAG = "CameraFragment";

    // constant
    private static final int INSTANT_FRAGMENT_NUM = 0;
    private static final int IMPORT_FRAGMENT_NUM = 1;
    private static final int CAMERA_REQUEST_CODE = 5;
    private static final int CAMERA_PERM_CODE = 6;

    private static final int RESULT_OK = -1;

    private ImageView mImageView;
    private Uri mImageUri;
    String currentPhotoPath;

    private Button mCountCaloriesBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_calories_camera, container, false);
        Log.d(TAG, "onCreateView: started.");

        mImageView = view.findViewById(R.id.cameraImageView);
        mCountCaloriesBtn = view.findViewById(R.id.cameraCountCaloriesBtn);

        Button launchCameraBtn = (Button) view.findViewById(R.id.launchCameraBtn);
        launchCameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Log.d(TAG, "onClick: launching camera.");

                //if (((uploadPicture)getActivity()).getCurrentTabNumber() == uploadPicture.INSTANT_FRAGMENT_ID) {
                //Log.d(TAG, "onClick: first if");
                if (((uploadPicture)getActivity()).checkPermissions(Permissions.CAMERA_PERMISSION[0])) {
                    Log.d(TAG, "onClick: starting camera.");
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
                } else {
                    Intent intent = new Intent(getActivity(), uploadPicture.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });

        mCountCaloriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        return view;
    }

    public void goToMyMealInformation(View view) {
        Intent intent = new Intent(getContext(), MyMealInformation.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {

            //mImageUri = data.getData();
            //Picasso.with(getActivity()).load(mImageUri).into((ImageView) getActivity().findViewById(R.id.cameraImageView));

            Bitmap image = (Bitmap) data.getExtras().get("data");
            mImageView.setImageBitmap(image);

            int byteSize = image.getRowBytes() * image.getHeight();
            ByteBuffer byteBuffer = ByteBuffer.allocate(byteSize);
            image.copyPixelsToBuffer(byteBuffer);

            byte[] byteArray = byteBuffer.array();

            ByteArrayInputStream bs = new ByteArrayInputStream(byteArray);

            Food food_img;
            try {
                food_img = Food.searchFoodsFromImg(bs);
                Log.d("food", food_img.getName());
            } catch (HttpsErrorException e) {
                e.printStackTrace();
            } catch (EmptyResultException e) {
                e.printStackTrace();
            }
        }
    }


}