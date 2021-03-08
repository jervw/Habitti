package com.example.habitti;

import android.util.Log;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * <h1>Habit</h1>
 * Used to create a new Habit-object
 * author Santeri Hytönen
 */
public class Habit {
    private String habitName;
    private int imageId;
    private String dateCreated;
    private String habitType;
    private int dayStreak;
    private double overallScore = 0;
    private double scoreMultiplier = 1.0;
    //checkedToday is use to get daily scores only once a day
    private boolean checkedToday;

    /**
     * Create new Habit-object
     * @param name              the name for the Habit
     * @param habitType         the good/bad type for the Habit
     * @param imageId           the id of the image for the Habit
     *
     *                          get date the Habit was created from GlobalModel.getInstance().getOwnDateCreatedAsString()
     *                          set dayStreak to 0
     *                          set checkedToday as false
     */
    public Habit(String name, String habitType, int imageId) {
        this.habitName = name;
        this.habitType = habitType;
        this.dateCreated = GlobalModel.getInstance().getOwnDateCreatedAsString();
        this.imageId = imageId;
        this.dayStreak = 0;
        this.checkedToday = false;

    }

    /**
     *
     * @return Habit name
     */
    public String getHabitName() {
        return this.habitName;
    }

    /**
     *
     * @param newHabitName set Habit's name for what is given
     */
    public void setHabitName(String newHabitName){this.habitName = newHabitName;}

    /**
     *
     * @return Habit's type
     */
    public String getHabitType() {
        return this.habitType;
    }

    /**
     *
     * @return date the Habit was created
     */
    public String getDateCreated() {
        return this.dateCreated;

    }

    /**
     *
     * @return id of the image
     */
    public int getImageId() {
        return this.imageId;
    }

    /**
     * Reset scoreMultiplier and dayStreak to default
     */
    public void resetScoreMultiplier() {
        this.scoreMultiplier = 1.0;
        this.dayStreak = 0;
    }

    /**
     * Adds daily scores according the scoreMultiplier
     */
    public void addDailyScore() {
        this.overallScore = this.overallScore + (this.scoreMultiplier * 10);
    }

    /**
     *
     * @return the scoreMultiplier
     */
    public double getScoreMultiplier() {
        return (double)Math.round(this.scoreMultiplier * 10) / 10;
    }

    /**
     *
     * @return Habit scores rounded to one decimal
     */
    public double getOverallScore() {
        return (double)Math.round(this.overallScore * 10) / 10;
    }

    /**
     *
     * @return current day streak
     */
    public int getDayStreak() {
        return this.dayStreak;
    }

    /**
     *
     * @param bool set the checkedToday value
     */
    public void setCheckedStatus(boolean bool) {
        this.checkedToday = bool;
    }

    /**
     *
     * @return checkedToday to see if this Habit is scored today or not
     */
    public boolean getCheckedStatus() {
        return this.checkedToday;
    }

    /**
     * Increases score multiplier by 5 percent
     * adds one to day streak
     */
    public void setDayStreak() {
        this.scoreMultiplier = this.scoreMultiplier + 0.005;
        this.dayStreak++;
    }

}
