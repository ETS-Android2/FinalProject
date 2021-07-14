package com.example.hitchikersguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class SavedList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_list);

        //Define Next Activity
        Intent activityJK = new Intent(this, Jokes.class);

        Button jokeButton = findViewById(R.id.SL_ToJokesButton);
        jokeButton.setOnClickListener(click  -> startActivity(activityJK));

        // Listview
        RecyclerView imgList = findViewById(R.id.SL_ListOfImages);
        //imgList.layoutM
        //imgList.setAdapter(ListAdapter myListAdapter);
        // Progress bar will be on async task


    }
}