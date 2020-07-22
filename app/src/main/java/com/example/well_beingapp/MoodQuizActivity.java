package com.example.well_beingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class MoodQuizActivity extends AppCompatActivity {

    Button logEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_quiz);
        setTitle("Mood Quiz");

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        TextView textViewDate = findViewById(R.id.textView10);
        textViewDate.setText(currentDate);

        logEntry = findViewById(R.id.button2);
        logEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToMoodDiary = new Intent(MoodQuizActivity.this, MoodLogActivity.class);
                startActivity(intToMoodDiary);
            }
        });

    }

}