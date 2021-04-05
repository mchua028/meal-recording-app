package com.example.mealtracker;


import com.example.mealtracker.Exceptions.EmptyInputException;
import com.example.mealtracker.Exceptions.EmptyResultException;

import org.junit.Test;
import org.junit.runner.RunWith;


public class FoodUnitTest {
    @Test
    public void searchMethod_SuccessfulReturn() {
        try {
            System.out.print(Food.searchFood("Banana"));
        } catch (EmptyInputException | EmptyResultException e) {

        }
    }

    @Test
    //Fixme
    public void searchMethod_EmptyResult_ThrowEmptyResu() {
//        assertThrows(EmptyResultException.class,
//                ()->{Food.searchFood("shit");});
    }

    @Test
    public void searchMethod_largeQuery_noOverflow() {
        try {
            System.out.print(Food.searchFood("Steak"));
        } catch (EmptyInputException | EmptyResultException e) {

        }
    }
}