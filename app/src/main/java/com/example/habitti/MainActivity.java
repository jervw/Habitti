package com.example.habitti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.Days;

import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

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



        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainFragment()).commit();




    }



    private  BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment=null;
            switch (item.getItemId())
            {
                case R.id.calendar:
                    selectedFragment = new CalendarFragment();
                    break;
                case R.id.habits:
                    selectedFragment = new MainFragment();
                    break;
                case R.id.settings:
                    selectedFragment = new SettingsFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
            return true;
        }
    };
}