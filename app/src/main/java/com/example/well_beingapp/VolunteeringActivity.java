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
 * The 'VolunteeringActivity' class sets up the 'volunteering log' window for the user, in which the UI elements
 * are generated with the 'setContentView(R.layout.activity_volunteering)' method. This class also includes
 * the logic required to retrieve a user's volunteering jobs from the Google Firestore database and fill the
 * recycler view with this data.
 */

public class VolunteeringActivity extends AppCompatActivity {

    private FirebaseAuth fAuth = FirebaseAuth.getInstance(); // Getting an instance of Firebase Authentication
    private FirebaseFirestore db = FirebaseFirestore.getInstance(); // Getting an instance of Firebase Firestore
    private CollectionReference uidRef = db.collection("volunteeringJobs"); // Making a reference to the 'volunteeringJobs' collection in the database
    private VolunteeringItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteering); // Sets the page's layout using activity_volunteering.xml file
        setTitle("Volunteering Log"); // Title of page in the application

        FloatingActionButton buttonAddVolunteering = findViewById(R.id.button_add_volunteering); // Initialising add volunteering job button defined in XML file
        buttonAddVolunteering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // When the button is clicked, this method will execute
                startActivity(new Intent(VolunteeringActivity.this, NewVolunteeringJobActivity.class )); // Takes the user to the add new volunteering job page
            }
        });

        setUpRecyclerView(); // setUpRecyclerView method executed upon pages creation

    }

    public void setUpRecyclerView(){

        Query query = uidRef.whereEqualTo("uid", fAuth.getCurrentUser().getUid()); // Query used to match user with their data stored in the database

        FirestoreRecyclerOptions<VolunteeringItem> options = new FirestoreRecyclerOptions.Builder<VolunteeringItem>()
                .setQuery(query, VolunteeringItem.class) // Get the volunteering job's organisation name, description, date and the user's id
                .build();

        adapter = new VolunteeringItemAdapter(options);

        RecyclerView volRecyclerView = findViewById(R.id.recycler_view_volunteering); // Locates the recycler view in the activity_volunteering.xml file
        volRecyclerView.setHasFixedSize(true);
        volRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        volRecyclerView.setAdapter(adapter); // Sets the adapter to the 'VolunteeringItemAdapter' class

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) { // Swipe left/right to delete job
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                adapter.deleteItem(viewHolder.getAdapterPosition()); // When swiped left or right, permanently  delete volunteering job from database

            }
        }).attachToRecyclerView(volRecyclerView);

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