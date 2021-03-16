package com.example.healthtracker.data_access;

import java.util.HashMap;

public class Food {

	private String name;
	private HashMap<String, String> nutrients;

	public String getName() {
		return this.name;
	}

	public void setNutrients(HashMap<String, String> nutrients) {
		this.nutrients = nutrients;
	}

	/**
	 * 
	 * @param name
	 */
	public static Food searchFood(String name) {
		// TODO - implement com.example.healthtracker.data_access.Food.searchFood
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 */
	private static Food[] searchFromAPI(String name) {
		// TODO - implement com.example.healthtracker.data_access.Food.searchFromAPI
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param name
	 */
	private Food[] searchFromCustomDatabase(String name) {
		// TODO - implement com.example.healthtracker.data_access.Food.searchFromCustomDatabase
		throw new UnsupportedOperationException();
	}

	public void addFoodToServer() {
		// TODO - implement com.example.healthtracker.data_access.Food.addFoodToServer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param img image file supported by Android
	 */
	public static Food[] searchFoods(Image img) {
		// TODO - implement com.example.healthtracker.data_access.Food.searchFoods
		throw new UnsupportedOperationException();
	}

}