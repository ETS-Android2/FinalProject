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


import com.google.android.material.navigation.NavigationView;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Use layoutID passed in
        setContentView(R.layout.activity_base);

        // Initialize Toolbar
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        // Initialize Drawer
        DrawerLayout drawer = findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, tb, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Initialize Navigation View
        NavigationView navigationView = findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);
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
        Intent GetImage = new Intent(this, ImageDisplay.class);
        Intent UserDetails = new Intent(this, UserDetails.class);
        Intent ImageList = new Intent(this, SavedList.class);
        Intent DatePicker = new Intent(this, DatePicker.class);
        Intent Everything = new Intent(this, Jokes.class);

        Intent Home = new Intent(this, MainActivity.class);

        //switch cases for toolbar icons, direct to each activity
        //depending on icon selected by user
        switch(item.getItemId()) {
            case R.id.ufo:
                startActivity(GetImage);
                break;
            case R.id.towel:
                startActivity(UserDetails);
                break;
            case R.id.comet:
                startActivity(ImageList);
                break;
            case R.id.calendar:
                startActivity(DatePicker);
                break;
            case R.id.number:
                startActivity(Everything);
                break;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawerLayout = findViewById(R.id.drawer);
        drawerLayout.closeDrawer(GravityCompat.START);

        Intent GetImage = new Intent(this, ImageDisplay.class);
        Intent UserDetails = new Intent(this, UserDetails.class);
        Intent ImageList = new Intent(this, SavedList.class);
        Intent DatePicker = new Intent(this, DatePicker.class);
        Intent Everything = new Intent(this, Jokes.class);
        Intent Home = new Intent(this, MainActivity.class);

        //switch cases for toolbar icons, direct to each activity
        //depending on icon selected by user
        switch(item.getItemId()) {
            case R.id.nav_home:
                startActivity(Home);
                break;
            case R.id.nav_ufo:
                startActivity(GetImage);
                break;
            case R.id.nav_towel:
                startActivity(UserDetails);
                break;
            case R.id.nav_comet:
                startActivity(ImageList);
                break;
            case R.id.nav_calendar:
                startActivity(DatePicker);
                break;
            case R.id.nav_everything:
                startActivity(Everything);
                break;
        }

        return false;
    }

}