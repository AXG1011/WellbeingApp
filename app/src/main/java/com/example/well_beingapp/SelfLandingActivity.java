package com.example.well_beingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelfLandingActivity extends AppCompatActivity {

    Button goToMoodQuiz;
    Button goToMoodLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_landing);

        goToMoodQuiz = findViewById(R.id.button_take_quiz);
        goToMoodQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent landingToQuiz = new Intent(SelfLandingActivity.this, MoodQuizActivity.class);
                startActivity(landingToQuiz);
            }
        });

        goToMoodLog = findViewById(R.id.button_mood_log);
        goToMoodLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent landingToLog = new Intent(SelfLandingActivity.this, MoodLogActivity.class);
                startActivity(landingToLog);
            }
        });

    }
}