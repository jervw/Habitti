package com.example.habitti;

import org.joda.time.DateTime;

public class Habbit {
    private String habbitName;
    private int imageId;
    DateTime dateCreated;
    private double overallScore = 0;
    private double scoreMultiplier = 1.0;


    public Habbit(String name, int imageId) {
        this.habbitName = name;
        this.dateCreated = new DateTime();
        this.imageId = imageId;

    }
    public String getHabbitName() {
        return this.habbitName;
    }

    public DateTime getDateCreated() {
        return this.dateCreated;
    }

    public int getImageId() {
        return this.imageId;
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
