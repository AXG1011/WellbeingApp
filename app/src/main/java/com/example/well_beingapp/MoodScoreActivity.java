package com.example.well_beingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class MoodScoreActivity extends AppCompatActivity {

    int finalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_score);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .9), (int) (height * .6));


        Intent intent = getIntent();
        finalScore = intent.getIntExtra(MoodQuizActivity.EXTRA_NUMBER, 0);
        TextView textViewScore = findViewById(R.id.final_mood_score);
        textViewScore.setText("" + finalScore + "%");

    }

}