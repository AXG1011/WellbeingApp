package com.example.well_beingapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

/**
 * @author Arjun Gill
 * @version 1.0
 * @since 4/9/20
 *
 * The 'NotificationReceiver' class includes the logic and creation of a notification that is sent to
 * user devices if they enable notifications in their settings. The notification itself is a reminder
 * for the daily mood quiz, and clikcing on the notification will take them directly to this quiz.
 */

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService
                (Context.NOTIFICATION_SERVICE);

        Intent repeating_intent = new Intent(context, MoodQuizActivity.class); // Takes the user to the mood quiz

        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                100, repeating_intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Building the notification:
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentTitle("Mood Quiz Reminder!") // Title of the notification
                .setContentText("Click to complete your daily mood quiz") // Text of the notification
                .setAutoCancel(true); // If the user does not touch the notificatin, it will dissapear automatically

        notificationManager.notify(100, builder.build());
    }
    
}