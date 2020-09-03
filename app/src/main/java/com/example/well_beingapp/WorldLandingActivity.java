package com.example.well_beingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @author Arjun Gill
 * @version 1.0
 * @since 4/9/20
 *
 * The 'WorldLandingActivity' class sets up the 'world landing page' window for the user, in which the UI elements
 * are generated with the 'setContentView(R.layout.activity_world_landing)' method. This class provides logic
 * for two buttons that take the user to new pages.
 */

public class WorldLandingActivity extends AppCompatActivity {

    // Declaring two button variables:
    Button goToMapsPage;
    Button goToVolunteerLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_landing); // Sets the page's layout using activity_world_landing.xml file
        setTitle("My World"); // Title of page in the application

        goToMapsPage = findViewById(R.id.button_find_opps); // Initialising find opportunities button defined in activity_world_landing.xml file
        goToMapsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Clicking on find opportunities button will take the user to the opportunity finder map
                Intent landingToMaps = new Intent(WorldLandingActivity.this, WorldActivity.class);
                startActivity(landingToMaps);
            }
        });

        goToVolunteerLog = findViewById(R.id.button_volunteering_log); // Initialising volunteering log button defined in activity_world_landing.xml file
        goToVolunteerLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Clicking on the volunteering log button will take the user their volunteering log
                Intent landingToLog = new Intent(WorldLandingActivity.this, VolunteeringActivity.class);
                startActivity(landingToLog);
            }
        });
    }

}