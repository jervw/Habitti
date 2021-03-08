package com.example.habitti;

import java.util.Date;

/**
 * <h1>HabitsView</h1>
 * Creates new objects based on Habits data
 * @author Santeri Hytönen
 */
public class HabitsView {
    private int ivHabbitImageId;
    private String mHabbitName;
    private String mHabitType;
    private String mHabbitScores;
    private String mHabbitDaysStreak;
    private String dateCreated;
    private double scoreMultiplier;
    private boolean checkStatus;

    /**
     *
     * @param ivHabbitImageId set image id
     * @param mHabbitName set name
     * @param mHabitType set type
     * @param mHabbitScores set scores
     * @param mHabbitDaysStreak set day streak
     * @param dateCreated set date created
     * @param scoreMultiplier set score multiplier
     * @param checkStatus set check status
     */
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

    /**
     *
     * @return image id
     */
    // getter method for returning the ID of the ImageView
    public int getIvHabbitImageIdImageId() {
        return ivHabbitImageId;
    }

    /**
     *
     * @return scores
     */
    // getter method for returning the level of the habit
    public String getmHabbitScores() {
        return mHabbitScores;
    }

    /**
     *
     * @return name
     */
    // getter method for returning the name of the habit
    public String getmHabbitName() {
        return mHabbitName;
    }

    /**
     *
     * @return type
     */
    // getter method for returning the type of the habit
    public String getmHabitType() {
        return mHabitType;
    }

    /**
     *
     * @return day streak
     */
    // getter method for returning the days of the habit
    public String getmHabbitDaysStreak() {
        return mHabbitDaysStreak;
    }

    /**
     *
     * @return date reated
     */
    public String getDateCreated() {
        return this.dateCreated;
    }

    /**
     *
     * @return score multiplier
     */
    public double getScoreMultiplier() {
        return this.scoreMultiplier;
    }

    /**
     *
     * @return check status
     */
    public boolean getCheckStatus() {
        return this.checkStatus;
    }
}

