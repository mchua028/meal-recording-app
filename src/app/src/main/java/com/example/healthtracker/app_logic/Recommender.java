package com.example.healthtracker.app_logic;

import com.example.healthtracker.data_access.Food;
import com.example.healthtracker.data_access.Nutrient;

import java.util.HashMap;


public class Recommender {

	private Nutrient suggestedNutrient;
	private Nutrient actualNutrient;
	private String[] lackedNutrients;

	public Recommender(Nutrient suggestedNutrient, Nutrient actualNutrient) {
		this.suggestedNutrient = suggestedNutrient;
		this.actualNutrient = actualNutrient;
	}

	public Nutrient getSuggestedNutrient() {
		return this.suggestedNutrient;
	}

	public void setSuggestedNutrient(Nutrient suggestedNutrient) {
		this.suggestedNutrient = suggestedNutrient;
	}

	public Nutrient getActualNutrient() {
		return this.actualNutrient;
	}

	public void setActualNutrient(Nutrient actualNutrient) {
		this.actualNutrient = actualNutrient;
	}

	public String[] getLackedNutrients() {
		return this.lackedNutrients;
	}

	public Nutrient getSuggestedNutrients() {
		// TODO - implement com.example.healthtracker.app_logic.Recommender.getSuggestedNutrients
		throw new UnsupportedOperationException();
	}

	/**
	 * boolean value: 0 means lower than suggested and 1 means higher than suggested
	 */
	public HashMap<String, String> getAbnormalNutrient() {
		// TODO - implement com.example.healthtracker.app_logic.Recommender.getAbnormalNutrient
		throw new UnsupportedOperationException();
	}

	public Food[] recommendFoods() {
		// TODO - implement com.example.healthtracker.app_logic.Recommender.recommendFoods
		throw new UnsupportedOperationException();
	}

	private Nutrient calculateActualIntake() {
		// TODO - implement com.example.healthtracker.app_logic.Recommender.calculateActualIntake
		throw new UnsupportedOperationException();
	}

}