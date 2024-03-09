package com.mrsjdeveloper.crudsqlitedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    //Database information
    private static final String DB_NAME="StudentSubject.db";
    private static final int DB_VERSION=1;

    //table information
    public static final String TABLE_NAME="Subject";
    //table columns
    public static final String _ID="_id";
    public static final String SUBJECT = "subjectName";
    public static final String DESC="description";

    //create table query
    private static final String CREATE_TABLE="create table "+TABLE_NAME+ "(" +_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "+SUBJECT+" TEXT NOT NULL, "+DESC+ " TEXT);";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
}
