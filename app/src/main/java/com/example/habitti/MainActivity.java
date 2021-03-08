package com.example.habitti;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * <h1>Main Activity</h1>
 * Main Activity is primarily used for always on-screen components such as bottom navigation bar and top bar
 * This class also implements methods for calling add habits dialog and notifications
 * @author Jere Vuola
 */


public class MainActivity extends AppCompatActivity {
    public static final String CHANNEL_1_ID = "channel";
    public static Fragment selectedFragment=null;
    public static String currentFragment = "Main";
    public static boolean firstCheckOfDay = true;
    private NotificationManagerCompat notificationManager;
    private boolean doubleBackToExitPressedOnce = false;

    /**
     * onCreate() initializes bottom navigation and notification channel
     * It also loads main fragment to user view
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainFragment()).commit();

        createNotificationChannel();
    }


    /**
     * Injects items from top_bar.xml to to top bar
     * @param menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Adds listener to top bar with following options:
     * 1. Rewards button to rewards activity.
     * 2. Help button to tutorial activity.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.rewardsButton:
                Intent i = new Intent(getApplicationContext(), RewardsActivity.class);
                startActivity(i);
                break;

            case R.id.howToUseButton:
                Intent y = new Intent(getApplicationContext(), TutorialActivity.class);
                startActivity(y);
                break;
        }
        return true;
    }


    /**
     * Adds listener to navigation bar with following options:
     * 1. Habit view / Main view
     * 2. Settings view
     * Changes current fragment according the choice
     * Starts fragment transition if selected fragment is valid
     */
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
    /**
     * Method to call add habit dialog
     * If user view is currently in settings fragment it will move to main fragment to avoid crashing
     * Displays dialog
     */
    public void addHabitDialog(View view){
        if (currentFragment.equals("Settings")){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainFragment()).commit();

        }
        AddHabitDialog habitDialog = new AddHabitDialog();
        habitDialog.show(getSupportFragmentManager(), "new habit");
    }

    /**
     * Creates notification channel to make notifications possible
     * Only creates channel if device is under Android 8.0 (Oreo)
     * Notification channels are not required under 8.0
     */
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

    /**
     * Sends notification with custom text parameters
     * @param title
     * @param message
     * Notification priority is default and category set to reminders.
     */
    public void sendNotification(String title, String message){

        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean("notifications", false)){
            Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                    .setSmallIcon(R.drawable.ic_baseline_sentiment_satisfied_alt_24)
                    .setContentTitle(title)
                    .setContentText(message)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setCategory(NotificationCompat.CATEGORY_REMINDER)
                    .build();

            notificationManager.notify(1, notification);
            Log.i("app","notification sent");
        }
    }

    /*** App will not close when back button is pressed once.*/
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = false;
    }
}