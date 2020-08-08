package com.example.well_beingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_quiz);
        setTitle("Mood Quiz");
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        uid = fAuth.getCurrentUser().getUid();

        Calendar calendar = Calendar.getInstance();
        final String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
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

                if (firstEntry.getText().toString().isEmpty()){
                    firstEntry.setText("0");
                }
                if (secondEntry.getText().toString().isEmpty()){
                    secondEntry.setText("0");
                }
                if (thirdEntry.getText().toString().isEmpty()){
                    thirdEntry.setText("0");
                }
                if (fourthEntry.getText().toString().isEmpty()){
                    fourthEntry.setText("0");
                }
                if (fifthEntry.getText().toString().isEmpty()){
                    fifthEntry.setText("0");
                }

                int num1 = Integer.parseInt(firstEntry.getText().toString());
                int num2 = Integer.parseInt(secondEntry.getText().toString());
                int num3 = Integer.parseInt(thirdEntry.getText().toString());
                int num4 = Integer.parseInt(fourthEntry.getText().toString());
                int num5 = Integer.parseInt(fifthEntry.getText().toString());

                totalScore = (num1 + num2 + num3 + num4 + num5) * 4;
                textViewResult.setText(String.valueOf(totalScore));


                Map<String, Object> moodScores = new HashMap<>();
                moodScores.put("moodScore", totalScore);
                moodScores.put("date", currentDate);
                moodScores.put("uid", uid);

                // Add a new document with a generated ID
                fStore.collection("moodScores")
                        .add(moodScores)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("TAG", "Error adding document", e);
                            }
                        });

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