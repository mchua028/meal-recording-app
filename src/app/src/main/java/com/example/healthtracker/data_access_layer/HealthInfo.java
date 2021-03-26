package com.example.healthtracker.data_access_layer;

import java.util.HashMap;

public class HealthInfo {

	private double height;
	private double weight;
	private int age;
	private double goalWeight;
	private Gender gender;
	private Activity dailyActivityLevel;
	private int suggestCalorieIntake;

	public double getHeight() {
		return this.height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return this.weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getGoalWeight() {
		return this.goalWeight;
	}

	public void setGoalWeight(double goalWeight) {
		this.goalWeight = goalWeight;
	}

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Activity getDailyActivityLevel() {
		return this.dailyActivityLevel;
	}

	public void setDailyActivityLevel(Activity dailyActivityLevel) {
		this.dailyActivityLevel = dailyActivityLevel;
	}

	public int getSuggestCalorieIntake() {
		return this.suggestCalorieIntake;
	}

	public void setSuggestCalorieIntake(int suggestCalorieIntake) {
		this.suggestCalorieIntake = suggestCalorieIntake;
	}

	public void addToServer() {
		// TODO - implement com.example.healthtracker.data_access_layer.HealthInfo.addToServer
		throw new UnsupportedOperationException();
	}

	public void updateToServer() {
		// TODO - implement com.example.healthtracker.data_access_layer.HealthInfo.updateToServer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param newInfo
	 */
	public void updateAttributes(HashMap<String, String> newInfo) {
		// TODO - implement com.example.healthtracker.data_access_layer.HealthInfo.updateAttributes
		throw new UnsupportedOperationException();
	}

}