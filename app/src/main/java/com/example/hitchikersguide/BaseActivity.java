package com.example.hitchikersguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.material.navigation.NavigationView;

/**
 * BaseActivity created to include toolbar/nav drawer functions
 * All activities extend BaseActivity to include toolbar and nav
 * Links to all other activities through the icons
 * Includes help messages for each activity
 *
 * @author Brianna Guerin
 * @author Jenne Stamplecoskie
 */
public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    /**
     * Sets toolbar and nav drawer, adds header to the nav drawer
     * with appropriate page title
     * Add colour to nav drawer icons
     *
     * @param savedInstanceState - the state that the app was last saved in
     */
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

        View headerView = navigationView.getHeaderView(0);

        TextView nav_title = headerView.findViewById(R.id.nav_title);
        String title = null;
        String activity = this.getClass().getSimpleName();
        switch(activity){
            //Home
            case "MainActivity":
                title = getString(R.string.MA_title);
                break;
            // Get Image
            case "ImageDisplay":
                title = getString(R.string.ID_title);
                break;
            // User Details
            case "UserDetails":
                title = getString(R.string.UD_title);
                break;
            // Image List
            case "SavedList":
                title = getString(R.string.SL_title);
                break;
            // Date Picker
            case "DatePicker":
                title = getString(R.string.DP_title);
                break;
            // Everything
            case "Jokes":
                title = getString(R.string.JK_title);
                break;
        }
        nav_title.setText(title + " " + BuildConfig.VERSION_CODE);

        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Inflate toolbar menu items
     *
     * @param menu - menu to be created
     * @return - true if successful
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflater to inflate menu items in toolbar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_layout, menu);
        return true;
    }

    /**
     * Sets action for each menu item if clicked
     * Includes intents that direct to each activity
     * Includes help message corresponding to current activity
     *
     * @param item - MenuItem selected
     * @return - true if completed successfully
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //new Intents that direct to each activity
        Intent GetImage = new Intent(this, ImageDisplay.class);
        Intent UserDetails = new Intent(this, UserDetails.class);
        Intent ImageList = new Intent(this, SavedList.class);
        Intent DatePicker = new Intent(this, DatePicker.class);
        Intent Everything = new Intent(this, Jokes.class);

        //Switch cases for toolbar icons, direct to each activity
        //Depending on icon selected by user
        switch(item.getTitle().toString()) {
            case "ufo":
                startActivity(GetImage);
                break;
            case "towel":
                startActivity(UserDetails);
                break;
            case "comet":
                startActivity(ImageList);
                break;
            case "calendar":
                startActivity(DatePicker);
                break;
            case "number":
                startActivity(Everything);
                break;
            case "Help":
                createMarvin();
        }
        return true;
    }

    /**
     * Sets actions for nav drawer icons if clicked
     * Includes intents that direct to each activity
     * Includes help message corresponding to current activity
     *
     * @param item - MenuItem selected
     * @return - true if completed successfully
     */
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawerLayout = findViewById(R.id.drawer);
        drawerLayout.closeDrawer(GravityCompat.START);

        Intent Home = new Intent(this, MainActivity.class);

        Intent GetImage = new Intent(this, ImageDisplay.class);
        Intent UserDetails = new Intent(this, UserDetails.class);
        Intent ImageList = new Intent(this, SavedList.class);
        Intent DatePicker = new Intent(this, DatePicker.class);
        Intent Everything = new Intent(this, Jokes.class);

        //Switch cases for toolbar icons, direct to each activity
        //Depending on icon selected by user
        switch(item.getTitle().toString()) {
            case "Welcome Page":
                startActivity(Home);
                break;
            case "Get Image":
                startActivity(GetImage);
                break;
            case "User Details":
                startActivity(UserDetails);
                break;
            case "Image List":
                startActivity(ImageList);
                break;
            case "Date Picker":
                startActivity(DatePicker);
                break;
            case "Everything":
                startActivity(Everything);
                break;
            case "Help":
                createMarvin();
        }
        return true;
    }

    /**
     * Create's the alert dialog for the appropriate page
     */
    public void createMarvin(){
        String message = null;
        String page = this.getClass().getSimpleName();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        switch(page){
            // Home
            case "MainActivity":
                message = getString(R.string.help_main);
                break;
            // Get Image
            case "ImageDisplay":
                message = getString(R.string.help_get_image);
                break;
            // User Details
            case "UserDetails":
                message = getString(R.string.help_user_details);
                break;
            // Image List
            case "SavedList":
                message = getString(R.string.help_image_list);
                break;
            // Date Picker
            case "DatePicker":
                message = getString(R.string.help_date_picker);
                break;
            // Everything
            case "Jokes":
                message = getString(R.string.help_everything);
                break;
        }
        builder.setTitle(getResources().getString(R.string.alert_title));

        final View alertLayout = getLayoutInflater().inflate(R.layout.help_dialog, null);

        final TextView msgText = alertLayout.findViewById(R.id.HD_messageView);
        msgText.setText(message);

        builder.setView(alertLayout);

        builder.setPositiveButton(R.string.ok,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        // the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        // Make the button pretty
        Button okButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        if (okButton != null) {
            okButton.setBackgroundColor(getResources().getColor(R.color.hh_blue));
            okButton.setTextColor(getResources().getColor(R.color.hh_orange));
        }
    }
}