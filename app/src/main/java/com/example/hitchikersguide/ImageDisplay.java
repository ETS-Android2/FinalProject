package com.example.hitchikersguide;

import androidx.appcompat.widget.Toolbar;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

/**
 * Image Display Activity shows the selected image and its related details
 * Link to HDURL
 *
 * @author Brianna Guerin
 * @author Jenne Stamplecoskie
 */
public class ImageDisplay extends BaseActivity {
    ProgressBar progressBar;
    TextView curDate, curTitle, curURL, curHDURL, curDetails;
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
        if (newDate == null) {
            newDate = "2021-07-20";
        }else {
            newDate = passDate.getStringExtra("Date");
        }
//        setContentView(R.layout.activity_image_display);
        // TODO: Image, Image Description, Date, Link to HDURL
        // TODO: Snackbar to say are you sure you want to leave this page to open the HDURL

//        // Initialize Date Picker
//        DatePicker picker;
//        picker = findViewById(R.id.ID_datePicker);

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

//        Button dateButton = findViewById(R.id.ID_PickDate);
//        dateButton.setOnClickListener(click -> {
//
//            newDate = picker.getYear() + "-" + (picker.getMonth() + 1 )+ "-" + picker.getDayOfMonth();
//            Log.i("DatePicker: ", "date selected is: " + newDate);

            // Open AsyncTask
//            String picADayURL = "https://api.nasa.gov/planetary/apod?api_key=DU59VMplWgJa1xFzZbTuMZgLkdcVeoZkJZu21esv&date=" + newDate;
//            NASAQuery getImageDetails = new NASAQuery();
//            getImageDetails.execute(picADayURL);

//        });

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setProgress(0);

        //Mod 3 Intents
        //    String url = "http://www.algonquincollege.com";
        //    Intent i = new Intent(Intent.ACTION_VIEW);
        //    i.setData( Uri.parse(url) );
        //    startActivity(i);
//        String date = "2021-07-01";

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

        // Toolbar as action bar
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
    }

    private class NASAQuery extends AsyncTask<String, Integer, String> {
        Bitmap myPic;

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

//

//TODO: Update progress bars
            //TODO: error coding for when not a picture
            //TODO: remove URL from display
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
            Picasso.get().load(imgURL).into(curImage);
//            curImage.setImageBitmap(myPic);
            curDate.setText(imgDate);
            curTitle.setText(imgTitle);
            curURL.setText(imgURL);
            curHDURL.setText(imgHDURL);
//            curDetails.setText(imgDetails);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}