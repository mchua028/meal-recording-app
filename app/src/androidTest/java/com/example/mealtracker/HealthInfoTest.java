package com.example.mealtracker;

import androidx.test.runner.AndroidJUnit4;

import com.example.mealtracker.AppLogic.HealthInfoManager;
import com.example.mealtracker.DAO.Database;
import com.example.mealtracker.DAO.Food;
import com.example.mealtracker.Exceptions.EmptyResultException;
import com.example.mealtracker.Exceptions.HttpsErrorException;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.InputStream;
import java.util.HashMap;


@RunWith(AndroidJUnit4.class)
public class HealthInfoTest {
//    @Test
//    public void createUserDir_whenRegister() {
//        Database.getSingleton().postNewAccount("lvOInQbGwdMcWFnfeag6CMP2flw2");
//    }

//    @Test
//    public void postHealthInfo_newRegistered() {
//        HashMap<String, String> info = new HashMap<>();
//        info.put("height", "165");
//        info.put("weight", "65");
//        info.put("age", "22");
//        info.put("goal weight", "2");
//        info.put("gender", "Female");
//        info.put("activity", "None");
//        Database.getSingleton().userId = "lvOInQbGwdMcWFnfeag6CMP2flw2";
//        HealthInfoManager.getSingleton().setHealthInfo(info);
//
//    }

    @Test
    public void getHealthInfo() {
        Database.getSingleton().userId = "lvOInQbGwdMcWFnfeag6CMP2flw2";
        
        double suggestion = HealthInfoManager.getSingleton().getSuggestedCalorie();
        System.out.print(suggestion);
    }
}
