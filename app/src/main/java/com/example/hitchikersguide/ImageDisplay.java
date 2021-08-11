package com.example.hitchikersguide;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Image Display Activity shows the selected image and its related details
 * Link to HDURL
 *
 * @author Brianna Guerin
 * @author Jenne Stamplecoskie
 */
public class ImageDisplay extends BaseActivity {
    ProgressBar progressBar;
    TextView curDate, curTitle, curURL, curHDURL;
    String newDate;
    ImageView curImage;
    String imgDate, imgTitle, imgURL, imgDetails, imgHDURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the Image Display Activity layout into the Base Activity frame
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_image_display, contentFrameLayout);

        // Get date from date picker if passed in
        Intent passDate = getIntent();
        newDate = passDate.getStringExtra("Date");
        if (newDate == null) {
            newDate = "2021-07-20";
        }
        Log.i("DatePicker: ", "date selected is: " + newDate);
//        setContentView(R.layout.activity_image_display);
        // TODO: Image, Image Description, Date, Link to HDURL
        // TODO: Snackbar to say are you sure you want to leave this page to open the HDURL

        Button saveImage = findViewById(R.id.ID_SaveImage);
        Intent passImg = new Intent(getBaseContext(), SavedList.class);
        saveImage.setOnClickListener(click -> {
            passImg.putExtra("Date", imgDate);
            passImg.putExtra("Title", imgTitle);
            passImg.putExtra("URL", imgURL);
            passImg.putExtra("HDURL", imgHDURL);
            passImg.putExtra("Details", imgDetails);
            startActivity(passImg);
        });

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(0);

        // Define fields
        curDate = findViewById(R.id.ID_Date);
        curTitle = findViewById(R.id.ID_Title);
        curURL = findViewById(R.id.ID_Url);
        curHDURL = findViewById(R.id.ID_HDurl);
//        curDetails = findViewById(R.id.ID_Detail);
        curImage = findViewById(R.id.ID_imageView);
        curImage.setOnClickListener(v -> {
            AlertDialog.Builder details = new AlertDialog.Builder(this);
            details.setMessage(imgDetails)
                    .setNegativeButton(R.string.dismiss, (click, arg) -> { })
                    .create().show();
        });

        // Open AsyncTask
        String picADayURL = "https://api.nasa.gov/planetary/apod?api_key=DU59VMplWgJa1xFzZbTuMZgLkdcVeoZkJZu21esv&date=" + newDate;
        NASAQuery forecast = new NASAQuery();
        forecast.execute(picADayURL);
    }

    private class NASAQuery extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... args){
            try{
                //create a URL object of what server to contact:
                URL url = new URL(args[0]);

                //open the connection
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                //wait for data:
                InputStream response = urlConnection.getInputStream();

                //JSON reading:
                //Build the entire string response:
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(response, StandardCharsets.UTF_8), 8);
                StringBuilder sb = new StringBuilder();

                String line;// = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                String result = sb.toString(); //result is the whole string

                // convert string to JSON:
                JSONObject spacePicData = new JSONObject(result);
                publishProgress(25);
                // get values
                imgTitle = spacePicData.getString("title");
                imgDate = spacePicData.getString("date");
                publishProgress(50);
                imgURL = spacePicData.getString("url");
                imgDetails = spacePicData.getString("explanation");
                publishProgress(75);
                imgHDURL = spacePicData.getString("hdurl");

                Log.i("ImageDisplay", "Image url is " + imgURL);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            } // end first try block
            publishProgress(100);

            //TODO: error coding for when not a picture
            return "Done";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Picasso.get().load(imgURL).into(curImage);
            curDate.setText(imgDate);
            curTitle.setText(imgTitle);
            curURL.setText(imgURL);
            curHDURL.setText(imgHDURL);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}