package com.example.hitchikersguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * The empty activity class is used for holding the fragment_details layout.
 * It shows the description of the image selected.
 * The image description provided was long enough that it required a complete screen to display.
 *
 * @author Brianna Guerin
 * @author Jenne Stamplecoskie
 *
 */
public class EmptyActivity extends AppCompatActivity {

    /** EmptyActivity used to view fragment details if viewed on phone
     * Displays data passed from SavedList
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        Bundle dataToPass = getIntent().getExtras();

        DetailsFragment dFragment = new DetailsFragment();
        dFragment.setArguments(dataToPass);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLocation, dFragment).commit();
    }
}