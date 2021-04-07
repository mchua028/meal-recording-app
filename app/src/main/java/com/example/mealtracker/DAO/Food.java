/*
  Data access object related to Food
  @Author: Tang Yuting
 */


package com.example.mealtracker.DAO;

import android.graphics.Bitmap;

import android.media.Image;
import android.util.Log;

import com.example.mealtracker.Exceptions.EmptyInputException;
import com.example.mealtracker.Exceptions.EmptyResultException;
import com.example.mealtracker.Exceptions.HttpsErrorException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


/**
 * DAO class for food
 */
public class Food {
    private String name;
    private Nutrient nutrients;
    private double actualIntake = 0;
    private int suggestedIntake = 0;

    private final static String FOOD_RECOGNITION_URL = "https://api.logmeal.es/v2/recognition/complete";
    private final static String USER_TOKEN = "0aabb46949de93de6ef729331af1d0a36fb202f0";

    public Food() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNutrients(Nutrient nutrients) {
        this.nutrients = nutrients;
    }

    public double getActualIntake() {
        return this.actualIntake;
    }

    public void setActualIntake(double actualIntake) {
        this.actualIntake = actualIntake;
    }

    public void setSuggestedIntake(int suggestedIntake) {
        this.suggestedIntake = suggestedIntake;
    }
    public double getSuggestedIntake() {
       return suggestedIntake;
    }


    /**
     * Query and parse food information via API (https://fdc.nal.usda.gov/api-guide.html#bkmk-6)
     * @param name, String of the food name.
     * @return Food, the searched food
     * @throws EmptyInputException when name is empty string
     * @throws EmptyResultException when no result is returned by query
     * FIXME:
     *  - the name of the food
     */
    public static Food searchFood(String name) throws EmptyInputException, EmptyResultException {
        Log.d("into","food.searchfood");
        Food returnFood = new Food();
        Log.d("before","setname");
        returnFood.name = name;
        if (name.isEmpty()) throw new EmptyInputException();
        name = name.replace(" ", "%"); // Due to syntax error in url
        Log.d("after set name",name);

        String url_string = String.format("https://api.nal.usda.gov/fdc/v1/foods/search?api_key=DEMO_KEY&query=%s", name);
        Log.d("url_string",url_string);
        URL api_url = null;

        try {
            api_url = new URL(url_string);
            Log.d("makeurl","done");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // make request
        try {
            Log.d("before","connection");
            HttpURLConnection con = (HttpURLConnection) api_url.openConnection();
            Log.d("after","connection");

            con.setRequestMethod("GET");
            Log.d("set","connectionGET");

            InputStream inputStream = con.getInputStream();
            Log.d("afeer","inputStream");

            JSONParser jsonParser = new JSONParser();
            String jsonString = jsonParser.parse(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).toString();
            Log.d("after","jsonPaarser");

            JSONObject jsonObject = new JSONObject(jsonString);
            Log.d("before","actualquery");

            returnFood.nutrients = parseSearchFoodQuery(jsonObject);
            Log.d("after","actualquery");

        } catch (IOException | ParseException | JSONException ignored) {
        }
        return returnFood;
    }

    /**
     * Parses json from food api on searching with keyword/food name.
     * @param jsonObject JSONObject to parse
     * @return Nutrient where the result is to stored
     * @throws JSONException when parsing error occurs
     * @throws EmptyResultException when the food cannot be searched
     */
    private static Nutrient parseSearchFoodQuery(JSONObject jsonObject) throws JSONException, EmptyResultException {
        Nutrient nutrient = new Nutrient();
        JSONArray foods = jsonObject.getJSONArray("foods");
        JSONObject foodJson;
        try {
            foodJson = foods.getJSONObject(1);
        } catch (JSONException e) {
            throw new EmptyResultException();
        }
        JSONArray nutrients = foodJson.getJSONArray("foodNutrients");
        for (int i = 0; i < nutrients.length(); i++) {
            JSONObject n = nutrients.getJSONObject(i);
            String nutrientName = n.getString("nutrientName");
            switch (nutrientName) {
                case "Protein":
                    nutrient.setProtein(n.getDouble("value"));
                    break;
                case "Total lipid (fat)":
                    nutrient.setFat(n.getDouble("value"));
                    break;
                case "Energy":
                    nutrient.setCaloriePer100g(n.getDouble("value"));
                    break;
                case "Sugars, total including NLEA":
                    nutrient.setSugar(n.getDouble("value"));
                    break;
                case "Sodium, Na":
                    nutrient.setSodium(n.getDouble("value"));
                    break;
                case "Vitamin C, total ascorbic acid":
                    nutrient.setVitaminC(n.getDouble("value"));
                    break;
            }
        }
        return nutrient;
    }


//    /**
//     *
//     * @param name
//     */
//    private Food[] searchFromCustomDatabase(String name) {
//    }
//
//    public void addFoodToServer() {
//        // TODO - implement com.example.healthtracker.data_access_layer.Food.addFoodToServer
//        throw new UnsupportedOperationException();
//    }


    /**
     * Recognizes the food in the img
     * @param img InputStream, the image to recognize
     * @return Food
     * @throws HttpsErrorException when the result cannot be retrived from API
     * @throws EmptyResultException when no food is found
     */
    public static Food searchFoodsFromImg(InputStream img) throws HttpsErrorException, EmptyResultException {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(FOOD_RECOGNITION_URL);
        httppost.setHeader("Authorization", "Bearer " + USER_TOKEN);  // add user token
        Food result = null;
        try {
            // upload the image file
            Log.d("before","upload image");
            MultipartEntity reqEntity = new MultipartEntity();
            reqEntity.addPart("image", new InputStreamBody(img, "data_recognition.jpeg"));
            Log.d("middle","upload image");

            httppost.setEntity(reqEntity);
            Log.d("after","upload image");

            // add the line below, otherwise the error is happening
            httpclient.getConnectionManager().getSchemeRegistry().register( new Scheme("https", SSLSocketFactory.getSocketFactory(), 443) );
            Log.d("after","socket");

            int statusCode = httpclient.execute(httppost).getStatusLine().getStatusCode();
            Log.d("statusCode",Integer.toString(statusCode));
            //HttpResponse responseBody = httpclient.execute(httppost);
            Log.d("after","response body");

            //int statusCode = responseBody.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new HttpsErrorException();
            }
            // parse the result
            Log.d("going to get result","result");

            String json_string = EntityUtils.toString(httpclient.execute(httppost).getEntity());
            JSONObject jsonObject = new JSONObject(json_string);
            JSONArray results = jsonObject.getJSONArray("recognition_results");
            JSONObject food = results.getJSONObject(0);  // get the 1st confident result
            String foodName = food.getString("name");
            result = Food.searchFood(foodName);
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (EmptyInputException e) {
            e.printStackTrace();
        } finally {
            httpclient.getConnectionManager().shutdown();
        }
        if (result == null) {
            throw new EmptyResultException();
        }
        return result;
    }

    public Nutrient getNutrients() {
        return this.nutrients;
    }

    /**
     *
     * @param nutrientName
     * @param top_k
     */
    public static ArrayList<String> searchFoodsRichInNutrient(String nutrientName, int top_k) {
        // TODO - implement com.example.healthtracker.data_access_layer.Food.searchFoodsRichInNutrient
        throw new UnsupportedOperationException();
    }

    /**
     * @return String displaying info of food
     */
    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", nutrients=" + nutrients.toString() +
                ", actualIntake=" + actualIntake +
                ", suggestedIntake=" + suggestedIntake +
                '}';
    }
}