package com.example.hitchikersguide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
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
public class SavedList extends BaseActivity {
    private ArrayList<SpacePic> pictures = new ArrayList<>();
    private SQLiteDatabase myDB;
    private Cursor results;
    private SpacePic curPic;
    String imgDate, imgTitle, imgURL, imgDetails, imgHDURL;
    SpacePic pic;
    static boolean isTablet;

    /**
     * On Create Function initializes widgets and listeners
     *
     * @param savedInstanceState - the state that the app was last saved in
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the Main Activity layout into the Base activity frame
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_saved_list, contentFrameLayout);

        isTablet = findViewById(R.id.fragmentLocation) != null;

        // Listview
        ListView imgList = findViewById(R.id.SL_ListOfImages);
        MyAdapter myAdapter = new MyAdapter();
        imgList.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        loadSavedPics();

        Intent passImg = getIntent();
        imgTitle = passImg.getStringExtra("Title");
        imgDate = passImg.getStringExtra("Date");
        imgURL = passImg.getStringExtra("URL");
        imgHDURL = passImg.getStringExtra("HDURL");
        imgDetails = passImg.getStringExtra("Details");

        if (imgTitle != null) {
            ContentValues newRowValues = new ContentValues();
            newRowValues.put(MyDBOpener.COL_DATE, imgDate);
            newRowValues.put(MyDBOpener.COL_URL, imgURL);
            newRowValues.put(MyDBOpener.COL_HDURL, imgHDURL);
            newRowValues.put(MyDBOpener.COL_TITLE, imgTitle);
            newRowValues.put(MyDBOpener.COL_DETAIL, imgDetails);
            long newID = myDB.insert(MyDBOpener.TABLE_NAME, null, newRowValues);

            pic = new SpacePic(imgDate, imgTitle, imgURL, imgHDURL, imgDetails);
            pictures.add(pic);
            myAdapter.notifyDataSetChanged();
        };

        // Get details of an item on the list
        imgList.setOnItemClickListener((parent, view, position, id) -> {

            Bundle dataToPass = new Bundle();
            dataToPass.putString("Title", pictures.get(position).imgTitle);
            dataToPass.putString("Details", pictures.get(position).imgDetails);
            dataToPass.putString("HDURL", pictures.get(position).imgHDURL);

            if (isTablet) {
                DetailsFragment dFragment = new DetailsFragment();
                dFragment.setArguments(dataToPass);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentLocation, dFragment).commit();
            }
            else {
                Intent activityEM = new Intent(this, EmptyActivity.class);
                activityEM.putExtras(dataToPass);
                startActivity(activityEM);
            }
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
                        pic = pictures.get(position);
                        pictures.remove(position);
                        deleteSpacePic(pic);
                        myAdapter.notifyDataSetChanged();
                        if (isTablet) {
                            this.getSupportFragmentManager().beginTransaction()
                                    .remove(getSupportFragmentManager().findFragmentById(R.id.fragmentLocation))
                                    .commit();
                        }
                    })

                    // No action
                    .setNegativeButton(R.string.no, (click, arg) -> { })

                    //Show the dialog
                    .create().show();
                return true;
            } );

        //TODO: Progress bar will be on async task

        // Load data from the database

        // Toolbar as action bar
        Toolbar tb = findViewById(R.id.toolbar);
        setSupportActionBar(tb);
    }

    private void loadSavedPics() {
        // Connect to DB
        MyDBOpener dbOpen = new MyDBOpener(this);
        myDB = dbOpen.getWritableDatabase();

        // list of columns
        String[] columns = {MyDBOpener.COL_ID, MyDBOpener.COL_DATE, MyDBOpener.COL_URL,
                            MyDBOpener.COL_HDURL, MyDBOpener.COL_TITLE, MyDBOpener.COL_DETAIL};
        // get all entries
        results = myDB.query(false, MyDBOpener.TABLE_NAME, columns, null,
                null, null, null, null, null);

        // Get column indices
        int idColIdx = results.getColumnIndex(MyDBOpener.COL_ID);
        int dateColIdx = results.getColumnIndex(MyDBOpener.COL_DATE);
        int urlColIdx = results.getColumnIndex(MyDBOpener.COL_URL);
        int hdurlColIdx = results.getColumnIndex(MyDBOpener.COL_HDURL);
        int titleColIdx = results.getColumnIndex(MyDBOpener.COL_TITLE);
        int detailColIdx = results.getColumnIndex(MyDBOpener.COL_DETAIL);


        // Iterate over the results, return true if there is a next item:
        while (results.moveToNext()) {
            // Create an image and add it to the arrayList
            curPic = new SpacePic(results.getLong(idColIdx), results.getString(dateColIdx),
                    results.getString(urlColIdx));
            //TODO: Might need to check if these are empty... but should be ok
            curPic.setHDURL(results.getString(hdurlColIdx));
            curPic.setTitle(results.getString(titleColIdx));
            curPic.setDetails(results.getString(detailColIdx));
            pictures.add(curPic);
        }
    }
    protected void deleteSpacePic(SpacePic pic) {
        myDB.delete(MyDBOpener.TABLE_NAME, MyDBOpener.COL_ID + "= ?", new String[] {Long.toString(pic.getImgID())});
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
//            View newView = convertView;
            LayoutInflater inflater = getLayoutInflater();

            // make a new row
//            if (myView == null) {
                myView = inflater.inflate(R.layout.img_list_row, parent, false);
//            }
            // TODO: Add view holder pattern in note need to write code to account for delete if I use this

            //set text for new row
            TextView dateView = myView.findViewById(R.id.IL_Date);
            dateView.setText(pictures.get(position).imgDate);

            TextView urlView = myView.findViewById(R.id.IL_URL);
            urlView.setText(pictures.get(position).imgURL);

            TextView titleView = myView.findViewById(R.id.IL_Title);
            titleView.setText(pictures.get(position).imgTitle);

//            TextView hdurlView = myView.findViewById(R.id.IL_HDURL);
//            hdurlView.setText(pictures.get(position).imgHDURL);
//
//            TextView detailsView = myView.findViewById(R.id.IL_Details);
//            detailsView.setText(pictures.get(position).imgDetails);

            // return new row to be added to table
            return myView;
        }
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //Inflater to inflate menu items in toolbar
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.toolbar_layout, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        //new Intents that direct to each activity
//        Intent image = new Intent(this, ImageDisplay.class);
//        Intent jokes = new Intent(this, Jokes.class);
//        Intent saved = new Intent(this, SavedList.class);
//        Intent main = new Intent(this, MainActivity.class);
//
//        //switch cases for toolbar icons, direct to each activity
//        //depending on icon selected by user
//        switch(item.getItemId()) {
//            case R.id.ufo:
//                startActivity(image);
//                break;
//            case R.id.towel:
//                startActivity(jokes);
//                break;
//            case R.id.comet:
//                startActivity(saved);
//                break;
//            case R.id.number:
//                startActivity(main);
//                break;
//        }
//        return true;
//    }
}