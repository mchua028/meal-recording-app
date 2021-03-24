package com.example.healthtracker.data_access;

public class Food {

	private String name;
	private Nutrient nutrients;
	private double actualIntake = 0;
	private int suggestedIntake = 0;

	public Food(String name, Nutrient nutrients) {
		this.name = name;
		this.nutrients = nutrients;
	}

	public String getName() {
		return this.name;
	}

	public void setNutrients(Nutrient nutrients) {
		this.nutrients = nutrients;
	}

	public double getActualIntake() {
		return this.actualIntake;
	}

	public void setActualIntake(double actualIntake) {
		this.actualIntake = actualIntake;
	}

	public void setSuggestedIntake(int suggestedIntake) {
		this.suggestedIntake = suggestedIntake;
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
	 * @param img
	 */
	public static Food[] searchFoods(Image img) {
		// TODO - implement com.example.healthtracker.data_access.Food.searchFoods
		throw new UnsupportedOperationException();
	}

	public Nutrient getNutrients() {
		return this.nutrients;
	}

	/**
	 * 
	 * @param nutrientName
	 * @param top_k
	 */
	public static Food[] searchFoodsRichInNutrient(String nutrientName, int top_k) {
		// TODO - implement com.example.healthtracker.data_access.Food.searchFoodsRichInNutrient
		throw new UnsupportedOperationException();
	}

}