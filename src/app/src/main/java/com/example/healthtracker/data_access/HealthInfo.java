package com.example.healthtracker.data_access;

import java.util.HashMap;

public class HealthInfo {

	private double height;
	private double weight;
	private int age;
	private double goalWeight;
	private Gender gender;
	private Activity dailyActivityLevel;
	private int suggestCalorieIntake;

	public HealthInfo(double height, double weight, int age, double goalWeight, Gender gender, Activity dailyActivityLevel, int suggestCalorieIntake) {
		this.height = height;
		this.weight = weight;
		this.age = age;
		this.goalWeight = goalWeight;
		this.gender = gender;
		this.dailyActivityLevel = dailyActivityLevel;
		this.suggestCalorieIntake = suggestCalorieIntake;
	}

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
		// TODO - implement com.example.healthtracker.data_access.HealthInfo.addToServer
		throw new UnsupportedOperationException();
	}

	public void updateToServer() {
		// TODO - implement com.example.healthtracker.data_access.HealthInfo.updateToServer
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param newInfo
	 */
	public void updateAttributes(HashMap<String, String> newInfo) {
		// TODO - implement com.example.healthtracker.data_access.HealthInfo.updateAttributes
		throw new UnsupportedOperationException();
	}

}