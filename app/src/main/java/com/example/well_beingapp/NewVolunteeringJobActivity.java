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
 * The 'NewVolunteeringJobActivity' class sets up the 'add volunteering job' window for the user,
 * in which the UI elements are generated with the 'setContentView(R.layout.activity_new_volunteering_job)'
 * method. This class also includes the logic required to add their new volunteering job to the Google
 * Firestore database.
 */

public class NewVolunteeringJobActivity extends AppCompatActivity {

    private FirebaseAuth fAuth = FirebaseAuth.getInstance(); // Getting an instance of Firebase Authentication

    // Declaring the variables required to add a volunteering job:
    private EditText editTextName;
    private EditText editTextDescription;
    private EditText editTextDate;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_volunteering_job); // Sets the page's layout using activity_new_volunteering_job.xml file
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add volunteering job"); // Title of page in the application

        // Linking variables to corresponding fields outlined in the activity_new_volunteering_job.xml file:
        editTextName = findViewById(R.id.edit_text_org_name);
        editTextDescription = findViewById(R.id.edit_text_vol_description);
        editTextDate = findViewById(R.id.edit_text_vol_date);
        uid = fAuth.getCurrentUser().getUid(); // Get the current user's id

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_volunteering_menu, menu); // Sets the menu to new_volunteering_menu.xml file
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_volunteering_item: // Save icon
                saveVolItem();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void saveVolItem() { // Method that takes the user's new volunteering job and stores it in the database

        // Setting variables to what the user has input in the new volunteering job fields:
        String volunteeringOrgName = editTextName.getText().toString();
        String volunteeringDescription = editTextDescription.getText().toString();
        String date = editTextDate.getText().toString();
        String userId = uid;

        // This message is shown if any field is left blank:
        if (volunteeringOrgName.trim().isEmpty() || volunteeringDescription.trim().isEmpty() || date.trim().isEmpty()){
            Toast.makeText(this, "You must not leave any field blank!", Toast.LENGTH_SHORT).show();
            return;
        }

        CollectionReference volJobRef = FirebaseFirestore.getInstance()
                .collection("volunteeringJobs"); // Adds data to the 'volunteeringJobs' collection in the database
        // Each document includes an organisation's name, volunteering description, completion date and the user's id:
        volJobRef.add(new VolunteeringItem(volunteeringOrgName, volunteeringDescription, date, userId));
        Toast.makeText(this, "Job added!", Toast.LENGTH_SHORT).show(); // Pop up message that shows job was added to the database
        finish();
    }

}