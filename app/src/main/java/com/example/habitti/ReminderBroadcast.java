package com.example.habitti;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

/**
 * <h1>Reminder broadcast</h1>
 * Use to schedule notifications
 * @author Jere Vuola
 */

public class ReminderBroadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "habits")
                .setSmallIcon(R.drawable.ic_baseline_sentiment_satisfied_alt_24)
                .setContentTitle("Habit reminder")
                .setContentText("This is a reminder to check your habits")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1, builder.build());

    }
}
