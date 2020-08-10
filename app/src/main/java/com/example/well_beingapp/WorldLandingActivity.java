package com.example.well_beingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WorldLandingActivity extends AppCompatActivity {

    Button goToMapsPage;
    Button goToVolunteerLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world_landing);

        goToMapsPage = findViewById(R.id.button_find_opps);
        goToMapsPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent landingToMaps = new Intent(WorldLandingActivity.this, WorldActivity.class);
                startActivity(landingToMaps);
            }
        });

        goToVolunteerLog = findViewById(R.id.button_volunteering_log);
        goToVolunteerLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent landingToLog = new Intent(WorldLandingActivity.this, HomeActivity.class);
                startActivity(landingToLog);
            }
        });

    }
}