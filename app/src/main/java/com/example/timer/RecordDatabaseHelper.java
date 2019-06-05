package com.example.timer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.timer.data.TimerContract;

import java.util.LinkedList;

public class RecordDatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "Records";
    public static final int DB_VERSION = 1;

    public RecordDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RECORD_DB = "CREATE TABLE " + TimerContract.RecordEntry.TABLE_NAME + "("
                + TimerContract.RecordEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TimerContract.RecordEntry.COLUMN_RECORD_TITLE + " TEXT NOT NULL,"
                + TimerContract.RecordEntry.COLUMN_RECORD_DETAIL + " TEXT,"
                + TimerContract.RecordEntry.COLUMN_RECORD_DATE + " DATE NOT NULL,"
                + TimerContract.RecordEntry.COLUMN_RECORD_BEGIN_TIME + " INTEGER NOT NULL,"
                + TimerContract.RecordEntry.COLUMN_RECORD_END_TIME + " INTEGER NOT NULL,"
                + TimerContract.RecordEntry.COLUMN_RECORD_ADDRESS + " TEXT)";
        db.execSQL(CREATE_RECORD_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
