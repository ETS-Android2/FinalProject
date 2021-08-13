package com.example.hitchikersguide;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

/**
 * Save List Activity holds the list of saved images
 * Links to joke page through Jokes Button
 *
 * @author Brianna Guerin
 * @author Jenne Stamplecoskie
 */
public class SavedList extends AppCompatActivity {
    private ArrayList<SpacePic> pictures = new ArrayList<>(); // Messages

    /**
     * On Create Function initializes widgets and listeners
     *
     * @param savedInstanceState - the state that the app was last saved in
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_list);

        // Define Next Activity - Jokes
        Intent activityJK = new Intent(this, Jokes.class);

        // Button to take you to Joke activity
        Button jokeButton = findViewById(R.id.SL_ToJokesButton);
        jokeButton.setOnClickListener(click -> startActivity(activityJK));

        // Listview
        ListView imgList = findViewById(R.id.SL_ListOfImages);
        MyAdapter myAdapter = new MyAdapter();
        imgList.setAdapter(myAdapter);

        //Add elements to the list view
        // TODO: Remove once we have a proper list of saved images
        SpacePic pic;
        for ( int i = 1; i <= 8; i++ ){
            pic = new SpacePic("date" + i, "url" + i, i);
            pictures.add(pic);
        }

        // Get details of an item on the list
        imgList.setOnItemClickListener((parent, view, position, id) -> {
            String details = "image date: " + pictures.get(position).imgDate +
                    " url: " + pictures.get(position).imgURL + " ID: " +
                    pictures.get(position).imgID;
            Snackbar.make(imgList, details, Snackbar.LENGTH_LONG).show();
        });

        // Remove an item from the list
        imgList.setOnItemLongClickListener(
                // Create a Dialog
                (parent, view, position, id) -> {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                    alertDialogBuilder.setTitle(getResources().getString(R.string.SL_Alert_Title))

                            // Message
                            .setMessage(getResources().getString(R.string.SL_Alert_msg1) + position + "\n"
                                    + getResources().getString(R.string.SL_Alert_msg2) + id)

                            // Yes Action
                            .setPositiveButton(R.string.yes, (click, arg) -> {
                                pictures.remove(position);
                                myAdapter.notifyDataSetChanged();
                            })

                            // No action
                            .setNegativeButton(R.string.no, (click, arg) -> { })

                            //Show the dialog
                            .create().show();
                    return true;
                } );

        //TODO: Progress bar will be on async task
        //TODO: Snackbar to give list details
        //TODO: Onclick listener
    }

    /**
     * Adapter Class for ListView extends BaseAdapter
     */
    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return pictures.size();
        }

        @Override
        public Object getItem(int position) {
            return pictures.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View myView, ViewGroup parent) {
            //View newView = convertView;
            LayoutInflater inflater = getLayoutInflater();

            // make a new row
            myView = inflater.inflate(R.layout.img_list_row, parent, false);
            // TODO: Add view holder pattern in

            //set text for new row
            TextView dateView = myView.findViewById(R.id.DateGoesHere);
            dateView.setText(pictures.get(position).imgDate);
            TextView urlView = myView.findViewById(R.id.TextGoesHere);
            urlView.setText(pictures.get(position).imgURL);

            // return new row to be added to table
            return myView;
        }
    }
}