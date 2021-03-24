package com.example.healthtracker.data_access;


/**
 * Note: no set because these attributes are obtained from database and read-only.
 */
public class Nutrient {
	private int calcium;
	private int caloriePer100g;
	private int cholesterol;
	private int cobalamin;
	private int dietaryFibre;
	private int fat;
	private int iron;
	private int magnesium;
	private int potassium;
	private int sodium;
	private int sugar;
	private int vitaminC;

	public Nutrient(int calcium, int caloriePer100g, int cholesterol, int cobalamin, int dietaryFibre, int fat, int iron, int magnesium, int potassium, int sodium, int sugar, int vitaminC) {
		this.calcium = calcium;
		this.caloriePer100g = caloriePer100g;
		this.cholesterol = cholesterol;
		this.cobalamin = cobalamin;
		this.dietaryFibre = dietaryFibre;
		this.fat = fat;
		this.iron = iron;
		this.magnesium = magnesium;
		this.potassium = potassium;
		this.sodium = sodium;
		this.sugar = sugar;
		this.vitaminC = vitaminC;
	}

	public int getCalcium() {
		return this.calcium;
	}

	public int getCaloriePer100g() {
		return this.caloriePer100g;
	}

	public int getCholesterol() {
		return this.cholesterol;
	}

	public int getCobalamin() {
		return this.cobalamin;
	}

	public int getDietaryFibre() {
		return this.dietaryFibre;
	}

	public int getFat() {
		return this.fat;
	}

	public int getIron() {
		return this.iron;
	}

	public int getMagnesium() {
		return this.magnesium;
	}

	public int getPotassium() {
		return this.potassium;
	}

	public int getSodium() {
		return this.sodium;
	}

	public int getSugar() {
		return this.sugar;
	}

	public int getVitaminC() {
		return this.vitaminC;
	}

}