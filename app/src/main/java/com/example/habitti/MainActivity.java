package com.example.habitti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.Days;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create new Date from current date and subtract one day.
        DateTime sDate = new DateTime();
        sDate = sDate.minusDays(1);

        Habbit habit = new Habbit("tupakointi");
        Log.d("Tag", habit.getDateCreated().toString());

        int days = Days.daysBetween(sDate, habit.getDateCreated()).getDays();
        Log.d("Tag", "Days between " + days);

        if (Days.daysBetween(sDate, habit.getDateCreated()).getDays() > 0) {
            habit.addScoreMultiplier();
            habit.addDailyScore();
            Log.d("Tag", "Scores: " + habit.getOverallScore());
        }


    }
}