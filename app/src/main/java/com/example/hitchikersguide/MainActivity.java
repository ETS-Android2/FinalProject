package com.example.hitchikersguide;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Main Activity is the first activity loaded for the Hitchhiker's Guide APP
 * Provides a place for the user to enter their login name
 * Links to image list through the Logo
 * Links to date picker through the don't panic button
 *
 * @author Brianna Guerin
 * @author Jenne Stamplecoskie
 */
public class MainActivity extends AppCompatActivity {
    private SharedPreferences prefs;

    /**
     * On Create Function initializes widgets and listeners
     *
     * @param savedInstanceState - the state that the app was last saved in
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define Next Activity
        Intent activitySL = new Intent(this, SavedList.class);
        Intent activityID = new Intent(this, ImageDisplay.class);

        // Logo Opens saved list of images
        ImageButton thumbButton = findViewById(R.id.AM_imageButton);
        thumbButton.setOnClickListener(click -> startActivity(activitySL));

        // Panic Button opens date picker activity
        Button panicButton = findViewById(R.id.AM_dontPanic);
        panicButton.setOnClickListener(click  -> startActivity(activityID));

        // Enter Login Name
        TextView myName = findViewById(R.id.AM_enterName);
        Button enterName = findViewById(R.id.AM_saveLogin);
        String parName = "Name";
        enterName.setOnClickListener(click -> {
            saveSharedPrefs(parName , myName.getText().toString());
            panicButton.setText(String.format(getString(R.string.don_t_panic), myName.getText().toString()));
            // TODO: Close keyboardJenne
            // TODO: Ideally we would close this out once logged in and have it open only as an
            //  option on the nav menu. Would probably need to load the login row as a fragment
        });

        // Load the sharedPreferences
        prefs = getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        String loginName = prefs.getString(parName, "");

        panicButton.setText(String.format(getString(R.string.don_t_panic), loginName));
    }

    /**
     * Saves users login name to share preferences for next opening of app
     * @param parName - this is the name of the parameter to be saved in shared preferences
     * @param parValue - this is the value of the parameter to be saved in shared preferences
     */
    private void saveSharedPrefs(String parName, String parValue)
    {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(parName, parValue);
        editor.apply();
    }
    // TODO: Add Toolbar and Nav drawer
    // TODO: Background sb black, maybe with starts, text and buttons adjusted appropriately.
}
