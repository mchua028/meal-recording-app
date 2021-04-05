package com.example.mealtracker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;


public class GalleryFragment extends Fragment {

    // TODO: CONNECT WITH GALLERY

    private static final String TAG = "GalleryFragment";

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int RESULT_OK = -1;
    private Button mChooseImageBtn;
    private Button mUploadBtn;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private Uri mImageUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_calories_gallery, container, false);

        Log.d(TAG, "onCreateView");

        mChooseImageBtn = view.findViewById(R.id.chooseImageBtn);
        mUploadBtn = view.findViewById(R.id.uploadBtn);
        mImageView = view.findViewById(R.id.imageView);
        mProgressBar = view.findViewById(R.id.progressBar);
        
        mChooseImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        
        mUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
        
        return view;
    }
    
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

        Picasso.with(getActivity()).load(mImageUri).into(mImageView);
        }
    }
}