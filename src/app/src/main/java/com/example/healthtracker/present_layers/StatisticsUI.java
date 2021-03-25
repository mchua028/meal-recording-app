package com.example.healthtracker.present_layers;

import com.example.healthtracker.data_access_layer.MealRecord;

public class StatisticsUI extends UI {
	public StatisticsUI() {
	}

	@Override
	public void display() {

	}

	@Override
	public void displayErrorMessage() {

	}

	@Override
	public void printSucceesulMessage() {

	}

	/**
	 * System plots the weekly calorie historical intake data in bar chat with days, when the actual intake is too high or too low, highlighted.  
	 * @param mealRecords
	 * @param suggestedIntake
	 */
	public void plotWeeklyCalorieIntake(MealRecord[] mealRecords, int suggestedIntake) {
		// TODO - implement com.example.healthtracker.interfaces.StatisticsUI.plotWeeklyCalorieIntake
		throw new UnsupportedOperationException();
	}

}