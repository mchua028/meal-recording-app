package com.example.mealtracker;

import androidx.annotation.Keep;

import java.io.Serializable;

@Keep
public class DataPoints implements Serializable {
    public static float xValue;
    public static float yValue;

    public DataPoints() {

    }

    public DataPoints(float xValue, float yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public float getxValue() {
        return xValue;
    }

    public float getyValue() {
        return yValue;
    }

    public void setxValue(float xValue) {
        this.xValue = xValue;
    }

    public void setyValue(float yValue) {
        this.yValue = yValue;
    }
}
