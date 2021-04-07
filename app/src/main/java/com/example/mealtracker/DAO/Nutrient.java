package com.example.mealtracker.DAO;

public class Nutrient {

    private double caloriePer100g;
    private double fat;
    private double cholesterol;
    private double sodium;
    private double potassium;
    private double sugar;
    private double dietaryFibre;
    private double protein;
    private double calcium;
    private double vitaminC;
    private double iron;
    private double magnesium;

    public Nutrient() {
    }

    public void setCaloriePer100g(double caloriePer100g) {
        this.caloriePer100g = caloriePer100g;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public void setCholesterol(double cholesterol) {
        this.cholesterol = cholesterol;
    }

    public void setSodium(double sodium) {
        this.sodium = sodium;
    }

    public void setPotassium(double potassium) {
        this.potassium = potassium;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public void setDietaryFibre(double dietaryFibre) {
        this.dietaryFibre = dietaryFibre;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public void setCalcium(double calcium) {
        this.calcium = calcium;
    }

    public void setVitaminC(double vitaminC) {
        this.vitaminC = vitaminC;
    }

    public void setIron(double iron) {
        this.iron = iron;
    }

    public void setMagnesium(double magnesium) {
        this.magnesium = magnesium;
    }

    public double getCaloriePer100g() {
        return caloriePer100g;
    }

    public double getFat() {
        return fat;
    }

    public double getCholesterol() {
        return cholesterol;
    }

    public double getSodium() {
        return sodium;
    }

    public double getPotassium() {
        return potassium;
    }

    public double getSugar() {
        return sugar;
    }

    public double getDietaryFibre() {
        return dietaryFibre;
    }

    public double getProtein() {
        return protein;
    }

    public double getCalcium() {
        return calcium;
    }

    public double getVitaminC() {
        return vitaminC;
    }

    public double getIron() {
        return iron;
    }

    public double getMagnesium() {
        return magnesium;
    }

    @Override
    public String toString() {
        return "Nutrient{" +
                "caloriePer100g=" + caloriePer100g +
                ", fat=" + fat +
                ", cholesterol=" + cholesterol +
                ", sodium=" + sodium +
                ", potassium=" + potassium +
                ", sugar=" + sugar +
                ", dietaryFibre=" + dietaryFibre +
                ", protein=" + protein +
                ", calcium=" + calcium +
                ", vitaminC=" + vitaminC +
                ", iron=" + iron +
                ", magnesium=" + magnesium +
                '}';
    }
}