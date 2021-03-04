package com.example.habitti;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.preference.PreferenceManager;

import android.app.FragmentContainer;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {
    public static final String CHANNEL_1_ID = "channel";
    public static Fragment selectedFragment=null;
    public static String currentFragment = "Main";
    private NotificationManagerCompat notificationManager;
    //Used to make sure that dateCheck only runs once per app start
    public static boolean firstCheckOfDay = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainFragment()).commit();

        createNotificationChannel();
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

    public void openDialog(View view){
        if (currentFragment.equals("Settings")){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainFragment()).commit();

        }
        AddHabitDialog habitDialog = new AddHabitDialog();
        habitDialog.show(getSupportFragmentManager(), "dialog");
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
}