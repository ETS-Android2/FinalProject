package com.example.hitchikersguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Define Next Activity
        Intent activitySL = new Intent(this, SavedList.class);
        Intent activityID = new Intent(this, ImageDisplay.class);

        ImageButton thumbButton = findViewById(R.id.imageButton);
        thumbButton.setOnClickListener(click -> startActivity(activityID));

        Button panicButton = findViewById(R.id.AM_dontPanic);
        panicButton.setOnClickListener(click  -> startActivity(activitySL));

        // Some kind of edit texts
        // save info to shared preferences
    }
}