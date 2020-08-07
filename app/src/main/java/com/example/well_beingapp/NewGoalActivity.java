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

public class NewGoalActivity extends AppCompatActivity {

    private FirebaseAuth fAuth = FirebaseAuth.getInstance();

    private EditText editTextTitle;
    private EditText editTextDescription;
    private EditText editTextDate;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_goal);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add goal");

        editTextTitle = findViewById(R.id.edit_text_goal_title);
        editTextDescription = findViewById(R.id.edit_text_goal_description);
        editTextDate = findViewById(R.id.edit_text_end_date);
        uid = fAuth.getCurrentUser().getUid();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_goal_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_goal:
                saveGoal();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void saveGoal(){
        String goalTitle = editTextTitle.getText().toString();
        String goalDescription = editTextDescription.getText().toString();
        String endDate = editTextDate.getText().toString();
        String userId = uid;

        if (goalTitle.trim().isEmpty() || goalDescription.trim().isEmpty() || endDate.trim().isEmpty()){
            Toast.makeText(this, "You must not leave any field blank!", Toast.LENGTH_SHORT).show();
            return;
        }

        CollectionReference goalsRef = FirebaseFirestore.getInstance()
                .collection("goals");
        goalsRef.add(new GoalLog(goalTitle, goalDescription, endDate, userId));
        Toast.makeText(this, "Goal added!", Toast.LENGTH_SHORT).show();
        finish();

    }

}