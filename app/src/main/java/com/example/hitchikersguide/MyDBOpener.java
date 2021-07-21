package com.example.hitchikersguide;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBOpener extends SQLiteOpenHelper {
    protected final static String DATABASE_NAME = "mySpacePics";
    protected final static int VERSION_NUM = 1;
    public final static String TABLE_NAME = "SPACE_PIC";
    public final static String COL_ID = "PicID";
    public final static String COL_DATE = "PicDate";
    public final static String COL_URL = "PicURL";
    public final static String COL_HDURL = "PicHDURL";
    public final static String COL_TITLE = "PicTitle";
    public final static String COL_DETAIL = "PicDetail";


    /**
     * Constructor class for the SQLiteOpenHelper.
     *
     * @param ctx - This is the activity which is calling the DBOpener
     */
    public MyDBOpener(Context ctx) {
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    /**
     * Create a Database if none exists. It can be found in the
     * /data/data/com.example.hitchikersguide/database directory.
     *
     * @param db - the database object
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + TABLE_NAME
                + " (" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_DATE + " text,"
                + COL_URL + " text,"
                + COL_HDURL + " text,"
                + COL_TITLE + " text,"
                + COL_DETAIL  + " text);"
        );
    }

    /**
     * This function gets called if the database version on your device is lower than VERSION_NUM.
     * It deletes the existing table and creates a new one. Note your saved data will not be
     * recovered into the new table.
     *
     * @param db - the database object
     * @param oldVersion - version of the current database object
     * @param newVersion - version of the database applicable to the currently running code
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table:
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create the new table:
        onCreate(db);
    }




    /**
     * This function gets called if the database version on your device is higher than VERSION_NUM.
     * It deletes the existing table and creates a new one. Note your saved data will not be
     * recovered into the new table.
     *
     * @param db - the database object
     * @param oldVersion - version of the current database object
     * @param newVersion - version of the database applicable to the currently running code
     */
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop the old table:
        db.execSQL( "DROP TABLE IF EXISTS " + TABLE_NAME);

        // Create the new table:
        onCreate(db);
    }

    /**
     * Opens the database.
     *
     * @param db - the database object.
     */
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
