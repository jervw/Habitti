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
    private String pattern = "dd/MM/yyyy";

    public dateCheck(Context context) {
        this.context = context;
        this.comparedDate = new DateTime();
    }

    public static void checkDate() {
        DateTimeFormatter df = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
        SharedPreferences sharedPref = context.getSharedPreferences("saved date", Context.MODE_PRIVATE);
        String savedDate = sharedPref.getString("saved date", null);
        if (savedDate != null) {
            long millis = df.parseMillis(savedDate);
            DateTime date = new DateTime(millis);
            int days = Days.daysBetween(date, comparedDate).getDays();
            if (days > 0) {
                Log.d("Tag", String.valueOf(days));
                DateTime currentDate = new DateTime();
                String currentDateString = currentDate.toString(df);
                SharedPreferences.Editor prefEditor = sharedPref.edit();
                prefEditor.putString("saved date", currentDateString);
                prefEditor.commit();
                Log.d("Tag", "CheckDate runned");
                while (days > 0) {
                    GlobalModel.getInstance().dailyPointsAndMultipliers();
                    days--;
                }
            }
        }
    }
}


