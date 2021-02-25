package com.example.habitti;

import java.util.Date;

public class HabbitsView {

    private int ivHabbitImageId;
    private String mHabbitName;
    private String mHabbitScores;
    private String mHabbitDaysStreak;
    private Date dateCreated;
    private double scoreMultiplier;


    // create constructor to set the values for all the parameters of the each single view
    public HabbitsView(int ivHabbitImageId, String mHabbitName, String mHabbitScores, String mHabbitDaysStreak, Date dateCreated, double scoreMultiplier) {
        this.ivHabbitImageId = ivHabbitImageId;
        this.mHabbitName = mHabbitName;
        this.mHabbitScores = mHabbitScores;
        this.mHabbitDaysStreak = mHabbitDaysStreak;
        this.dateCreated = dateCreated;
        this.scoreMultiplier = scoreMultiplier;
    }

    // getter method for returning the ID of the imageview
    public int getIvHabbitImageIdImageId() {
        return ivHabbitImageId;
    }

    // getter method for returning the level of the habbit
    public String getmHabbitScores() {
        return mHabbitScores;
    }

    // getter method for returning the name of the habbit
    public String getmHabbitName() {
        return mHabbitName;
    }

    // getter method for returning the days of the habbit
    public String getmHabbitDaysStreak() {
        return mHabbitDaysStreak;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    public double getScoreMultiplier() {
        return this.scoreMultiplier;
    }
}

