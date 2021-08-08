package com.example.hitchikersguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class UserDetails extends BaseActivity {
    private SharedPreferences prefs;
    private static final String PREF_FILENAME = "myPrefs";
    private static final String USRNAME = "Name";
    private static final String EMAIL = "Email";
    private static final String BDAY = "Birthday";
    private static final String ABOUT = "Bio";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the Main Activity layout into the Base activity frame
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_user_details, contentFrameLayout);

        // Load the sharedPreferences
        prefs = getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE);
        String loginName = prefs.getString(USRNAME, "");

        // Initialize Page Elements
        ImageView profilePic = findViewById(R.id.UD_imageView);
        TextView name = findViewById(R.id.UD_Name);
        TextView email = findViewById(R.id.UD_Email);
        TextView birthday = findViewById(R.id.UD_Birthdate);
        TextView bio = findViewById(R.id.UD_Bio);
        Button update = findViewById(R.id.UD_UpdateButton);

        update.setOnClickListener(click -> {
            saveSharedPrefs(USRNAME, name.getText().toString());
            saveSharedPrefs(EMAIL, email.getText().toString());
            saveSharedPrefs(BDAY, birthday.getText().toString());
            saveSharedPrefs(ABOUT, bio.getText().toString());
        });

        // Profile pic loading crashed it (I think I need to make a smaller profile pic)
        // Update info... do we want textviews and update has edit text or ??

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
}