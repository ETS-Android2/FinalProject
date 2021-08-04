package com.example.hitchikersguide;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Jokes page displays selected space jokes. Cause we got jokes son.
 *
 * @author Brianna Guerin
 * @author Jenne Stamplecoskie
 */
public class Jokes extends AppCompatActivity {
    TextView JKshowjoke;
    Button JKgetjoke;
    Button JKanswer;

    /**
     * On Create Function initializes widgets and listeners
     *
     * @param savedInstanceState - the state that the app was last saved in
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jokes);

        JKshowjoke = findViewById(R.id.JK_textView);
        JKgetjoke = findViewById(R.id.JK_getjoke);
        JKanswer = findViewById(R.id.JK_answer);

        String[][] jokes = {
                {"I am throwing a party in space", "Can you help me planet?"},
                {"Where do the keyboards go to have dinner?", "The space bar."},
                {"Why did the cow go to outer space?", "To visit the milky way."},
                {"Why don't aliens eat clowns?", "They taste funny!"},
                {"What kind of music do planets sing?", "Nep-tunes!"},
                {"Saturn's name is the best in our solar system", "It has a nice ring to it."},
                {"I'm reading a book about anti-gravity.", "It's hard to put down."},
                {"Yesterday I was charged $100,000 for sending my cat into space.", "It was a cat astro fee."},
                {"Why did the Americans win the space race?", "Because the Soviets were Stalin"},
                {"How did the space teddy cross the road?", "Ewoked."},
                {"Why did the star go to school?", "So it could get brighter"},
                {"I'm addicted to space jokes.", "Someday I'll over-comet"},
                {"My kid is obsessed with the moon", "I'm hoping it's just a phase."},
                {"What's E.T. short for?", "He has little legs."},
                {"Why didn't the sun go to college?", "It already has a million degrees."},
                {"How does the man on the moon cut his hair?", "Eclipse it."},
                {"What does the astronaut use to keep his feet warm?", "A space heater."},
                {"How do you start a fight in outer space?", "Comet me bro!"}
        };

        JKgetjoke.setOnClickListener(click -> {
            Random rand = new Random();
            int max = 19;
            int num = rand.nextInt(max);

            JKshowjoke.setText(jokes[num][0]);
            String answer = jokes[num][1];

            JKanswer.setOnClickListener(v -> {
                Snackbar.make(JKshowjoke, answer, Snackbar.LENGTH_LONG).show();
            });
        });
    }
}