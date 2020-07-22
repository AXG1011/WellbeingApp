package com.example.well_beingapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WorldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);
        setTitle("My World");

        WebView goodNews = (WebView) findViewById(R.id.webView);
        goodNews.loadUrl("https://www.goodnewsnetwork.org/");
    }
}