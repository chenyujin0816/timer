package com.example.timer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

//    String date =DateUtil.getCurTime();

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
        }
    }

    public void writeToDB(){
        String stitle;
        String sadress;
        String sbody;



        stitle=title.getText().toString();
        sadress=adress.getText().toString();
        sbody=body.getText().toString();

        Record record=new Record();




//        GlobalUtil.getInstance().recordDatabaseHelper.addRecord();

    }
}
