package com.example.mealtracker;

import androidx.test.runner.AndroidJUnit4;

import com.example.mealtracker.DAO.Food;
import com.example.mealtracker.Exceptions.EmptyResultException;
import com.example.mealtracker.Exceptions.HttpsErrorException;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.InputStream;


@RunWith(AndroidJUnit4.class)
public class FoodInstrumentedTest {
    @Test
    public void test_FoodRecognition() {
        InputStream is = getClass().getResourceAsStream("/sushi.jpg");
//        Bitmap bitmap = BitmapFactory.decodeStream(is);
//        File bitmap = getClass().getResource("/sushi.jpg");
        try {
            Food.searchFoodsFromImg(is);
        } catch (HttpsErrorException e) {
            e.printStackTrace();
        } catch (EmptyResultException e) {
            e.printStackTrace();
        }
    }
}
