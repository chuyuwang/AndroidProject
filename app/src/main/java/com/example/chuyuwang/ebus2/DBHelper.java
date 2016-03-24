package com.example.chuyuwang.ebus2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chuyuwang on 2/22/15.
 */
public class DBHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 4;

    private static final String DATABASE_NAME = "course.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String CREATE_TABLE_COURSE = " CREATE TABLE "+ Course.Table +"("
            + Course.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + Course.KEY_NAME + " TEXT, "
            + Course.KEY_TIME + " TEXT, "
            + Course.KEY_PROF + " TEXT )";
        db.execSQL(CREATE_TABLE_COURSE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+ Course.Table);

    }
}
