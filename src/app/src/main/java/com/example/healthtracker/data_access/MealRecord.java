package com.example.healthtracker.data_access;

public class MealRecord {

	private long idInServer;

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

}