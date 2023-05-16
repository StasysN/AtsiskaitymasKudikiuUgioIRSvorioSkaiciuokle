package com.example.happybaby;

public class Baby {

    private int id;
    private double babyAge;
    private double babyHeight;
    private double babyWeight;
    private String babyHeightAverage;
    private String babyWeightAverage;

    public Baby(double babyAge, double babyHeight, double babyWeight, String babyHeightAverage, String babyWeightAverage) {
        this.babyAge = babyAge;
        this.babyHeight = babyHeight;
        this.babyWeight = babyWeight;
        this.babyHeightAverage = babyHeightAverage;
        this.babyWeightAverage = babyWeightAverage;
    }

    public Baby(int id, double babyAge, double babyHeight, double babyWeight, String babyHeightAverage, String babyWeightAverage) {
        this.id = id;
        this.babyAge = babyAge;
        this.babyHeight = babyHeight;
        this.babyWeight = babyWeight;
        this.babyHeightAverage = babyHeightAverage;
        this.babyWeightAverage = babyWeightAverage;
    }

    public Baby(double babyAge, double babyHeight, double babyWeight) {
        this.babyAge = babyAge;
        this.babyHeight = babyHeight;
        this.babyWeight = babyWeight;
    }

    public Baby() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBabyAge() {
        return babyAge;
    }

    public void setBabyAge(double babyAge) {
        this.babyAge = babyAge;
    }

    public double getBabyHeight() {
        return babyHeight;
    }

    public void setBabyHeight(double babyHeight) {
        this.babyHeight = babyHeight;
    }

    public double getBabyWeight() {
        return babyWeight;
    }

    public void setBabyWeight(double babyWeight) {
        this.babyWeight = babyWeight;
    }

    public String getBabyHeightAverage() {
        return babyHeightAverage;
    }

    public void setBabyHeightAverage(String babyHeightAverage) {
        this.babyHeightAverage = babyHeightAverage;
    }

    public String getBabyWeightAverage() {
        return babyWeightAverage;
    }

    public void setBabyWeightAverage(String babyWeightAverage) {
        this.babyWeightAverage = babyWeightAverage;
    }

    @Override
    public String toString() {
        return "Baby{" +
                "id=" + id +
                ", babyName='" + babyAge + '\'' +
                ", babyHeight=" + babyHeight +
                ", babyWeight=" + babyWeight +
                ", babyHeightAverage='" + babyHeightAverage + '\'' +
                ", babyWeightAverage='" + babyWeightAverage + '\'' +
                '}';
    }
}
