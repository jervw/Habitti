package com.example.habitti;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.ReadableInstant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class dateCheck {
    private static Context context;
    private static ReadableInstant comparedDate;
    private static int dayComparison;

    //When called set Context to current activity where called, set compareDate to current date and
    // dayComparison to 0 (always 0 except in dev mode).
    public dateCheck(Context context) {
        this.context = context;
        this.comparedDate = new DateTime();
        this.dayComparison = 0;
    }

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
            if (days > dayComparison) {
                Log.d("Tag", String.valueOf(days));
                //Save current date as a new saved date to SharedPreferences
                DateTime currentDate = new DateTime();
                String currentDateString = currentDate.toString(df);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putString("saved date", currentDateString);
                prefEditor.commit();
                Log.d("Tag", "CheckDate runned");
                //Run dailyPointsAndMultiplier as many times as the int's value
                while (days > dayComparison) {
                    GlobalModel.getInstance().dailyPointsAndMultipliers();
                    days--;
                }
                GlobalModel.getInstance().getUserScoresFromHabbits();
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


    //Used only in devMode
    //Minus one from the dayComparison int to make the program think there is one day difference in current date and saved date
    //Then run checkDate to get points and set dayComparison back to normal
    public static void devAddDay() {
        Log.d("Tag", "devAddDay activated");
        dayComparison--;
        checkDate();
        dayComparison++;
    }

    public static int loginDayStreak() {
        int returner;
        DateTimeFormatter df = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
        SharedPreferences sharedPref = context.getSharedPreferences("login date", Context.MODE_PRIVATE);
        returner = sharedPref.getInt("days streak", 0);
        String savedDate = sharedPref.getString("login date", null);
        if (savedDate != null) {
            //Convert saved String into milliseconds and then those -seconds into date
            long millis = df.parseMillis(savedDate);
            DateTime date = new DateTime(millis);
            int days = Days.daysBetween(date, comparedDate).getDays();
            if (days == 0) {
            }
            else if (days > dayComparison && days < 2) {
                returner++;
            }else if (days > 1) {
                returner = 0;
            }
            DateTime currentDate = new DateTime();
            String currentDateString = currentDate.toString(df);
            SharedPreferences.Editor prefEditor = sharedPref.edit();
            prefEditor.putString("login date", currentDateString);
            prefEditor.putInt("days streak", returner);
            prefEditor.commit();
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


}


