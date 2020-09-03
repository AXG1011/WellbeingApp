package com.example.well_beingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**
 * @author Arjun Gill
 * @version 1.0
 * @since 4/9/20
 *
 * The 'NewGoalActivity' class sets up the 'add goal' window for the user, in which the UI elements
 * are generated with the 'setContentView(R.layout.activity_new_goal)' method. This class also includes
 * the logic required to add their new goal to the Google Firestore database.
 */

public class NewGoalActivity extends AppCompatActivity {

    private FirebaseAuth fAuth = FirebaseAuth.getInstance(); // Getting an instance of Firebase Authentication

    // Declaring the variables required to add a goal:
    private EditText editTextTitle;
    private EditText editTextDescription;
    private EditText editTextSubGoals;
    private EditText editTextDate;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_goal); // Sets the page's layout using activity_new_goal.xml file
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close); // Close icon
        setTitle("Add goal"); // Title of page in the application

        // Linking variables to corresponding fields outlined in the activity_new_goal.xml file:
        editTextTitle = findViewById(R.id.edit_text_goal_title);
        editTextDescription = findViewById(R.id.edit_text_goal_description);
        editTextSubGoals = findViewById(R.id.edit_text_sub_goals);
        editTextDate = findViewById(R.id.edit_text_end_date);
        uid = fAuth.getCurrentUser().getUid(); // Get the current user's id

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_goal_menu, menu); // Sets the menu to new_goal_menu.xml file
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_goal: // Save icon
                saveGoal();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void saveGoal() { // Method that takes the user's new goal and stores it in the database

        // Setting variables to what the user has input in the new goal fields:
        String goalTitle = editTextTitle.getText().toString();
        String goalDescription = editTextDescription.getText().toString();
        String subGoals = editTextSubGoals.getText().toString();
        String endDate = editTextDate.getText().toString();
        String userId = uid;

        // This message is shown if any field is left blank:
        if (goalTitle.trim().isEmpty() || goalDescription.trim().isEmpty() || subGoals.trim().isEmpty() || endDate.trim().isEmpty()){
            Toast.makeText(this, "You must not leave any field blank!", Toast.LENGTH_SHORT).show();
            return;
        }

        CollectionReference goalsRef = FirebaseFirestore.getInstance()
                .collection("goals"); // Adds data to the 'goals' collection in the database
        // Each document includes a title, description, sub-goals, an end date and the user's id:
        goalsRef.add(new GoalLog(goalTitle, goalDescription, subGoals, endDate, userId));
        Toast.makeText(this, "Goal added!", Toast.LENGTH_SHORT).show(); // Pop up message that shows goal was added to the database
        finish();
    }

}