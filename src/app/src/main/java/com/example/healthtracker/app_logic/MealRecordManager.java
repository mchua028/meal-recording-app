package com.example.healthtracker.app_logic;

import com.example.healthtracker.data_access.Food;
import com.example.healthtracker.data_access.MealRecord;

import java.util.Date;
import java.util.HashMap;

public class MealRecordManager {

	private static MealRecordManager singleton;

	private MealRecordManager() {
	}

	public MealRecordManager getSingleton() {
		return singleton;
	}

	/**
	 * 
	 * @param foodName
	 */
	public Food query(String foodName) {
		// TODO - implement com.example.healthtracker.app_logic.MealRecordManager.query
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param foods
	 */
	public void addMealRecord(Food[] foods) {
		// TODO - implement com.example.healthtracker.app_logic.MealRecordManager.addMealRecord
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param mealRecordId
	 */
	public void deleteMealRecord(double mealRecordId) {
		// TODO - implement com.example.healthtracker.app_logic.MealRecordManager.deleteMealRecord
		throw new UnsupportedOperationException();
	}

	/**
	 * TODO: feel free to replace image data type
	 * @param img
	 */
	public Food[] queryFoodInImage(Image img) {
		// TODO - implement com.example.healthtracker.app_logic.MealRecordManager.queryFoodInImage
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param foodInfo
	 */
	public void addFood(HashMap<String, String> foodInfo) {
		// TODO - implement com.example.healthtracker.app_logic.MealRecordManager.addFood
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 */
	public MealRecord[] query(Date startDate, Date endDate) {
		// TODO - implement com.example.healthtracker.app_logic.MealRecordManager.query
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param idInList
	 * @param newInfo
	 */
	public void editMealRecord(double idInList, Food[] newInfo) {
		// TODO - implement com.example.healthtracker.app_logic.MealRecordManager.editMealRecord
		throw new UnsupportedOperationException();
	}

	private MealRecordManager MealRecordManager() {
		// TODO - implement com.example.healthtracker.app_logic.MealRecordManager.com.example.healthtracker.app_logic.MealRecordManager
		throw new UnsupportedOperationException();
	}

	public int calculateCalorieQuotaRemainingToday() {
		// TODO - implement com.example.healthtracker.app_logic.MealRecordManager.calculateCalorieQuotaRemainingToday
		throw new UnsupportedOperationException();
	}

}