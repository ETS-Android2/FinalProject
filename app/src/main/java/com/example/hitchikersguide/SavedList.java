package com.example.hitchikersguide;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SavedList extends AppCompatActivity {
    private ArrayList<SpacePic> elements = new ArrayList<>(); // Messages

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_list);

        //Define Next Activity
        Intent activityJK = new Intent(this, Jokes.class);

        // Button to take you to Joke activity
        Button jokeButton = findViewById(R.id.SL_ToJokesButton);
        jokeButton.setOnClickListener(click -> startActivity(activityJK));

        // Listview
        ListView imgList = findViewById(R.id.SL_ListOfImages);
        MyAdapter myAdapter = new MyAdapter();
        imgList.setAdapter(myAdapter);

        //Add elements to the list view to start
        String[] picDetails = {"date", "url", "HDurl", "description"};
        SpacePic aPic = new SpacePic(picDetails, 1);
        aPic.imgTitle = "Title";
        elements.add(aPic);
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
                                elements.remove(position);
                                myAdapter.notifyDataSetChanged();
                            })

                            // No action
                            .setNegativeButton(R.string.no, (click, arg) -> { })

                            //Show the dialog
                            .create().show();
                    return true;
                } );

        //TODO: Progress bar will be on async task
    }


        // ----------------------------------------------------------------------------------
    /**
     * Adapter Class for ListView
     */
    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return elements.size();
        }

        @Override
        public Object getItem(int position) {
            return elements.get(position);
        }

        @Override
        public long getItemId(int position) {
            return (long) position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View newView = convertView;
            LayoutInflater inflater = getLayoutInflater();

            // make a new row
            newView = inflater.inflate(R.layout.img_list_row, parent, false);

            //set text for new row
            TextView tView = newView.findViewById(R.id.TextGoesHere);
            tView.setText(elements.get(position).imgTitle);

            // return new row to be added to table
            return newView;
        }
    }
}