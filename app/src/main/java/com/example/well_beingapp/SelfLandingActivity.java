package com.example.well_beingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @author Arjun Gill
 * @version 1.0
 * @since 4/9/20
 *
 * The 'SelfLandingActivity' class sets up the 'self landing page' window for the user, in which the UI elements
 * are generated with the 'setContentView(R.layout.activity_self_landing)' method. This class provides logic
 * for two buttons that take the user to new pages.
 */

public class SelfLandingActivity extends AppCompatActivity {

    // Declaring two button variables:
    Button goToMoodQuiz;
    Button goToMoodLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_landing); // Sets the page's layout using activity_self_landing.xml file
        setTitle("Myself"); // Title of page in the application

        goToMoodQuiz = findViewById(R.id.button_take_quiz); // Initialising take quiz button defined in activity_self_landing.xml file
        goToMoodQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Clicking on take quiz will take the user to the mood quiz page
                Intent landingToQuiz = new Intent(SelfLandingActivity.this, MoodQuizActivity.class);
                startActivity(landingToQuiz);
            }
        });

        goToMoodLog = findViewById(R.id.button_mood_log); // Initialising mood log button defined in activity_self_landing.xml file
        goToMoodLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Clicking on the mood log button will take the user their mood log
                Intent landingToLog = new Intent(SelfLandingActivity.this, MoodLogActivity.class);
                startActivity(landingToLog);
            }
        });
    }

}