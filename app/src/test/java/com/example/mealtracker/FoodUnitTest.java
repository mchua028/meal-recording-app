package com.example.mealtracker;


import com.example.mealtracker.Exceptions.EmptyInputException;
import com.example.mealtracker.Exceptions.EmptyResultException;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertThrows;


public class FoodUnitTest {
    @Test
    public void searchMethod_SuccessfulReturn() {
        try {
            System.out.print(Food.searchFood("Banana"));
        } catch (EmptyInputException | EmptyResultException e) {

        }
    }

    @Test
    public void searchMethod_EmptyResult_ThrowEmptyResu() {
        assertThrows(EmptyResultException.class,
                ()->{Food.searchFood("shit");});
    }
}