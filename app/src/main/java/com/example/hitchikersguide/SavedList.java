package com.example.hitchikersguide;

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

public class SavedList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_list);

        //Define Next Activity
        Intent activityJK = new Intent(this, Jokes.class);

        Button jokeButton = findViewById(R.id.SL_ToJokesButton);
        jokeButton.setOnClickListener(click -> startActivity(activityJK));

        // Listview
        ListView imgList = findViewById(R.id.SL_ListOfImages);
        MyAdapter myAdapter = new MyAdapter();
        imgList.setAdapter(myAdapter);

        // Progress bar will be on async task
    }

    /**
     * Adapter Class for ListView
     */
    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 0;//elements.size();
        }

        @Override
        public Object getItem(int position) {
            return null;//elements.get(position);
        }

        @Override
        public long getItemId(int position) {
            return (long) position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View newView = convertView;
            LayoutInflater inflater = getLayoutInflater();

//            // make a new row
//            newView = inflater.inflate(elements.get(position).getLayout(), parent, false);
//
//            //set text for new row
//            TextView tView = newView.findViewById(elements.get(position).getTextId());
//            tView.setText(elements.get(position).msgText);

            // return new row to be added to table
            return newView;
        }
    }
}