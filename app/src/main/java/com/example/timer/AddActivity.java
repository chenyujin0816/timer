package com.example.timer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    TimePicker begin;
    TimePicker end;
    ImageView cancle_add;
    ImageView finish_add;
    EditText title;
    EditText adress;
    EditText body;

    String date =DateUtil.getCurTime(DateUtil.stdDatePattern);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initView();
        setListener();

    }

    private void  initView(){
        begin=(TimePicker)findViewById(R.id.begin);
        end=(TimePicker) findViewById(R.id.end);
        begin.setIs24HourView(true);//是否显示24小时制？默认false
        end.setIs24HourView(true);
        begin.setHour(0);
        begin.setMinute(0);
        end.setHour(23);
        end.setMinute(59);
        cancle_add=(ImageView)findViewById(R.id.cancle_add);
        finish_add=(ImageView)findViewById(R.id.finish_add);
        title=(EditText)findViewById(R.id.title);
        adress=(EditText)findViewById(R.id.address);
        body=(EditText)findViewById(R.id.body);
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
                Log.e("Addactivity", "write" );
                break;
        }
    }

    public void writeToDB(){
        String stitle;
        String sadress;
        String sbody;
        String sbegin;
        String send;
        long lbegin;
        long lend;

        Log.e("Addactivity", "111" );
        stitle=title.getText().toString();
        sadress=adress.getText().toString();
        sbody=body.getText().toString();
        sbegin=Integer.toString(begin.getHour())+":"+Integer.toString(begin.getMinute());
        send=Integer.toString(end.getHour())+":"+Integer.toString(end.getMinute());
        lbegin=DateUtil.getStringToDate(sbegin,DateUtil.stdTimePattern);
        lend=DateUtil.getStringToDate(send,DateUtil.stdTimePattern);

        Log.e("Addactivity", "222" );
        Record record=new Record();
        record.setTitle(stitle);
        record.setAddress(sadress);
        record.setDetail(sbody);
        record.setBeginTime(lbegin);
        record.setEndTime(lend);
        record.setDate(date);

        Log.e("Addactivity", "333" );
        GlobalUtil.getInstance().recordDatabaseHelper.addRecord(record);
        Log.e("Addactivity", "444" );
        Log.e("Addactivity",GlobalUtil.getInstance().recordDatabaseHelper.readRecords(date).get(0).getTitle());

        finish();

    }
}
