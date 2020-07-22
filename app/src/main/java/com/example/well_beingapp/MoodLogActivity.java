package com.example.well_beingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MoodLogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_log);
        setTitle("Mood Log");
    }
}