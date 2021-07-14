package com.example.hitchikersguide;

import androidx.annotation.NonNull;
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

public class ToolBar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);

        //Get toolbar from layout
        Toolbar myToolbar = findViewById(R.id.toolbar);
        //Loads the toolbar, calls onCreateOptionsMenu
        setSupportActionBar(myToolbar);

        // Welcome Page (maybe)
        // Saved Pictures List
        // Date Picker Page
        // Jokes Page
    }

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

        return false;
    }
}