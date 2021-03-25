package com.example.healthtracker.data_access_layer;

public class Nutrient {

	private float caloriePer100g;
	private float fat;
	private float cholesterol;
	private float sodium;
	private float potassium;
	private float sugar;
	private double dietaryFibre;
	private float protein;
	private float calcium;
	private float vitaminC;
	private float iron;
	private float cobalamin;
	private float magnesium;

	public float getCaloriePer100g() {
		return this.caloriePer100g;
	}

	public void setCaloriePer100g(float caloriePer100g) {
		this.caloriePer100g = caloriePer100g;
	}

	public float getFat() {
		return this.fat;
	}

	public void setFat(float fat) {
		this.fat = fat;
	}

	public float getCholesterol() {
		return this.cholesterol;
	}

	public void setCholesterol(float cholesterol) {
		this.cholesterol = cholesterol;
	}

	public float getSodium() {
		return this.sodium;
	}

	public void setSodium(float sodium) {
		this.sodium = sodium;
	}

	public float getPotassium() {
		return this.potassium;
	}

	public void setPotassium(float potassium) {
		this.potassium = potassium;
	}

	public float getSugar() {
		return this.sugar;
	}

	public void setSugar(float sugar) {
		this.sugar = sugar;
	}

	public double getDietaryFibre() {
		return this.dietaryFibre;
	}

	public void setDietaryFibre(double dietaryFibre) {
		this.dietaryFibre = dietaryFibre;
	}

	public float getProtein() {
		return this.protein;
	}

	public void setProtein(float protein) {
		this.protein = protein;
	}

	public float getCalcium() {
		return this.calcium;
	}

	public void setCalcium(float calcium) {
		this.calcium = calcium;
	}

	public float getVitaminC() {
		return this.vitaminC;
	}

	public void setVitaminC(float vitaminC) {
		this.vitaminC = vitaminC;
	}

	public float getIron() {
		return this.iron;
	}

	public void setIron(float iron) {
		this.iron = iron;
	}

	public float getCobalamin() {
		return this.cobalamin;
	}

	public void setCobalamin(float cobalamin) {
		this.cobalamin = cobalamin;
	}

	public float getMagnesium() {
		return this.magnesium;
	}

	public void setMagnesium(float magnesium) {
		this.magnesium = magnesium;
	}

}