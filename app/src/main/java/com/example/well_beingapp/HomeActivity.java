package com.example.well_beingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

/**
 * @author Arjun Gill
 * @version 1.0
 * @since 4/9/20
 *
 * The 'HomeActivity' class sets up the 'landing page' window for the user, in which the UI elements
 * are generated with the 'setContentView(R.layout.activity_home)' method.
 */

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home); // Sets the page's layout using activity_home.xml file

        ImageView settings = findViewById(R.id.imageView2); // Initialising settings icon defined in activity_home.xml file
        settings.bringToFront();
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Clicking on imageView2 will take the user to the settings page
                Intent homeToSettings = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(homeToSettings);
            }
        });

        ImageView self = findViewById(R.id.imageView4); // Initialising self icon defined in activity_home.xml file
        self.bringToFront();
        self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Clicking on imageView4 will take the user to the self-landing page
                Intent homeToSelf = new Intent(HomeActivity.this, SelfLandingActivity.class);
                startActivity(homeToSelf);
            }
        });

        ImageView future = findViewById(R.id.imageView6); // Initialising future icon defined in activity_home.xml file
        future.bringToFront();
        future.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Clicking on imageView6 will take the user to their future page (goal log)
                Intent homeToFuture = new Intent(HomeActivity.this, FutureActivity.class);
                startActivity(homeToFuture);
            }
        });

        ImageView world = findViewById(R.id.imageView5); // Initialising world icon defined in activity_home.xml file
        world.bringToFront();
        world.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // Clicking on imageView5 will take the user to the world-landing page
                Intent homeToWorld = new Intent(HomeActivity.this, WorldLandingActivity.class);
                startActivity(homeToWorld);
            }
        });
    }

}