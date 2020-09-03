package com.example.well_beingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

/**
 * @author Arjun Gill
 * @version 1.0
 * @since 4/9/20
 *
 * The 'SettingsActivity' class sets up the 'settings page' window for the user, in which the UI elements
 * are generated with the 'setContentView(R.layout.activity_settings)' method. This class also includes
 * the logic required to enable notifications, go to the about page and sign out of the application.
 */

public class SettingsActivity extends AppCompatActivity {

    // Declaring the notifications and about buttons:
    Button notificationButton;
    Button aboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings); // Sets the page's layout using activity_settings.xml file
        setTitle("Settings"); // Title of page in the application

        notificationButton = findViewById(R.id.notifications_button); // Initialising notifications button located in activity_settings.xml file
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // When button is clicked, notifications will be enabled

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 18); // Sends the notification at 18:00

                // Gets the logic for the notification from NotificationReceiver class:
                Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
                intent.setAction("");

                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),
                        100, intent, PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent); // Sends the notification daily

                Toast.makeText(getBaseContext(), "Notifications enabled", Toast.LENGTH_SHORT).show(); // Pop up message to show user that notifications are enabled
            }
        });

        aboutButton = findViewById(R.id.about_button); // Initialising about button located in activity_settings.xml file
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // When about button is clicked, user will be taken to the about page
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
            }
        });
    }

    public void logOut(View view) { // Method to log user out, log out button linked to 'logOut' method via xml file
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class)); // Takes user back to sign in page
        finish();
    }
}