package com.example.well_beingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DateFormat;
import java.util.Calendar;

public class MoodQuizActivity extends AppCompatActivity {

    public static final String EXTRA_NUMBER = "com.example.well_beingapp.EXTRA_TEXT";

    private EditText firstEntry;
    private EditText secondEntry;
    private EditText thirdEntry;
    private EditText fourthEntry;
    private EditText fifthEntry;
    private TextView textViewResult;

    Button calculateScoreButton;
    int totalScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_quiz);
        setTitle("Mood Quiz");

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        TextView textViewDate = findViewById(R.id.textView10);
        textViewDate.setText(currentDate);

        firstEntry = findViewById(R.id.entry_one);
        secondEntry = findViewById(R.id.entry_two);
        thirdEntry = findViewById(R.id.entry_three);
        fourthEntry = findViewById(R.id.entry_four);
        fifthEntry = findViewById(R.id.entry_five);
        textViewResult = findViewById(R.id.result);
        calculateScoreButton = findViewById(R.id.calculate_score_button);

        calculateScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (firstEntry.getText().toString().length() == 0){
                    firstEntry.setText("0");
                }
                if (secondEntry.getText().toString().length() == 0){
                    secondEntry.setText("0");
                }
                if (thirdEntry.getText().toString().length() == 0){
                    thirdEntry.setText("0");
                }
                if (fourthEntry.getText().toString().length() == 0){
                    fourthEntry.setText("0");
                }
                if (fifthEntry.getText().toString().length() == 0){
                    fifthEntry.setText("0");
                }

                int num1 = Integer.parseInt(firstEntry.getText().toString());
                int num2 = Integer.parseInt(secondEntry.getText().toString());
                int num3 = Integer.parseInt(thirdEntry.getText().toString());
                int num4 = Integer.parseInt(fourthEntry.getText().toString());
                int num5 = Integer.parseInt(fifthEntry.getText().toString());

                totalScore = (num1 + num2 + num3 + num4 + num5) * 4;

                textViewResult.setText(String.valueOf(totalScore));

                openMoodScore();

            }

        });

    }

    public void openMoodScore(){

        int finalScore = totalScore;

        Intent intentToMoodScore = new Intent(this, MoodScoreActivity.class);
        intentToMoodScore.putExtra(EXTRA_NUMBER, finalScore);
        startActivity(intentToMoodScore);

    }

}