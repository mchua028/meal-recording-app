/*
  Data access object related to Food
  @Author: Tang Yuting
 */


package com.example.mealtracker;


import com.example.mealtracker.Exceptions.EmptyInputException;
import com.example.mealtracker.Exceptions.EmptyResultException;

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


public class Food {
    private String name;
    private Nutrient nutrients;
    private double actualIntake = 0;
    private int suggestedIntake = 0;

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
     * FIXME:
     *  - the name of the food
     */
    public static Food searchFood(String name) throws EmptyInputException, EmptyResultException {
        Food returnFood = new Food();
        returnFood.name = name;
        if (name.isEmpty()) throw new EmptyInputException();
        name = name.replace(" ", "%"); // Due to syntax error in url
        String url_string = String.format("https://api.nal.usda.gov/fdc/v1/foods/search?api_key=DEMO_KEY&query=%s", name);
        URL api_url = null;

        try {
            api_url = new URL(url_string);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        // make request
        try {
            HttpURLConnection con = (HttpURLConnection) api_url.openConnection();
            con.setRequestMethod("GET");
            InputStream inputStream = con.getInputStream();

            JSONParser jsonParser = new JSONParser();
            String jsonString = jsonParser.parse(new InputStreamReader(inputStream, StandardCharsets.UTF_8)).toString();

            JSONObject jsonObject = new JSONObject(jsonString);
            returnFood.nutrients = parseSearchFoodQuery(jsonObject);
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

    /**
     *
     * @param name
     */
    private static Food[] searchFromAPI(String name) {
        // TODO - implement com.example.healthtracker.data_access_layer.Food.searchFromAPI
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param name
     */
    private Food[] searchFromCustomDatabase(String name) {
        // TODO - implement com.example.healthtracker.data_access_layer.Food.searchFromCustomDatabase
        throw new UnsupportedOperationException();
    }

    public void addFoodToServer() {
        // TODO - implement com.example.healthtracker.data_access_layer.Food.addFoodToServer
        throw new UnsupportedOperationException();
    }


    /*public static Food[] searchFoods(Image img) {
        // TODO - implement com.example.healthtracker.data_access_layer.Food.searchFoods
        throw new UnsupportedOperationException();
    }
*/
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