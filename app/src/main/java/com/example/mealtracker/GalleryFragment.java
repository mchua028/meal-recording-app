package com.example.mealtracker;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


public class GalleryFragment extends Fragment {

    private static final String TAG = "GalleryFragment";

    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int RESULT_OK = -1;
    private Button mChooseImageBtn;
    private Button mUploadBtn;
    private EditText mEditTextFileName;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private Uri mImageUri;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_calories_gallery, container, false);

        Log.d(TAG, "onCreateView");

        mChooseImageBtn = view.findViewById(R.id.chooseImageBtn);
        mUploadBtn = view.findViewById(R.id.uploadBtn);
        mEditTextFileName = view.findViewById(R.id.editTextFileName);
        mImageView = view.findViewById(R.id.imageView);
        mProgressBar = view.findViewById(R.id.progressBar);

        mStorageRef = FirebaseStorage.getInstance().getReference("Uploads");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Uploads");
        
        mChooseImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        
        mUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
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

    /**
     * Returns extension name of file e.g. jpeg, png
     * @param uri
     * @return extension
     */
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getActivity().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        if (mImageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                + "." + getFileExtension(mImageUri));   // e.g. name of file = timeInMilliseconds.jpeg

            fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);
                                }
                            }, 5000);

                            Toast.makeText(getActivity(), "Upload successful", Toast.LENGTH_LONG).show();
                            GalleryFragmentUpload upload = new GalleryFragmentUpload(mEditTextFileName.getText().toString().trim(),
                                    taskSnapshot.getMetadata().getReference().getDownloadUrl().toString());
                            String uploadId = mDatabaseRef.push().getKey();
                            mDatabaseRef.child(uploadId).setValue(upload);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                            mProgressBar.setProgress((int) progress);
                        }
                    });
        }
        else {
            Toast.makeText(getActivity(), "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
}