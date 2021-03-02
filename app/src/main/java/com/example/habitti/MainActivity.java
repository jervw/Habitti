package com.example.habitti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.app.Notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    public static final String CHANNEL_1_ID = "channel";

    private NotificationManagerCompat notificationManager;
    Button addNewHabbitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainFragment()).commit();

        createNotificationChannel();
        notificationManager = NotificationManagerCompat.from(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private  BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment=null;
            switch (item.getItemId())
            {
                case R.id.habits:
                    selectedFragment = new MainFragment();
                    break;
                case R.id.calendar:
                    openDialog();
                    break;
                case R.id.settings:
                    selectedFragment = new SettingsFragment();
                    break;
            }
            if (selectedFragment != null){
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                return true;
            }
            return false;
        }
    };

    public void openDialog(){
        AddHabitDialog habitDialog = new AddHabitDialog();
        habitDialog.show(getSupportFragmentManager(), "test dialog");
    }
    private void createNotificationChannel() {
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


    public void onClickAddHabbit (View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.new_habit_dialog, null);
        builder.setTitle("Add new habit");
        Spinner habbitSpinner = (Spinner) mView.findViewById(R.id.spinnerHabbits2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.habbits_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        habbitSpinner.setAdapter(adapter);
        /*Intent intent = new Intent(this, AddNewHabbits.class);
        startActivity(intent);*/
        builder.setView(mView);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}