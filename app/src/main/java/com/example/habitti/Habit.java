package com.example.habitti;

public class Habit {
    private String habbitName;
    private int imageId;
    private String dateCreated;
    private String habitType;
    private int dayStreak;
    private double overallScore = 0;
    private double scoreMultiplier = 1.0;
    private boolean checkedToday;

    public Habit(String name, String habitType, int imageId) {
        this.habbitName = name;
        this.habitType = habitType;
        this.dateCreated = GlobalModel.getInstance().getOwnDateCreatedAsString();
        this.imageId = imageId;
        this.dayStreak = 0;
        this.checkedToday = false;

    }
    public String getHabbitName() {
        return this.habbitName;
    }

    public void setHabitName(String newHabitName){this.habbitName = newHabitName;}

    public String getHabitType() {
        return this.habitType;
    }

    public String getDateCreated() {
        return this.dateCreated;

    }

    public int getImageId() {
        return this.imageId;
    }

    public void resetScoreMultiplier() {
        this.scoreMultiplier = 1.0;
        this.dayStreak = 0;
    }

    public void addDailyScore() {
        this.overallScore = this.overallScore + (this.scoreMultiplier * 10);
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

    public void setCheckedStatus(boolean bool) {
        this.checkedToday = bool;
    }

    public boolean getCheckedStatus() {
        return this.checkedToday;
    }

    public void setDayStreak() {
        this.scoreMultiplier = this.scoreMultiplier + 0.005;
        this.dayStreak++;
    }

}
