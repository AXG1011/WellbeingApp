package com.example.well_beingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * @author Arjun Gill
 * @version 1.0
 * @since 4/9/20
 *
 * The 'MoodLogActivity' class sets up the 'mood log' window for the user, in which the UI elements
 * are generated with the 'setContentView(R.layout.activity_mood_log)' method. This class also includes
 * the logic required to retrieve a user's mood scores from the Google Firestore database and fill the
 * recycler view with this data.
 */

public class MoodLogActivity extends AppCompatActivity {

    private FirebaseAuth fAuth = FirebaseAuth.getInstance(); // Getting an instance of Firebase Authentication
    private FirebaseFirestore db = FirebaseFirestore.getInstance(); // Getting an instance of Firebase Firestore
    private CollectionReference uidRef = db.collection("moodScores"); // Making a reference to the 'moodScores' collection in the database
    private MoodScoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_log); // Sets the page's layout using activity_mood_log.xml file
        setTitle("Mood Log"); // Title of page in the application

        setUpRecyclerView(); // setUpRecyclerView method executed upon pages creation

        ImageView goHome = findViewById(R.id.home); // Initialising the home icon defined in XML file
        goHome.bringToFront();
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Takes the user to the main landing page when they press the home icon
                Intent logToHome = new Intent(MoodLogActivity.this, HomeActivity.class);
                startActivity(logToHome);
            }
        });

    }

    public void setUpRecyclerView() { // Sets up the recycler view list by fetching data from database

        Query query = uidRef.whereEqualTo("uid", fAuth.getCurrentUser().getUid()); // Query used to match user with their data stored in the database

            FirestoreRecyclerOptions<ScoreLog> options = new FirestoreRecyclerOptions.Builder<ScoreLog>()
                    .setQuery(query, ScoreLog.class) // Get's the mood-entry's score, date, journal entry and user's id
                    .build();

            adapter = new MoodScoreAdapter(options);

            RecyclerView recyclerView = findViewById(R.id.recycler_view); // Locates the recycler view in the activity_mood_log.xml file
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter); // Sets the adapter to the 'MoodScoreAdapter' class

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening(); // Listens for changes in the database
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening(); // Stops listening for changes in the database
    }
}