package com.example.mealtracker;

import androidx.test.runner.AndroidJUnit4;

import com.example.mealtracker.Exceptions.EmptyInputException;
import com.example.mealtracker.Exceptions.EmptyResultException;
import com.example.mealtracker.Exceptions.RecordNotInServerException;
import com.example.mealtracker.Exceptions.ValueCannotBeNonPositiveException;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * TODO:
 *  - change account
 */
@RunWith(AndroidJUnit4.class)
public class HealthInfoInstrumentedTest {
    private Database d = Database.getSingleton();


    @Test
    public HealthInfo post_HealthInfo() {
        HealthInfo healthInfo = null;
        d.postHealthInfo(healthInfo);
        return healthInfo;
   }
}
