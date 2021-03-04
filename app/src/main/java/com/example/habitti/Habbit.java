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

public class Habbit {
    private String habbitName;
    private int imageId;
    private String dateCreated;
    private int dayStreak;
    private double overallScore = 0;
    private double scoreMultiplier = 1.0;

    public Habbit(String name, int imageId) {
        this.habbitName = name;
        this.dateCreated = GlobalModel.getInstance().getOwnDateCreatedAsString();
        this.imageId = imageId;
        this.dayStreak = 0;

    }
    public String getHabbitName() {
        return this.habbitName;
    }

    public String getDateCreated() {
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
        this.dayStreak = 0;
    }

    public void addDailyScore() {
        this.overallScore = this.overallScore + (this.scoreMultiplier * 100);
        this.dayStreak++;
        Log.d("Tag", String.valueOf(this.overallScore));
    }

    public double getScoreMultiplier() {
        return (double)Math.round(this.scoreMultiplier * 10) / 10;
    }

    public double getOverallScore() {
        return (double)Math.round(this.overallScore * 10) / 10;
    }

    public int getDayStreak() {
        return this.dayStreak;
    }
}
