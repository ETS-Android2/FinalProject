package com.example.hitchikersguide;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Image Display Activity shows the selected image and its related details
 * Link to HDURL
 *
 * @author Brianna Guerin
 * @author Jenne Stamplecoskie
 */
public class ImageDisplay extends AppCompatActivity {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        // TODO: Image, Image Description, Date, Link to HDURL
        // TODO: Snackbar to say are you sure you want to leave this page to open the HDURL

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(25);

        //Mod 3 Intents
        //    String url = "http://www.algonquincollege.com";
        //    Intent i = new Intent(Intent.ACTION_VIEW);
        //    i.setData( Uri.parse(url) );
        //    startActivity(i);
    }
}