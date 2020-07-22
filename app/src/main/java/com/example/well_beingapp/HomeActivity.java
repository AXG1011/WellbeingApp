package com.example.well_beingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Home");


        ImageView settings = (ImageView) findViewById(R.id.imageView2);
        settings.bringToFront();
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeToSettings = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(homeToSettings);
            }
        });

        ImageView self = (ImageView) findViewById(R.id.imageView4);
        self.bringToFront();
        self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeToSelf = new Intent(HomeActivity.this, MoodQuizActivity.class);
                startActivity(homeToSelf);
            }
        });

        ImageView future = (ImageView) findViewById(R.id.imageView6);
        future.bringToFront();
        future.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeToFuture = new Intent(HomeActivity.this, FutureActivity.class);
                startActivity(homeToFuture);
            }
        });

        ImageView world = (ImageView) findViewById(R.id.imageView5);
        world.bringToFront();
        world.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeToWorld = new Intent(HomeActivity.this, WorldActivity.class);
                startActivity(homeToWorld);
            }
        });

    }

}