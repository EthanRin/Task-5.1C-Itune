package com.example.a51cp2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "iTuneUsers.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "userProfiles";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_FULLNAME = "fullName";
    private static final String COLUMN_PLAYLISTS = "playlist";

    public MyDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_USERNAME + " TEXT PRIMARY KEY, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_FULLNAME + " TEXT, " +
                COLUMN_PLAYLISTS + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addUser(String username, String full_name, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USERNAME, username);
        cv.put(COLUMN_PASSWORD, password);
        cv.put(COLUMN_FULLNAME, full_name);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Insert Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Insert Successful", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean loginValidation(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            // Define the columns you want to retrieve
            String[] projection = {COLUMN_USERNAME, COLUMN_PASSWORD};

            // Define the selection criteria
            String selection = COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?";

            // Define the selection arguments
            String[] selectionArgs = {username, password};

            // Query the database
            cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);

            // Check if any row matches the provided username and password
            return cursor != null && cursor.getCount() > 0;
        } finally {
            // Close the cursor to release resources
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void addPlaylists(String username, String password, List<String> playlists) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Serialize the list to a JSON string
        String playlistsJson = new Gson().toJson(playlists);

        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_PLAYLISTS, playlistsJson);

        // Insert or replace the playlists for the user
        long result = db.update(TABLE_NAME, values, COLUMN_USERNAME + "=?", new String[]{username});
        if (result == -1) {
            Toast.makeText(context, "Insertion failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Insertion Successful", Toast.LENGTH_SHORT).show();
        }
    }

    public List<String> getPlaylists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> playlists = new ArrayList<>();
        Cursor cursor = null;
        try {
            // Define the columns you want to retrieve
            String[] projection = {COLUMN_PLAYLISTS};

            // Define the selection criteria
            String selection = COLUMN_USERNAME + " = ?";

            // Define the selection arguments
            String[] selectionArgs = {username};

            // Query the database
            cursor = db.query(TABLE_NAME, projection, selection, selectionArgs, null, null, null);

            // If cursor has data, retrieve the playlists
            if (cursor != null && cursor.moveToFirst()) {
                String playlistsJson = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PLAYLISTS));
                Type listType = new TypeToken<List<String>>(){}.getType();
                playlists = new Gson().fromJson(playlistsJson, listType);
            }
        } finally {
            // Close the cursor to release resources
            if (cursor != null) {
                cursor.close();
            }
        }
        return playlists;
    }

}
