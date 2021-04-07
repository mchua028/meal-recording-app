package com.example.mealtracker;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ParseException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
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

import com.squareup.picasso.Picasso;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.ContentBody;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_calories_camera, container, false);
        Log.d(TAG, "onCreateView: started.");

        mImageView = view.findViewById(R.id.cameraImageView);

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
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {

            //mImageUri = data.getData();
            //Picasso.with(getActivity()).load(mImageUri).into((ImageView) getActivity().findViewById(R.id.cameraImageView));

            Bitmap image = (Bitmap) data.getExtras().get("data");
            mImageView.setImageBitmap(image);
            String filename = "filename.jpg";
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            Log.d("image",bos.toString());
            ContentBody contentPart = new ByteArrayBody(bos.toByteArray(), filename);
            Log.d("contentPart",contentPart.toString());
            String url_string = "https://api.logmeal.es/v2/recognition/complete";
            MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
            reqEntity.addPart("picture", contentPart);
            Log.d("reqentity","hellooo");
            Log.d("reqentity",reqEntity.toString());
            Log.d("before","multipost");
            String response = multipost(url_string, reqEntity);
            Log.d("response",response);
/*
            String url_string = String.format("https://api.logmeal.es/v2/recognition/complete");

            String credentials = "Bearer f73890fbb9658a20272f33fc426601bc42533516";
           // HttpResponse<String> response = Unirest.post("http://35.192.98.23:8080/oauth/token")
             //       .header("content-type", "application/json")
               //     .header("Authorization", base64)

            URL api_url = null;

            try {
                api_url = new URL(url_string);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            // make request
            try {
                HttpURLConnection con = (HttpURLConnection) api_url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("Authorization",credentials);
                InputStream inputStream = con.getInputStream();

                JSONParser jsonParser = new JSONParser();
                String jsonString = jsonParser.parse(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).toString();

                JSONObject jsonObject = new JSONObject(jsonString);
                //returnFood.nutrients = parseSearchFoodQuery(jsonObject);
            } catch (IOException | ParseException | JSONException | org.json.simple.parser.ParseException ignored) {
            }

 */
        }
    }

    private static String multipost(String urlString, MultipartEntity reqEntity) {
        try {
            Log.d("multipost","entering");
            URL url = new URL(" http://api.foodai.org/v1/classify");
            Log.d("responseCode","hellooo");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Log.d("responseCode",Integer.toString(conn.getResponseCode()));

            //conn.setReadTimeout(10000);
            //conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);

            String credentials = "Bearer f73890fbb9658a20272f33fc426601bc42533516";
            conn.setRequestProperty("Authorization",credentials);
            conn.setRequestProperty("Connection", "Keep-Alive");
           // conn.addRequestProperty("Content-length", reqEntity.getContentLength()+"");
           // conn.addRequestProperty(reqEntity.getContentType().getName(), reqEntity.getContentType().getValue());
            Log.d("multipost","beforeOutputStream");
            Log.d("outputstream",conn.getOutputStream().toString());
            OutputStream os = conn.getOutputStream();
            Log.d("outputstream",os.toString());
            Log.d("multipost","beforereqEntity");
            reqEntity.writeTo(conn.getOutputStream());
            Log.d("multipost","afterRedEntity");
            os.close();
            Log.d("multipost","afterClose");
            conn.connect();
            Log.d("multipost","afterConnect");

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return readStream(conn.getInputStream());
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "multipart post error " + e + "(" + urlString + ")");
        }
        return null;
    }

    private static String multipost2(String urlString, MultipartEntity reqEntity) {
        try {
            Log.d("multipost2","entering");
            URL url = new URL("http://api.foodai.org/v1/classify");
            Log.d("responseCode","hellooo");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            Log.d("responseCode",Integer.toString(conn.getResponseCode()));

            //conn.setReadTimeout(10000);
            //conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");

            String credentials = "Bearer f73890fbb9658a20272f33fc426601bc42533516";
            conn.setRequestProperty("Authorization",credentials);
            conn.addRequestProperty("Connection", "Keep-Alive");
            // conn.addRequestProperty("Content-length", reqEntity.getContentLength()+"");
            // conn.addRequestProperty(reqEntity.getContentType().getName(), reqEntity.getContentType().getValue());
            Log.d("multipost","beforeOutputStream");
            Log.d("outputstream",conn.getOutputStream().toString());
            OutputStream os = conn.getOutputStream();
            Log.d("outputstream",os.toString());
            Log.d("multipost","beforereqEntity");
            reqEntity.writeTo(conn.getOutputStream());
            Log.d("multipost","afterRedEntity");
            os.close();
            Log.d("multipost","afterClose");
            conn.connect();
            Log.d("multipost","afterConnect");

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return readStream(conn.getInputStream());
            }

        } catch (Exception e) {
            Log.e(TAG, "multipart post error " + e + "(" + urlString + ")");
        }
        return null;
    }


    private static String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return builder.toString();
    }


}