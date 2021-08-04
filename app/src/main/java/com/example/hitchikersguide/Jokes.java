package com.example.hitchikersguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Jokes page displays selected space jokes. Cause we got jokes son.
 *
 * @author Brianna Guerin
 * @author Jenne Stamplecoskie
 */
public class Jokes extends AppCompatActivity {
    TextView textView;
    Button jkButton;

    /**
     * On Create Function initializes widgets and listeners
     *
     * @param savedInstanceState - the state that the app was last saved in
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);

        textView = findViewById(R.id.JK_textView);
        jkButton = findViewById(R.id.JK_button);

        jkButton.setOnClickListener(click -> {
                    Toast.makeText(this, "This will display the answer to a joke.",
                            Toast.LENGTH_LONG).show();
                });

        //TODO: Toast with the answer when you hit the button

        // Toolbar as action bar
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
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
}