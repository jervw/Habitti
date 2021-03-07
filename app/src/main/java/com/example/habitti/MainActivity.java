package com.example.habitti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    public static final String CHANNEL_1_ID = "channel";
    public static Fragment selectedFragment=null;
    public static String currentFragment = "Main";
    public static boolean firstCheckOfDay = true;
    private NotificationManagerCompat notificationManager;
    private boolean doubleBackToExitPressedOnce = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainFragment()).commit();

        createNotificationChannel();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.rewardsButton){
            Intent i = new Intent(getApplicationContext(), ShopPopUp.class);
            startActivity(i);
            return true;
        } else{
            return super.onOptionsItemSelected(item);
        }
    }

    private  BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment=null;
            switch (item.getItemId())
            {
                case R.id.habits:
                    selectedFragment = new MainFragment();
                    currentFragment = "Main";
                    break;
                case R.id.settings:
                    selectedFragment = new SettingsFragment();
                    currentFragment = "Settings";
                    break;
            }
            if (selectedFragment != null){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                return true;
            }
            return false;
        }
    };

    public void addHabitDialog(View view){
        if (currentFragment.equals("Settings")){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainFragment()).commit();

        }
        AddHabitDialog habitDialog = new AddHabitDialog();
        habitDialog.show(getSupportFragmentManager(), "new habit");
    }

    public void habitDetailsDialog(int index){
        HabitDetailsDialog detailsDialog = new HabitDetailsDialog(index);
        detailsDialog.show(getSupportFragmentManager(), "habit details");
    }

    private void createNotificationChannel() {
        notificationManager = NotificationManagerCompat.from(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.i("app", "channel create");
            NotificationChannel channel = new NotificationChannel(CHANNEL_1_ID, "Habit reminder", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Description");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    public void sendNotification(View view){

        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("notifications", false)){
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                    .setSmallIcon(R.drawable.ic_baseline_sentiment_satisfied_alt_24)
                    .setContentTitle("Habitti test notification")
                    .setContentText("Test message")
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setCategory(NotificationCompat.CATEGORY_REMINDER)
                    .build();

            notificationManager.notify(1, notification);
            Log.i("app","notification sent");
        }
    }

    // App will not close when back button is pressed once.
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = false;
    }
}