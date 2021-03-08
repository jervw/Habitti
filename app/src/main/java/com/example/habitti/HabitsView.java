package com.example.habitti;

public class HabitsView {

    private int ivHabbitImageId;
    private String mHabbitName;
    private String mHabitType;
    private String mHabbitScores;
    private String mHabbitDaysStreak;
    private String dateCreated;
    private double scoreMultiplier;
    private boolean checkStatus;


    // create constructor to set the values for all the parameters of the each single view
    public HabitsView(int ivHabbitImageId, String mHabbitName, String mHabitType , String mHabbitScores, String mHabbitDaysStreak, String dateCreated, double scoreMultiplier, boolean checkStatus) {
        this.ivHabbitImageId = ivHabbitImageId;
        this.mHabbitName = mHabbitName;
        this.mHabitType = mHabitType;
        this.mHabbitScores = mHabbitScores;
        this.mHabbitDaysStreak = mHabbitDaysStreak;
        this.dateCreated = dateCreated;
        this.scoreMultiplier = scoreMultiplier;
        this.checkStatus = checkStatus;
    }

    // getter method for returning the ID of the ImageView
    public int getIvHabbitImageIdImageId() {
        return ivHabbitImageId;
    }

    // getter method for returning the level of the habit
    public String getmHabbitScores() {
        return mHabbitScores;
    }

    // getter method for returning the name of the habit
    public String getmHabbitName() {
        return mHabbitName;
    }

    // getter method for returning the type of the habit
    public String getmHabitType() {
        return mHabitType;
    }

    // getter method for returning the days of the habit
    public String getmHabbitDaysStreak() {
        return mHabbitDaysStreak;
    }

    public String getDateCreated() {
        return this.dateCreated;
    }

    public double getScoreMultiplier() {
        return this.scoreMultiplier;
    }

    public boolean getCheckStatus() {
        return this.checkStatus;
    }
}

