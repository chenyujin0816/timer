package com.example.timer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.RelativeLayout;

import com.example.timer.Data.RecordProvider;
import com.example.timer.Data.TimerContract;

import java.util.LinkedList;

public class RecordDatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="Records";
    public static final int DB_VERSION=1;

    private static final String CREATE_RECORD_DB="CREATE TABLE Records("
            +"id INTEGER PRIMARY KEY AUTOINCREMENT,"
            +"uuid TEXT,"
            +"title TEXT NOT NULL,"
            +"detail TEXT,"
            +"date DATE NOT NULL,"
            +"beginTime INTEGER NOT NULL,"
            +"endTime INTEGER NOT NULL,"
            +"address TEXT)";

    public RecordDatabaseHelper( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_RECORD_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addRecord(Record record){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("uuid",record.getUuid());
        values.put("title",record.getTitle());
        values.put("detail",record.getDetail());
        values.put("date",record.getDate());
        values.put("beginTime",record.getBeginTime());
        values.put("endTime",record.getEndTime());
        values.put("address",record.getAddress());
        db.insert(DB_NAME,null,values);
    }

    public void removeRecord(String uuid){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(DB_NAME,"uuid=?",new String[]{uuid});
    }

    public void editRecord(String uuid,Record record){
        removeRecord(uuid);
        record.setUuid(uuid);
        addRecord(record);
    }

    public LinkedList<Record> readRecords(String dataStr){
        LinkedList<Record> records=new LinkedList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT DISTINCT * FROM Records WHERE date=? ORDER BY beginTime ASC",
                new String[]{dataStr});
        while(cursor.moveToNext()){
            Record record=new Record();
            String uuid=cursor.getString(cursor.getColumnIndex("uuid"));
            String title=cursor.getString(cursor.getColumnIndex("title"));
            String detail=cursor.getString(cursor.getColumnIndex("detail"));
            String date=cursor.getString(cursor.getColumnIndex("date"));
            long beginTime=cursor.getLong(cursor.getColumnIndex("beginTime"));
            long endTime=cursor.getLong(cursor.getColumnIndex("endTime"));
            String address=cursor.getString(cursor.getColumnIndex("address"));

            record.setUuid(uuid);
            record.setTitle(title);
            record.setDetail(detail);
            record.setAddress(address);
            record.setDate(date);
            record.setBeginTime(beginTime);
            record.setEndTime(endTime);

            records.add(record);
        }
        return records;
    }

    public Record readRecord(String uid){
        LinkedList<Record> records=new LinkedList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT DISTINCT * FROM Records WHERE uuid=?",
                new String[]{uid});
        Record record=new Record();
        if(cursor.moveToFirst()){
            String uuid=cursor.getString(cursor.getColumnIndex("uuid"));
            String title=cursor.getString(cursor.getColumnIndex("title"));
            String detail=cursor.getString(cursor.getColumnIndex("detail"));
            String date=cursor.getString(cursor.getColumnIndex("date"));
            long beginTime=cursor.getLong(cursor.getColumnIndex("beginTime"));
            long endTime=cursor.getLong(cursor.getColumnIndex("endTime"));
            String address=cursor.getString(cursor.getColumnIndex("address"));

            record.setUuid(uuid);
            record.setTitle(title);
            record.setDetail(detail);
            record.setAddress(address);
            record.setDate(date);
            record.setBeginTime(beginTime);
            record.setEndTime(endTime);
        }
        return record;
    }

    public LinkedList<String> getAvaliableDate(){
        LinkedList<String> dates=new LinkedList<>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT DISTINCT * FROM Records ORDER BY date ASC",
                new String[]{});

        while(cursor.moveToNext()){
            String date=cursor.getString(cursor.getColumnIndex("date"));
            if(!dates.contains(date))
                dates.add(date);
        }
        return dates;
    }
}
