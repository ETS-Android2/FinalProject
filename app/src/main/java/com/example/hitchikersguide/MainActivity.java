package com.example.hitchikersguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

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
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
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

//        // Toolbar button
//        Intent activityTB = new Intent(this, ToolBar.class);
//        Button tbButton = findViewById(R.id.tb);
//        tbButton.setOnClickListener(click -> startActivity(activityTB));

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

        // Toolbar as action bar
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        DrawerLayout drawer = findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, tb, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        drawer.bringToFront();
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflater to inflate menu items in toolbar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //new Intents that direct to each activity
        Intent image = new Intent(this, ImageDisplay.class);
        Intent jokes = new Intent(this, Jokes.class);
        Intent saved = new Intent(this, SavedList.class);
        Intent main = new Intent(this, MainActivity.class);

        //switch cases for toolbar icons, direct to each activity
        //depending on icon selected by user
        switch(item.getItemId()) {
            case R.id.ufo:
                startActivity(image);
                break;
            case R.id.towel:
                startActivity(jokes);
                break;
            case R.id.comet:
                startActivity(saved);
                break;
            case R.id.number:
                startActivity(main);
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawerLayout = findViewById(R.id.drawer);
        drawerLayout.closeDrawer(GravityCompat.START);

        Intent image = new Intent(this, ImageDisplay.class);
        Intent jokes = new Intent(this, Jokes.class);
        Intent saved = new Intent(this, SavedList.class);
        Intent main = new Intent(this, MainActivity.class);

        //switch cases for toolbar icons, direct to each activity
        //depending on icon selected by user
        switch(item.getItemId()) {
            case R.id.nav_image:
                startActivity(image);
                break;
            case R.id.nav_jokes:
                startActivity(jokes);
                break;
            case R.id.nav_saved:
                startActivity(saved);
                break;
            case R.id.nav_main:
                startActivity(main);
                break;
        }

        return false;
    }
}
