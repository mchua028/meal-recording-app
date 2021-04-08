package com.example.mealtracker;

public class BarGraphValues {
    private static int xValue;
    private static int yValue;

    public BarGraphValues() {

    }

    public BarGraphValues(int xValue, int yValue) {
        this.xValue = xValue;
        this.yValue = yValue;
    }

    public static int getxValue() {
        return xValue;
    }

    public static int getyValue() {
        return yValue;
    }
}
