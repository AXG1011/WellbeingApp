package com.example.well_beingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * @author Arjun Gill
 * @version 1.0
 * @since 4/9/20
 *
 * The 'AboutActivity' class sets up the 'about page' window for the user, in which the UI elements
 * are generated with the 'setContentView(R.layout.activity_about)' method. This class also includes
 * the logic required to take users to an external website upon clicking on the 'learn more' button
 * located within the about page.
 */

public class AboutActivity extends AppCompatActivity {

    Button learnMoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about); // Sets the page's layout using activity_about.xml file
        setTitle("About"); // Title of page in the application

        learnMoreButton = findViewById(R.id.learn_more); // Initialising 'learnMoreButton' as the button defined in XML file
        learnMoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // When the button is clicked, this method will execute
                String url = "https://en.wikipedia.org/wiki/Beck%27s_cognitive_triad"; // URL of external website
                Intent i = new Intent(Intent.ACTION_VIEW); // Intent to move from app to external website
                i.setData(Uri.parse(url));
                startActivity(i); // Initiates the intent
            }
        });
    }

}