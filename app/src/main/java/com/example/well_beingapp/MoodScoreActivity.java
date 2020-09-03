package com.example.well_beingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Arjun Gill
 * @version 1.0
 * @since 4/9/20
 *
 * The 'MoodScoreActivity' class sets up the 'mood score' window for the user, in which the UI elements
 * are generated with the 'setContentView(R.layout.activity_mood_score)' method. This class also includes
 * the logic required to get the total score from the 'MoodQuizActivity' class, as well as the logic to
 * set the dimensions of the display.
 */

public class MoodScoreActivity extends AppCompatActivity {

    int finalScore;
    Button moodLogBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_score); // Sets the page's layout using activity_mood_score.xml file

        moodLogBtn = findViewById(R.id.moodLogButton); // Initialising 'moodLogBtn' as the button defined in XML file

        // Setting the dimensions of the pop-up window that displays the user's final score:
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .9), (int) (height * .72));

        // Gets the final score from the 'MoodQuizActivity' class and shows in a text view defined in the XML file
        Intent intent = getIntent();
        finalScore = intent.getIntExtra(MoodQuizActivity.EXTRA_NUMBER, 0);
        TextView textViewScore = findViewById(R.id.final_mood_score);
        textViewScore.setText("" + finalScore);

        openMoodLog();

    }

    public void openMoodLog() {

        moodLogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Clicking the mood log button will take the user to their mood log
                Intent intentToMoodLog = new Intent(MoodScoreActivity.this, MoodLogActivity.class);
                startActivity(intentToMoodLog);

            }
        });
    }

}