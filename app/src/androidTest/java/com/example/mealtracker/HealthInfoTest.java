package com.example.mealtracker;

import androidx.test.runner.AndroidJUnit4;

import com.example.mealtracker.DAO.Database;
import com.example.mealtracker.DAO.Food;
import com.example.mealtracker.Exceptions.EmptyResultException;
import com.example.mealtracker.Exceptions.HttpsErrorException;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.InputStream;


@RunWith(AndroidJUnit4.class)
public class HealthInfoTest {
    @Test
    public void createUserDir_whenRegister() {
        Database.getSingleton().postNewAccount("lvOInQbGwdMcWFnfeag6CMP2flw2");
    }
}
