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
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Arjun Gill
 * @version 1.0
 * @since 4/9/20
 *
 * The 'MoodQuizActivity' class sets up the 'mood quiz' window for the user, in which the UI elements
 * are generated with the 'setContentView(R.layout.activity_mood_quiz)' method. This class also includes
 * the logic required to calculate a mood score for the user, display this final score to the user
 * and save their mood entry to the Google Firestore database.
 */

public class MoodQuizActivity extends AppCompatActivity {

    public static final String EXTRA_NUMBER = "com.example.well_beingapp.EXTRA_TEXT"; // Final mood score to be used by the MoodScoreActivity class

    FirebaseAuth fAuth;
    FirebaseFirestore fStore;

    Button submitQuiz;

    int totalScore;

    String uid;
    String currentDate;

    // Declaring the edit text and text view fields:
    private EditText firstEntry;
    private EditText secondEntry;
    private EditText thirdEntry;
    private EditText fourthEntry;
    private EditText fifthEntry;
    private EditText journalEntry;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_quiz); // Sets the page's layout using activity_mood_quiz.xml file
        setTitle("Mood Quiz"); // Title of page in the application

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        uid = fAuth.getCurrentUser().getUid(); // Getting the current user's id

        Calendar calendar = Calendar.getInstance(); // Getting a calendar instance (including current day, month and year)
        currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        TextView textViewDate = findViewById(R.id.textView10);
        textViewDate.setText(currentDate); // Setting the current date to a text view located in the activity_mood_quiz.xml file

        // Initialising edit text variables to those located in the activity_mood_quiz.xml file:
        firstEntry = findViewById(R.id.entry_one);
        secondEntry = findViewById(R.id.entry_two);
        thirdEntry = findViewById(R.id.entry_three);
        fourthEntry = findViewById(R.id.entry_four);
        fifthEntry = findViewById(R.id.entry_five);
        journalEntry = findViewById(R.id.final_entry);

        textViewResult = findViewById(R.id.result); // Initialising text view result located in the activity_mood_quiz.xml file

        submitQuiz = findViewById(R.id.submit_quiz_button); // Initialising submit quiz button located in the activity_mood_quiz.xml file
        submitQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateMoodScore(); // When users click the submit quiz button, the calculateMoodScore method will execute
            }

        });

    }

    public void calculateMoodScore() { // Calculates the user's mood score based on their input and saves it to the database

        // If the user enters nothing for the first, second, third, fourth, fifth entries, they are set to '0':
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
        if (journalEntry.getText().toString().isEmpty()){
            journalEntry.setText("No journal entry"); // If journal entry field if left empty, this text will be set
        }
            // Converting the users string text entries into integers:
            int num1 = Integer.parseInt(firstEntry.getText().toString());
            int num2 = Integer.parseInt(secondEntry.getText().toString());
            int num3 = Integer.parseInt(thirdEntry.getText().toString());
            int num4 = Integer.parseInt(fourthEntry.getText().toString());
            int num5 = Integer.parseInt(fifthEntry.getText().toString());

            // Calculates a total score by summing the five entries and multiplying the result by 4 (as per the WHO-5 index guidelines)
            totalScore = (num1 + num2 + num3 + num4 + num5) * 4;
            textViewResult.setText(String.valueOf(totalScore)); // Sets the text view result as the total score

            /* If the score is greater than 100, the user has entered a number greater than 5 for one or more of the statements, therefore,
            the following error message is shown:
             */
            if (totalScore > 100) {
                Toast.makeText(this, "Error: entries must be between 0 and 5!", Toast.LENGTH_SHORT).show();
            }
            else { // If all entries are between 0 and 5, the score, along with the date, the user's id and their journal entry will be saved to the database

                // Creating a hash map with the user's mood score, current date, user id and journal entry, and declaring document fields:
                Map<String, Object> moodScores = new HashMap<>();
                moodScores.put("moodScore", totalScore);
                moodScores.put("date", currentDate);
                moodScores.put("uid", uid);
                moodScores.put("journalEntry", journalEntry.getText().toString());

                // Add a new document with a generated ID
                fStore.collection("moodScores") // Adds data to the 'moodScores' collection
                        .add(moodScores) // Add new mood score object to moodScores collection
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) { // If the task is successful:
                                Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) { // If the task fails:
                                Log.w("TAG", "Error adding document", e);
                            }
                        });

                openMoodScore(); // Opens a new activity with the user's total score and button to view their mood log
            }

        }

    public void openMoodScore(){

        int finalScore = totalScore; // Getting the user's final mood score

        Intent intentToMoodScore = new Intent(this, MoodScoreActivity.class); // Opening mood score page
        intentToMoodScore.putExtra(EXTRA_NUMBER, finalScore); // Getting the final mood score and taking it to the mood score activity
        startActivity(intentToMoodScore);
    }

}