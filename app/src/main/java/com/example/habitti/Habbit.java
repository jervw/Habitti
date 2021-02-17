package com.example.habitti;

import org.joda.time.DateTime;

public class Habbit {
    private String habbitName;
    DateTime dateCreated;
    private double overallScore = 0;
    private double scoreMultiplier = 1.0;


    public Habbit(String name) {
        this.habbitName = name;
        this.dateCreated = new DateTime();

    }
    public DateTime getDateCreated() {
        return this.dateCreated;
    }

    public void addScoreMultiplier() {
        this.scoreMultiplier = this.scoreMultiplier + 0.1;
    }

    public void resetScoreMultiplier() {
        this.scoreMultiplier = 1.0;
    }

    public void addDailyScore() {
        this.overallScore = this.overallScore + (this.scoreMultiplier * 100);
    }

    public double getOverallScore() {
        return (double)Math.round(this.overallScore * 10) / 10;
    }
}
