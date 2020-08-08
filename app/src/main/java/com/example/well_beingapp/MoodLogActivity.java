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


public class MoodLogActivity extends AppCompatActivity {

    private FirebaseAuth fAuth = FirebaseAuth.getInstance();

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference uidRef = db.collection("moodScores");

    private MoodScoreAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_log);
        setTitle("Mood Log");

        setUpRecyclerView();

        ImageView goHome = (ImageView) findViewById(R.id.home);
        goHome.bringToFront();
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logToHome = new Intent(MoodLogActivity.this, HomeActivity.class);
                startActivity(logToHome);
            }
        });

    }

    public void setUpRecyclerView() {

        Query query = uidRef.whereEqualTo("uid", fAuth.getCurrentUser().getUid());

            FirestoreRecyclerOptions<ScoreLog> options = new FirestoreRecyclerOptions.Builder<ScoreLog>()
                    .setQuery(query, ScoreLog.class)
                    .build();

            adapter = new MoodScoreAdapter(options);

            RecyclerView recyclerView = findViewById(R.id.recycler_view);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}