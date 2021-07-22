package com.example.hitchikersguide;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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

    }
}