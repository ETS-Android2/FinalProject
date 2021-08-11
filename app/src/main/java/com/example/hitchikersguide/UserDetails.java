package com.example.hitchikersguide;

import androidx.appcompat.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UserDetails extends BaseActivity {
    private SharedPreferences prefs;
    private static final String PREF_FILENAME = "myPrefs";
    private static final String USRNAME = "Name";
    String loginName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the Main Activity layout into the Base activity frame
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_user_details, contentFrameLayout);

        // Load the sharedPreferences
        prefs = getSharedPreferences(PREF_FILENAME, Context.MODE_PRIVATE);
        loginName = prefs.getString(USRNAME, "");

        // Initialize UserName with username from shared prefs
        TextView userName = findViewById(R.id.UD_UserName);
        if (!loginName.isEmpty()){
            userName.setText(loginName);
        }

        // Initialize edit button and set on click listener
        ImageButton updateProfile = findViewById(R.id.UD_UpdateButton);
        updateProfile.setOnClickListener(click -> {
            // create an alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Name");
            // set the custom layout
            final View updateLayout = getLayoutInflater().inflate(R.layout.update_name_alert, null);
            builder.setView(updateLayout);
            // add a button
            builder.setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // send data from the AlertDialog to the Activity
                    EditText editName = updateLayout.findViewById(R.id.UD_updateName);
                    loginName = editName.getText().toString();
                    saveSharedPrefs(USRNAME, loginName);
                    userName.setText(loginName);
//                    sendDialogDataToActivity(editName.getText().toString());
                }
            });
            // create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();

            // Make the button pretty
            Button okButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
            if (okButton != null) {
                okButton.setBackgroundColor(getResources().getColor(R.color.hh_blue));
                okButton.setTextColor(getResources().getColor(R.color.hh_orange));
            }
        });

        // Initialize Profile Picture and Make a Toast
        ImageView profilePic = findViewById(R.id.UD_imageView);
        profilePic.setOnClickListener(click -> {
            Toast.makeText(getBaseContext(), R.string.towel_quote, Toast.LENGTH_LONG).show();
        });



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