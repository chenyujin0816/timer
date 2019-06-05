package com.example.timer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

import com.example.timer.data.TimerContract;

public class AddActivity extends AppCompatActivity implements View.OnClickListener, LoaderManager.LoaderCallbacks<Cursor> {

    TimePicker begin;
    TimePicker end;
    ImageView cancle_add;
    ImageView finish_add;
    EditText title;
    EditText adress;
    EditText body;

    Record record;
    Intent intent;

    String stitle;
    String sadress;
    String sbody;
    String sbegin;
    String send;
    String date=null;
    long lbegin;
    long lend;

    private Uri currentUri;
    private static final int ADD_LOADER=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        intent=this.getIntent();
        initView();
        setListener();

    }

    private void  initView(){
        begin=(TimePicker)findViewById(R.id.begin);
        end=(TimePicker) findViewById(R.id.end);
        begin.setIs24HourView(true);//是否显示24小时制？默认false
        end.setIs24HourView(true);
        cancle_add=(ImageView)findViewById(R.id.cancle_add);
        finish_add=(ImageView)findViewById(R.id.finish_add);
        title=(EditText)findViewById(R.id.title);
        adress=(EditText)findViewById(R.id.address);
        body=(EditText)findViewById(R.id.body);
        currentUri=intent.getData();
        if (intent.getStringExtra("date")!=null){
            date=intent.getStringExtra("date");
            begin.setHour(0);
            begin.setMinute(0);
            end.setHour(23);
            end.setMinute(59);
        }
        else if(currentUri!=null){
            getSupportLoaderManager().initLoader(ADD_LOADER,null,this);
        }
    }

    private void setListener(){
        cancle_add.setOnClickListener(this);
        finish_add.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancle_add  :
                finish();
                break;
            case R.id.finish_add:
                writeToDB();
                break;
        }
    }

    public void writeToDB(){

        stitle=title.getText().toString();
        sadress=adress.getText().toString();
        sbody=body.getText().toString();
        sbegin=Integer.toString(begin.getHour())+":"+Integer.toString(begin.getMinute());
        send=Integer.toString(end.getHour())+":"+Integer.toString(end.getMinute());
        lbegin=DateUtil.getStringToDate(sbegin,DateUtil.stdTimePattern);
        lend=DateUtil.getStringToDate(send,DateUtil.stdTimePattern);

        ContentValues values=new ContentValues();
        values.put(TimerContract.RecordEntry.COLUMN_RECORD_TITLE,stitle);
        values.put(TimerContract.RecordEntry.COLUMN_RECORD_ADDRESS,sadress);
        values.put(TimerContract.RecordEntry.COLUMN_RECORD_DETAIL,sbody);
        values.put(TimerContract.RecordEntry.COLUMN_RECORD_BEGIN_TIME,lbegin);
        values.put(TimerContract.RecordEntry.COLUMN_RECORD_END_TIME,lend);
        values.put(TimerContract.RecordEntry.COLUMN_RECORD_DATE,date);

        if(currentUri!=null){
            if(isAnyNull()) {
                getContentResolver().update(currentUri,values,null,null);
                finish();
            }
            else{
                Log.e("isA", ""+isAnyNull() );
                sendADialog();
            }
        }
        else if(date!=null){
            if (isAnyNull()){
                getContentResolver().insert(TimerContract.RecordEntry.CONTENT_URI,values);
                finish();
            }
            else {
                sendADialog();
            }
        }

    }

    private boolean isAnyNull(){
        if (stitle.length()==0)
            return false;
        else if(lbegin>lend)
            return false;
        else
            return true;
    }

    private void sendADialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);
        if(stitle.length()==0){
            builder.setTitle("Title为空");
            builder.setMessage("为什么没有标题？");
        }

        else if(lbegin>lend){
            builder.setTitle("时间出错");
            builder.setMessage("开始时间迟于结束时间！");
        }


        builder.setPositiveButton("再改改", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.e("setPosition", "sure" );
            }
        });

        builder.show();
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        CursorLoader cursorLoader=new CursorLoader(
                this,
                currentUri,
                null,
                null,
                null,
                null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();
        String date=data.getString(data.getColumnIndex(TimerContract.RecordEntry.COLUMN_RECORD_DATE));
        String title=data.getString(data.getColumnIndex(TimerContract.RecordEntry.COLUMN_RECORD_TITLE));
        String address=data.getString(data.getColumnIndex(TimerContract.RecordEntry.COLUMN_RECORD_ADDRESS));
        String detail=data.getString(data.getColumnIndex(TimerContract.RecordEntry.COLUMN_RECORD_DETAIL));
        long beginTime=data.getLong(data.getColumnIndex(TimerContract.RecordEntry.COLUMN_RECORD_BEGIN_TIME));
        long endTime=data.getLong(data.getColumnIndex(TimerContract.RecordEntry.COLUMN_RECORD_END_TIME));

        this.title.setText(title);
        this.adress.setText(address);
        this.body.setText(detail);
        String time=DateUtil.getDateToString(beginTime,DateUtil.stdTimePattern);
        String hour=time.substring(0,2);
        String min=time.substring(3,5);
        begin.setHour(Integer.parseInt(hour));
        begin.setMinute(Integer.parseInt(min));
        time=DateUtil.getDateToString(endTime,DateUtil.stdTimePattern);
        hour=time.substring(0,2);
        min=time.substring(3,5);
        end.setHour(Integer.parseInt(hour));
        end.setMinute(Integer.parseInt(min));

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}
