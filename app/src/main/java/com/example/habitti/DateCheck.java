package com.example.habitti;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * <h1>DateCheck</h1>
 * Used to tell when day has passed to give points, multipliers and day streaks
 * @author Santeri Hytönen
 */
public class DateCheck {
    private static Context context;
    private static ReadableInstant comparedDate;
    private static int dayComparison;

    /**
     *
     * @param context get context for sharedPreference
     */
    //When called set Context to current activity where called, set compareDate to current date and
    // dayComparison to 0 (always 0 except in dev mode).
    public DateCheck(Context context) {
        this.context = context;
        this.comparedDate = new DateTime();
        this.dayComparison = 0;
    }

    /**
     * Create new DateTimeFormatter to set all the dates to compareable form
     * Get last time saved date from sharedPreferences
     * If the saved date is null, skip everything and save the current date to sharedPreferences
     * If not create new date-variable with current date and compare it to the saved date
     * If the difference is one day save current date to sharedPreferences, run checkHabitStatus() and get scores from all the Habits
     * If the difference is more than one day call resetAllHabitsStreak() to reset score multipliers and day streak from all Habits
     */
    public static void checkDate() {
        //When called create new DateTimeFormatter for converting strings to dates and vice versa
        DateTimeFormatter df = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
        //Use SharedPreferences to get date that was saved last time this method was called
        SharedPreferences sharedPref = context.getSharedPreferences("saved date", Context.MODE_PRIVATE);
        String savedDate = sharedPref.getString("saved date", null);
        //If saved value is not null it means there is saved data and dates can be compared
        if (savedDate != null) {
            //Convert saved String into milliseconds and then those -seconds into date
            long millis = df.parseMillis(savedDate);
            DateTime date = new DateTime(millis);
            //Get int value of days between saved date and current date
            int days = Days.daysBetween(date, comparedDate).getDays();
            //Check if that int value is bigger than 0
            if (days == 1) {
                Log.d("Tag", String.valueOf(days));
                //Save current date as a new saved date to SharedPreferences
                DateTime currentDate = new DateTime();
                String currentDateString = currentDate.toString(df);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putString("saved date", currentDateString);
                prefEditor.commit();
                checkHabitStatus();
                GlobalModel.getInstance().getUserScoresFromHabits();
            } else if (days > 1) {
                resetAllHabitStreak();
            }
            //If the saved value is null (In first time running the app) add current date to to SharedPreferences to not make it null anymore
        } else {
            DateTime currentDate = new DateTime();
            String currentDateString = currentDate.toString(df);
            SharedPreferences.Editor prefEditor = sharedPref.edit();
            prefEditor.putString("saved date", currentDateString);
            prefEditor.commit();
        }
    }

    /**
     * Used only in developer mode to instantly get one day streak
     */
    //Used only in devMode
    //Minus one from the dayComparison int to make the program think there is one day difference in current date and saved date
    //Then run checkDate to get points and set dayComparison back to normal
    public static void devAddDay() {
        Log.d("Tag", "devAddDay activated");
        dayComparison--;
        checkDate();
        dayComparison++;
    }

    /**
     *
     * @return the current login streak
     */
    public static int loginDayStreak() {
        int returner;
        //Create a new DateTimeFormatter and get last time saved date
        DateTimeFormatter df = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
        SharedPreferences sharedPref = context.getSharedPreferences("login date", Context.MODE_PRIVATE);
        returner = sharedPref.getInt("days streak", 0);
        String savedDate = sharedPref.getString("login date", null);
        //If saved date is not null compare it to current date
        if (savedDate != null) {
            long millis = df.parseMillis(savedDate);
            DateTime date = new DateTime(millis);
            int days = Days.daysBetween(date, comparedDate).getDays();
            //If difference is 0 days do nothing, if its 1 day add one to day streak and if its more than 1 reset the day streak
            if (days == 0) {
            }
            else if (days > dayComparison && days < 2) {
                returner++;
            }else if (days > 1) {
                returner = 0;
            }
            //Save the login streak and current date to sharedPreferences
            DateTime currentDate = new DateTime();
            String currentDateString = currentDate.toString(df);
            SharedPreferences.Editor prefEditor = sharedPref.edit();
            prefEditor.putString("login date", currentDateString);
            prefEditor.putInt("days streak", returner);
            prefEditor.commit();
            //If date in sharedPreferences is null, save current date and day streak 0
        } else if (savedDate == null) {
            DateTime currentDate = new DateTime();
            String currentDateString = currentDate.toString(df);
            SharedPreferences.Editor prefEditor = sharedPref.edit();
            prefEditor.putString("login date", currentDateString);
            prefEditor.putInt("days streak", returner);
            prefEditor.commit();
        }
        return returner;
    }

    /**
     * Get all the Habits from GlobalModel
     * Check every Habit's checked status, if its true give them points and set the status to false
     * If the status is false, reset score multiplier and day streak
     */
    public static void checkHabitStatus() {
        int index = 0;
        while (index < GlobalModel.getInstance().getHabitsList().size()) {
            if (GlobalModel.getInstance().getHabitItem(index).getCheckedStatus()) {
                GlobalModel.getInstance().getHabitItem(index).setDayStreak();
                GlobalModel.getInstance().getHabitItem(index).setCheckedStatus(false);
            } else {
                GlobalModel.getInstance().getHabitItem(index).resetScoreMultiplier();
                GlobalModel.getInstance().getHabitItem(index).setCheckedStatus(false);
            }
            index++;
        }
    }

    /**
     * Used to reset every Habits score multiplier and day streak if user has missed a day
     */
    public static void resetAllHabitStreak() {
        int index = 0;
        while (index < GlobalModel.getInstance().getHabitsList().size()) {
            GlobalModel.getInstance().resetMultiplier(GlobalModel.getInstance().getHabitItem(index));
            index++;
        }
    }


}


