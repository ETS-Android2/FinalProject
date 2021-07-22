package com.example.hitchikersguide;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Image Display Activity shows the selected image and its related details
 * Link to HDURL
 *
 * @author Brianna Guerin
 * @author Jenne Stamplecoskie
 */
public class ImageDisplay extends AppCompatActivity {
    ProgressBar progressBar;
    TextView curDate, curTitle, curURL, curHDURL, curDetails;
//    ImageView curImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);
        // TODO: Image, Image Description, Date, Link to HDURL
        // TODO: Snackbar to say are you sure you want to leave this page to open the HDURL

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(0);

        //Mod 3 Intents
        //    String url = "http://www.algonquincollege.com";
        //    Intent i = new Intent(Intent.ACTION_VIEW);
        //    i.setData( Uri.parse(url) );
        //    startActivity(i);
        String date = "2021-07-01";

        // Define fields
        curDate = findViewById(R.id.ID_Date);
        curTitle = findViewById(R.id.ID_Title);
        curURL = findViewById(R.id.ID_Url);
        curHDURL = findViewById(R.id.ID_HDurl);
        curDetails = findViewById(R.id.ID_Detail);

        // Open AsyncTask
        String picADayURL = "https://api.nasa.gov/planetary/apod?api_key=DU59VMplWgJa1xFzZbTuMZgLkdcVeoZkJZu21esv&date=" + date;
        NASAQuery forecast = new NASAQuery();
        forecast.execute(picADayURL);
    }

    private class NASAQuery extends AsyncTask<String, Integer, String> {
        Bitmap myPic;
        String imgDate, imgTitle, imgURL, imgDetails, imgHDURL;

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
                BufferedReader reader = new BufferedReader(new InputStreamReader(response, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                String result = sb.toString(); //result is the whole string

                // convert string to JSON:
                JSONObject spacePicData = new JSONObject(result);

                // get values
                imgTitle = String.valueOf(spacePicData.getString("title"));
                imgDate = String.valueOf(spacePicData.getString("date"));
                imgURL = String.valueOf(spacePicData.getString("url"));
                imgDetails = String.valueOf(spacePicData.getString("explanation"));
                imgHDURL = String.valueOf(spacePicData.getString("hdurl"));

                publishProgress(25);
                Log.i("ImageDisplay", "Image url is " + imgURL);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            } // end first try block

//            publishProgress(25);
//            minTemp = "Low: " + xpp.getAttributeValue(null, "min")
//                    + " \u2103";
//            Log.i("ForecastQuery: ", "minimum temperature is " + minTemp);
//            publishProgress(50);
//            maxTemp = "High: " + xpp.getAttributeValue(null, "max")
//                    + " \u2103";
//            Log.i("ForecastQuery: ", "maximum temperature is " + maxTemp);
//            publishProgress(75);
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
            curDate.setText(imgDate);
            curTitle.setText(imgTitle);
            curURL.setText(imgURL);
            curHDURL.setText(imgHDURL);
            curDetails.setText(imgDetails);
//            currTV.setText(currTemp);
//            minTV.setText(minTemp);
//            maxTV.setText(maxTemp);
//            wxIcon.setImageBitmap(wxPic);
//            uvRate.setText(UVRating);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}