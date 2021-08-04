package com.example.hitchikersguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

/**
 * Toolbar holds the app's toolbar icons and the navigation drawer for implementation in the
 * base activities
 *
 * @author Brianna Guerin
 * @author Jenne Stamplecoskie
 */
public class ToolBar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    /**
     * On Create Function initializes widgets and listeners
     *
     * @param savedInstanceState - the state that the app was last saved in
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);

        //Get toolbar from layout
        Toolbar myToolbar = findViewById(R.id.toolbar);
        //Loads the toolbar, calls onCreateOptionsMenu
        setSupportActionBar(myToolbar);

        DrawerLayout drawer = findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, myToolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);

        // Welcome Page (maybe)
        // Saved Pictures List
        // Date Picker Page
        // Jokes Page
    }

    /**
     * onCreate option menu
     *
     * @param menu
     * @return
     */
    // TODO: Finish JavaDoc
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