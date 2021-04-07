package com.example.mealtracker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;
import com.example.mealtracker.DAO.MealRecord;
import com.example.mealtracker.Exceptions.EmptyResultException;
import com.example.mealtracker.Exceptions.HttpsErrorException;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;


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
