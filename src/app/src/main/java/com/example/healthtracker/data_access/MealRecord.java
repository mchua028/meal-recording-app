package com.example.healthtracker.data_access;

import java.util.Date;

public class MealRecord {

	private long idInServer;
	private Food[] foods;

	public MealRecord(Food[] foods) {
		this.foods = foods;
	}

	public long getIdInServer() {
		return this.idInServer;
	}



	public void addToServer() {
		// TODO - implement com.example.healthtracker.data_access.MealRecord.addToServer
		throw new UnsupportedOperationException();
	}

	public void deleteFromServer() {
		// TODO - implement com.example.healthtracker.data_access.MealRecord.deleteFromServer
		throw new UnsupportedOperationException();
	}

	public void updateToServer() {
		// TODO - implement com.example.healthtracker.data_access.MealRecord.updateToServer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param startDate
	 * @param endDate
	 */
	public static MealRecord[] queryByDate(Date startDate, Date endDate) {
		// TODO - implement com.example.healthtracker.data_access.MealRecord.queryByDate
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param newFoods
	 */
	public void updateFood(Food[] newFoods) {
		// TODO - implement com.example.healthtracker.data_access.MealRecord.updateFood
		throw new UnsupportedOperationException();
	}

	public Nutrient getNutrient() {
		// TODO - implement com.example.healthtracker.data_access.MealRecord.getNutrient
		throw new UnsupportedOperationException();
	}

}