package com.example.well_beingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MoodQuizActivity extends AppCompatActivity {

    public static final String EXTRA_NUMBER = "com.example.well_beingapp.EXTRA_TEXT";

    Button calculateScoreButton;
    int totalScore = 0;
    List<Integer> userSelections = new ArrayList<Integer>();
    int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_quiz);
        setTitle("Mood Quiz");
        calculateScore(new ArrayList<Integer>(userSelections));

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        TextView textViewDate = findViewById(R.id.textView10);
        textViewDate.setText(currentDate);

        calculateScoreButton = findViewById(R.id.calculate_score_button);
        calculateScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMoodScore();
            }

        });

    }

    public void onRadioButtonClicked (View view){

        boolean checked = ((RadioButton) view).isChecked();

        // Statement 1:
        switch (view.getId()) {
            case R.id.option_1_1:
                if (checked)
                    userSelections.add(5);
                break;
            case R.id.option_1_2:
                if (checked)
                    userSelections.add(4);
                break;
            case R.id.option_1_3:
                if (checked)
                    userSelections.add(3);
                break;
            case R.id.option_1_4:
                if (checked)
                    userSelections.add(2);
                break;
            case R.id.option_1_5:
                if (checked)
                    userSelections.add(1);
                break;
            case R.id.option_1_6:
                if (checked)
                    userSelections.add(0);
                break;

        }

        // Statement 2:
        switch (view.getId()) {
            case R.id.option_2_1:
                if (checked)
                    userSelections.add(5);
                break;
            case R.id.option_2_2:
                if (checked)
                    userSelections.add(4);
                break;
            case R.id.option_2_3:
                if (checked)
                    userSelections.add(3);
                break;
            case R.id.option_2_4:
                if (checked)
                    userSelections.add(2);
                break;
            case R.id.option_2_5:
                if (checked)
                    userSelections.add(1);
                break;
            case R.id.option_2_6:
                if (checked)
                    userSelections.add(0);
                break;

        }

    }

    public int calculateScore(ArrayList<Integer> userSelections) {

        for(int i = 0; i < userSelections.size(); i++)
            sum += userSelections.get(i);
        return sum;

    }

    public void openMoodScore(){

        int score = sum;

        Intent intentToMoodScore = new Intent(this, MoodScoreActivity.class);
        intentToMoodScore.putExtra(EXTRA_NUMBER, score);
        startActivity(intentToMoodScore);

    }

}