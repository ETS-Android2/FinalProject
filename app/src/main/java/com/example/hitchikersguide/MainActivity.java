package com.example.hitchikersguide;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Main Activity is the first activity loaded for the Hitchhiker's Guide APP
 * Provides a place for the user to enter their login name
 * Links to image list through the Logo
 * Links to date picker through the don't panic button
 * Temporarily links to toolbar through toolbar button
 *
 * @author Brianna Guerin
 * @author Jenne Stamplecoskie
 */
public class MainActivity extends BaseActivity{
    private static final String PREF_FILENAME = "myPrefs";
    private static final String USRNAME = "Name";

    /**
     * On Create Function initializes widgets and listeners
     *
     * @param savedInstanceState - the state that the app was last saved in
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the Main Activity layout into the Base Activity frame
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);

        // Initialize Name Text
        TextView name = findViewById(R.id.UserName);

        // Load the sharedPreferences
        SharedPreferences prefs = getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE);
        String loginName = prefs.getString(USRNAME, "");
        name.setText(loginName);
    }
}


