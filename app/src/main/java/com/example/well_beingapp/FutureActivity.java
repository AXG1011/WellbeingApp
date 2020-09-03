package com.example.well_beingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * @author Arjun Gill
 * @version 1.0
 * @since 4/9/20
 *
 * The 'FutureActivity' class sets up the 'future' window for the user, in which the UI elements
 * are generated with the 'setContentView(R.layout.activity_future)' method. This class also includes
 * the logic required to retrieve a user's goals from the Google Firestore database and fill the
 * recycler view with this data.
 */

public class FutureActivity extends AppCompatActivity {

    private FirebaseAuth fAuth = FirebaseAuth.getInstance(); // Getting an instance of Firebase Authentication
    private FirebaseFirestore db = FirebaseFirestore.getInstance(); // Getting an instance of Firebase Firestore
    private CollectionReference uidRef = db.collection("goals"); // Making a reference to the 'goals' collection in the database
    private GoalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future); // Sets the page's layout using activity_future.xml file
        setTitle("My Future"); // Title of page in the application

        FloatingActionButton buttonAddGoal = findViewById(R.id.button_add_goal); // Initialising add goal button defined in XML file
        buttonAddGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // When the button is clicked, this method will execute
                startActivity(new Intent(FutureActivity.this, NewGoalActivity.class)); // Takes the user to the add new goal page
            }
        });

        setUpRecyclerView(); // setUpRecyclerView method executed upon pages creation

    }

    public void setUpRecyclerView() { // Sets up the recycler view list by fetching data from database

        Query query = uidRef.whereEqualTo("uid", fAuth.getCurrentUser().getUid()); // Query used to match user with their data stored in the database

        FirestoreRecyclerOptions<GoalLog> options = new FirestoreRecyclerOptions.Builder<GoalLog>()
                .setQuery(query, GoalLog.class) // Get's the goal's title, description, sub-goals, end date and user's id
                .build();

        adapter = new GoalAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_goals); // Locates the recycler view in the activity_future.xml file
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter); // Sets the adapter to the 'GoalAdapter' class

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) { // Swipe left/right to delete goal
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                adapter.deleteItem(viewHolder.getAdapterPosition()); // When swiped left or right, permanently  delete goal from database

            }
        }).attachToRecyclerView(recyclerView);
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